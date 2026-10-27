package ru.praktikum.stellarburgers.api;

import java.util.UUID;

public class User {
    private static final String EMAIL_DOMAIN = "@example.com";
    private static final int RANDOM_PART_LENGTH = 10;

    private String email;
    private String password;
    private String name;

    public User() {
    }

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static User random() {
        return new User("user_" + randomString() + EMAIL_DOMAIN, randomString(), randomString());
    }

    private static String randomString() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, RANDOM_PART_LENGTH);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
