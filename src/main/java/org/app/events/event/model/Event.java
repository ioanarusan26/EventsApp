package org.app.events.event.model;

import org.app.events.registration.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Event
{
    private String name;
    private String date;
    private String description;
    private List<User> participants;
    private List<User> volunteers;

    public Event(){
    }

    public Event(String name, String date, String description)
    {
        this.name = name;
        this.date = date;
        this.description = description;
        participants = new ArrayList<User>();
        volunteers = new ArrayList<User>();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return name.equals(event.name) &&
                date.equals(event.date) &&
                description.equals(event.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, description);
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
