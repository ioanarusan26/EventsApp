package org.app.events.event.exceptions;

public class EventAlreadyExistsException extends Exception
{
    private String name;

    public EventAlreadyExistsException(String name)
    {
        super(String.format("An events with the name %s already exists!", name));
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
