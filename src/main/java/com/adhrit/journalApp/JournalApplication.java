package com.adhrit.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {

    public static void main(String[] args) {
        SpringApplication.run(JournalApplication.class, args);
    }

    // The manual Bean for MongoTransactionManager has been removed.
    // Spring Boot's JPA starter automatically configures a JpaTransactionManager for MySQL.

    /* Note: If you want a similar diagnostic runner for MySQL,
       you can use the DataSource to log the connection metadata.
    */
    @Bean
    public org.springframework.boot.CommandLineRunner dbDiagnostic(javax.sql.DataSource dataSource) {
        return args -> {
            try (java.sql.Connection connection = dataSource.getConnection()) {
                System.out.println("[db-diagnostic] Connected to: " + connection.getMetaData().getURL());
                System.out.println("[db-diagnostic] Database Product: " + connection.getMetaData().getDatabaseProductName());
            } catch (Exception e) {
                System.err.println("[db-diagnostic] Connection failed: " + e.getMessage());
            }
        };
    }
}