package org.app.events.login.exceptions;

public class UserDontExistsException extends Exception
{
    private String username;

    public UserDontExistsException(String username)
    {
        super(String.format("An account with this username %s doesn't exists!", username));
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
}
