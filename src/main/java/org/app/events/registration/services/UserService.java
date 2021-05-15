package org.app.events.registration.services;

import org.app.events.registration.model.User;
import org.app.events.registration.exceptions.UsernameAlreadyExistsException;
import org.app.events.registration.exceptions.CouldNotWriteUsersException;
import org.apache.commons.io.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    private static List<User> users;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("config", "users.json");

    public static void loadUsersFromFile() throws IOException
    {
        if(!Files.exists(USERS_PATH))
        {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("users.json"), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
    }

    public static void addUser(String username, String firstname, String lastname, String email, String password, String role) throws UsernameAlreadyExistsException
    {
        checkUserDoesNotAlreadyExist(username);
        users.add(new User(username, firstname, lastname, email, encodePassword(username,password), role));
        persistUsers();
    }

    public static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException
    {
        for(User user : users)
        {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    public static void persistUsers()
    {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), users);
        }
        catch(IOException e)
        {
            throw new CouldNotWriteUsersException();
        }
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
