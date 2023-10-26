-- SEQUENCE
CREATE SEQUENCE pix_seq
    AS bigint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- FUNCTION
create OR REPLACE FUNCTION pix_next_id(OUT result bigint) AS
$$
DECLARE
our_epoch  bigint := 1609459200000;
    seq_id
bigint;
    now_millis
bigint;
    shard_id
int    := 1;
BEGIN
SELECT nextval('pix.pix_seq') % 1024
INTO seq_id;
SELECT FLOOR(EXTRACT(EPOCH FROM clock_timestamp()) * 1000)
INTO now_millis;
result
:= (now_millis - our_epoch) << 23;
    result
:= result | (shard_id << 10);
    result
:= result | (seq_id);
END;
$$
LANGUAGE PLPGSQL;

CREATE TABLE "pix"
(
    "id"                        bigint PRIMARY KEY   DEFAULT pix_next_id(),
    "name"                      varchar(100) NULL,
    "status"                    varchar(100) NULL,
    "correlation_id"             varchar(100) NULL,
    "quantity"                  bigint NULL,
    "amount"                    varchar(100) NULL,
    UNIQUE ("id")
);

GRANT USAGE ON SEQUENCE pix_seq TO PUBLIC;

GRANT SELECT, INSERT, UPDATE ON pix_seq to public;

CREATE INDEX correlation_id_idx ON pix (correlation_id);