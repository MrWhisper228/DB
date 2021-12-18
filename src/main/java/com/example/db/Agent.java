package com.example.db;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class Agent {
    private int agId;
    private String login;
    private String password;
    private LocalDateTime registerDate;

    public Agent(String login, String password, LocalDateTime registerDate) {
        this.setLogin(login);
        this.setPassword(password);
        this.setRegisterDate(registerDate);
    }

    public Agent() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String password1) {
        password1 = password1.strip();
        char[] chars;
        try {
            chars = password1.toCharArray();
        } catch (Exception e) {
            return false;
        }
        boolean hasDigit = false;
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasAnother = false;
        for (char aChar : chars) {
            if (Character.isDigit(aChar)) {
                hasDigit = true;
            } else if (Character.isLowerCase(aChar)) {
                hasLowerCase = true;
            } else if (Character.isUpperCase(aChar)) {
                hasUpperCase = true;
            } else if (!Character.isDigit(aChar) && !Character.isAlphabetic(aChar)) {
                hasAnother = true;
            }

        }
        if (hasLowerCase&& hasDigit && hasAnother && hasUpperCase) {
            this.password = password1;
            return true;
        } else return false;
    }

    public int getAgId() {
        return agId;
    }

    public void setAgId(int agId) {
        this.agId = agId;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = Period.between(LocalDate.from(registerDate), LocalDate.now()).isNegative() ? LocalDateTime.now() : registerDate;
    }
}
