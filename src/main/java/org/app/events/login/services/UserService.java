package org.app.events.login.services;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.app.events.login.controllers.LoginController;
import org.app.events.login.exceptions.PasswordIsWrongException;
import org.app.events.login.exceptions.UserDontExistsException;
import org.app.events.registration.model.User;
import org.app.events.registration.services.FileSystemService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

public class UserService
{
    private static List<org.app.events.registration.model.User> users;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("config", "users.json");

    public static void loadUsersFromFile() throws IOException
    {
        if(!Files.exists(USERS_PATH))
        {
            FileUtils.copyURLToFile(Objects.requireNonNull(org.app.events.registration.services.UserService.class.getClassLoader().getResource("users.json")), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
    }

    public static int logInUser(String username, String password) throws UserDontExistsException, PasswordIsWrongException {
//        System.out.println("LOGIN");

       if(searchForUser(username))
       {
           if(checkPassword(username, password))
           {
               return getUserRole(username);
           }
           else throw  new PasswordIsWrongException();
       }
       else throw new UserDontExistsException(username);
    }
    public static int getUserRole(String username) throws UserDontExistsException {
        for(User user : users)
        {
            if ((user.getUsername()).equals(username)) {
                if ((user.getRole()).equals("Participant")){
                    return 1;
                }
                else if ((user.getRole()).equals("Volunteer")) {
                    return 2;
                }
            }
        }
        return 0;
    }

    public static boolean searchForUser(String username) throws UserDontExistsException {

        for (User user : users) {
            if(username.equals(user.getUsername()))
            {
                LoginController.activeUser=user;
                return  true;
            }
        }
        throw new UserDontExistsException(username);

    }
    public static boolean checkPassword(String username, String password) throws PasswordIsWrongException {
        for(User user : users)
        {
            if (Objects.equals(username, user.getUsername()))
            {
                if(Objects.equals(encodePassword(username, password), user.getPassword()))
                    return true;
                else
                    throw new PasswordIsWrongException();
            }
        }
        return false;

    }
    public static String encodePassword(String salt, String password)
    {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        return new String(hashedPassword, StandardCharsets.UTF_8).replace("\"","");
    }
    public static MessageDigest getMessageDigest()
    {
        MessageDigest md;
        try
        {
            md = MessageDigest.getInstance("SHA-512");
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new IllegalStateException("SHA-512 does not exist");
        }

        return md;
    }


}
