build:
	@gradle clean build

init-project: init-database-test init-database-pix

init-database-pix:
	export FILE_MIGRATIONS=pix \
	export SCHEMA_MIGRATIONS_PASSWORD=123456 \
  	export SCHEMA_MIGRATIONS_USER=postgres \
    export DATABASE_JDBC_RW_URL=jdbc:postgresql://127.0.0.1:5432/pix \
	export SCHEMA_MIGRATIONS=pix;./gradlew flywayMigrate -i

init-database-test:
	export FILE_MIGRATIONS=test \
	export SCHEMA_MIGRATIONS_PASSWORD=123456 \
  	export SCHEMA_MIGRATIONS_USER=postgres \
    export DATABASE_JDBC_RW_URL=jdbc:postgresql://127.0.0.1:5432/pix \
	export SCHEMA_MIGRATIONS=test;./gradlew :flywayMigrate -i