package com.progi.progi;

import com.progi.progi.model.Scrapper;
import com.progi.progi.service.ArticleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ProgiApplication implements CommandLineRunner {

    private final Scrapper scrapper;

    // Constructor injection za Scrapper
    public ProgiApplication(Scrapper scrapper) {
        this.scrapper = scrapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProgiApplication.class, args);
    }

    @Override
    public void run(String... args) throws IOException {
        scrapper.getItems();
    }
}

