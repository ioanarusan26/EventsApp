package org.app.events.login.exceptions;

public class PasswordIsWrongException extends Exception{



    public PasswordIsWrongException()
    {
        super(String.format("The password is wrong"));

    }


}
