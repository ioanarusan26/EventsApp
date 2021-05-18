package org.app.events.lists.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.app.events.event.services.EventService;
import org.app.events.registration.model.User;
import org.app.events.registration.services.UserService;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AdminToParticipantsForEvent {

    @FXML
    public Label participantFirstNameLbl, participantLastNameLbl, emailLbl;
    @FXML
    public Button previousBtn, nextBtn;

    public static int indexATPE =0;
    public static int indexP =0;
    public static List<User> participantsForEvent = new ArrayList<User>();

    public void populateParticipantsForEventList() throws IOException {
        EventService.loadEventsFromFile();

        if(EventService.events.size()>=1)
        {
            if(EventService.events.get(AdminToAllEvents.indexATE).participants.size()>=1)
            {
                participantsForEvent.addAll(EventService.events.get(AdminToAllEvents.indexATE).participants);
            }
        }
        System.out.println(participantsForEvent);

    }
    @FXML
    public void initialize() throws IOException
    {
        EventService.loadEventsFromFile();
        populateParticipantsForEventList();
        if(participantsForEvent.size()>=1)
        {
            participantFirstNameLbl.setText(participantsForEvent.get(0).getFirstname());
            participantLastNameLbl.setText(participantsForEvent.get(0).getLastname());
            emailLbl.setText(participantsForEvent.get(0).getEmail());
        }
        else {
            emailLbl.setText("NO PARTICIPANTS");
        }
    }

    @FXML
    public void changeToNext()
    {
        if(indexATPE<(participantsForEvent.size()-1))
        {
            indexP++;
            participantFirstNameLbl.setText(participantsForEvent.get(indexP).getFirstname());
            participantLastNameLbl.setText(participantsForEvent.get(indexP).getLastname());
            emailLbl.setText(participantsForEvent.get(indexP).getEmail());
        }
    }

    @FXML
    public void changeToPrevious()
    {
        if(indexP>0)
        {
            indexP--;
            participantFirstNameLbl.setText(participantsForEvent.get(indexP).getFirstname());
            participantLastNameLbl.setText(participantsForEvent.get(indexP).getLastname());
            emailLbl.setText(participantsForEvent.get(indexP).getEmail());
        }
    }
}
