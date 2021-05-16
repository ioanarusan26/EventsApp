package org.app.events.event.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.app.events.event.exceptions.CouldNotWriteEventsException;
import org.app.events.event.exceptions.EventAlreadyExistsException;
import org.app.events.event.model.Event;
import org.app.events.registration.services.FileSystemService;
import org.app.events.registration.services.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class EventService
{
    private static List<Event> events;
    private static final Path EVENTS_PATH = FileSystemService.getPathToFile("config", "events.json");

    public static void loadEventsFromFile() throws IOException
    {
        if(!Files.exists(EVENTS_PATH))
        {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("events.json"), EVENTS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        events = objectMapper.readValue(EVENTS_PATH.toFile(), new TypeReference<List<Event>>() {
        });
    }

    public static void addEvent(String name, String date, String description) throws EventAlreadyExistsException {
        checkEventDoesNotAlreadyExist(name);
        events.add(new Event(name, date, description));
        persistEvents();
    }

    public static void checkEventDoesNotAlreadyExist(String name) throws EventAlreadyExistsException
    {
        for(Event event : events)
        {
            if(Objects.equals(name, event.getName()))
            {
                throw new EventAlreadyExistsException(name);
            }
        }
    }

    public static void persistEvents()
    {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(EVENTS_PATH.toFile(), events);
        }
        catch(IOException e)
        {
            throw new CouldNotWriteEventsException();
        }
    }
}
