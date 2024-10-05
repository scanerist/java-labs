package ru.itmo.migrations;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;

public class DatabaseMigration {
    public static void main(String[] args) {
        FluentConfiguration configuration = new FluentConfiguration()
                .driver("org.postgresql.Driver")
                .dataSource("jdbc:postgresql://localhost:5432/kotiki", "postgres", "postgres")
                .locations("infrastructure\\src\\main\\java\\ru\\itmo\\migrations");

        Flyway flyway = new Flyway(configuration);

       flyway.migrate();
    }
}
