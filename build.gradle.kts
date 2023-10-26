import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val flywayVersion: String by project

plugins {
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    id("org.flywaydb.flyway") version "8.5.11"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
}

group = "br.com.requestReply"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.flywaydb:flyway-core:$flywayVersion")
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation ("org.postgresql:postgresql")

    implementation ("org.springframework.boot:spring-boot-starter-validation")
    implementation ("org.springframework.boot:spring-boot-devtools")


    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

flyway {
    val migrationsDir by lazy {
        val projectRootDir = System.getProperty("user.dir")
        val schemaMigration = System.getenv("SCHEMA_MIGRATIONS")
        val fileMigration = System.getenv("FILE_MIGRATIONS")
        "filesystem:/$projectRootDir/migrations/$fileMigration"
    }
    url = System.getenv("DATABASE_JDBC_RW_URL")
    user = System.getenv("SCHEMA_MIGRATIONS_USER")
    password = System.getenv("SCHEMA_MIGRATIONS_PASSWORD")
    schemas = arrayOf(System.getenv("SCHEMA_MIGRATIONS"))
    baselineOnMigrate = true
    locations = arrayOf(migrationsDir)
    createSchemas = true
}