package org.app.events.volunteer.exceptions;

public class VolunteerAlreadyAppliedException extends Exception
{
    public VolunteerAlreadyAppliedException()
    {
        super("Already applied to this event!");
    }
}
