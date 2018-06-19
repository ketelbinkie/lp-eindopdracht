package nl.hanze.application.session;

import nl.hanze.application.domain.User;

import java.util.HashMap;
import java.util.Map;

public class Session {
    private static Map<String, User> loggedInUsers = new HashMap<>();

    public static User getLoggedInUser(String sessionId) {
        return loggedInUsers.get(sessionId);
    }

    public static void setLoggedInUser(String session, User loggedInUser) {
        loggedInUsers.put(session, loggedInUser);
    }

    public static void logoutUser(String session){
        loggedInUsers.remove(session);
    }


}
