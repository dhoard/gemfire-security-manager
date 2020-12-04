package com.github.dhoard;

import java.util.Properties;
import org.apache.geode.security.SecurityManager;
import org.apache.geode.security.AuthenticationFailedException;
import org.apache.geode.security.ResourcePermission;

public class SimpleSecurityManager implements SecurityManager {

    public static final String MESSAGE = "Invalid username/password";

    public void init(Properties securityProps) {
        System.out.println("Begin init()");

        // DO NOTHING

        System.out.println("End init()");
    }

    public Object authenticate(Properties credentials) throws AuthenticationFailedException {
        System.out.println("Begin authenticate()");

        String result = null;

        String username = credentials.getProperty(USER_NAME);
        String password = credentials.getProperty(PASSWORD);

        System.out.println("username = [" + username + "]");

        // Hardcoded username/password
        if ("admin".equals(username) && "xyz1234".equals(password)) {
            result = username;
        } else {
            System.out.println(MESSAGE);
            throw new AuthenticationFailedException(MESSAGE);
        }

        System.out.println("result = [" + result + "]");
        System.out.println("End authenticate()");

        return result;
    }

    public boolean authorize(Object principal, ResourcePermission resourcePermission) {
        System.out.println("Begin authorize()");

        // Authorize all resources
        boolean result = true;

        System.out.println("result = [" + result + "]");
        System.out.println("Begin authorize()");

        return result;
    }

    public void close() {
        System.out.println("Begin close()");

        // DO NOTHING

        System.out.println("End close()");
    }
}
