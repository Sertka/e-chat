package ru.stk.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.geekbrains.data.User;

import java.util.ArrayList;
import java.util.HashMap;

public class AuthController {
    private static final Logger logger = LogManager.getLogger(ru.geekbrains.core.AuthController.class);

    HashMap<String, User> users = new HashMap<>();

    public void init() {
        for (User user : receiveUsers()) {
            users.put(user.getLogin(), user);
        }
    }

    public String getNickname(String login, String password) {
        User user = users.get(login);
        if (user != null && user.isPasswordCorrect(password)) {
            logger.info("User " + login + " authorised");
            return user.getNickname();
        }
        logger.debug("Authorisation for user " + login + " failed");
        return null;
    }

    private ArrayList<User> receiveUsers() {
        logger.info("Users received from DB");
        return SQLite.getUsers();
    }
}
