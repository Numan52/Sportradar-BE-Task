package com.example.sportradarbetask;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;


// Runs the testData.sql script in the resources folder at startup
@Component
public class DataLoader implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;

    public DataLoader(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        String sqlFilePath = "src/main/resources/testData.sql";

        try {
            Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM sport", Integer.class);
            if (count == 0) {
                String sql = new String(Files.readAllBytes(Paths.get(sqlFilePath)));
                jdbcTemplate.execute(sql);
                System.out.println("Test data loaded successfully.");
            } else {
                System.out.println("Test data was not loaded because the tables aren't empty.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load test data.");
        }
    }
}
