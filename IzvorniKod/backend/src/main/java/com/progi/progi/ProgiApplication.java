package com.progi.progi;

import com.progi.progi.model.ClosetGenerator;
import com.progi.progi.model.Scrapper;
import com.progi.progi.model.UsersGenerator;
import com.progi.progi.service.ArticleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ProgiApplication implements CommandLineRunner {

    private final Scrapper scrapper;
    private final UsersGenerator usersGenerator;
    private final ClosetGenerator closetGenerator;

    // Constructor injection za Scrapper
    public ProgiApplication(Scrapper scrapper, UsersGenerator usersGenerator, ClosetGenerator closetGenerator) {
        this.scrapper = scrapper;
        this.usersGenerator = usersGenerator;
        this.closetGenerator = closetGenerator;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProgiApplication.class, args);
    }

    @Override
    public void run(String... args) throws IOException {
        System.out.println("Adding users...");
        usersGenerator.generataeUsers();
        System.out.println("Done!");
        System.out.println("Adding articles...");
        scrapper.getItems();
        System.out.println("Done!");
        System.out.println("Filling up the closets...");
        closetGenerator.generateClosets();
        System.out.println("Done!");
        System.out.println("Database built!");
    }
}

