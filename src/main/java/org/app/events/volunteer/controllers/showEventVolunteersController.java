package org.app.events.volunteer.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.app.events.event.model.Event;
import org.app.events.event.services.EventService;
import org.app.events.lists.controllers.AdminToAllEvents;
import org.app.events.registration.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.app.events.login.controllers.LoginController.activeUser;

public class showEventVolunteersController
{
    @FXML
    private Label volunteerFirstNameLbl, volunteerLastNameLbl, emailLbl;

    public static int k;
    public ObservableList<String> list;

    @FXML
    public void initialize()
    {
        k=0;
        if(EventService.events.get(AdminToAllEvents.indexATE).volunteers.size() > 0) {
            volunteerFirstNameLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).volunteers.get(k).getFirstname());
            volunteerLastNameLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).volunteers.get(k).getLastname());
            emailLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).volunteers.get(k).getEmail());
        }
        else
            emailLbl.setText("NO VOLUNTEERS");
    }

    @FXML
    public void changeToNextVolunteer()
    {
        if(k < (EventService.events.get(AdminToAllEvents.indexATE).volunteers.size()-1)) {
            k++;
            volunteerFirstNameLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).volunteers.get(k).getFirstname());
            volunteerLastNameLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).volunteers.get(k).getLastname());
            emailLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).volunteers.get(k).getEmail());
        }
    }

    @FXML
    public void changeToPreviousVolunteer()
    {
        if(k > 0) {
            k--;
            volunteerFirstNameLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).volunteers.get(k).getFirstname());
            volunteerLastNameLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).volunteers.get(k).getLastname());
            emailLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).volunteers.get(k).getEmail());
        }
    }

    @FXML
    public TextField moveTo;

    @FXML
    public void moveVolunteer() throws IOException {
        for(Event event: EventService.events)
        {
            System.out.println(moveTo.getText());
            if((moveTo.getText()).equals(event.getName()))
            {
                event.volunteers.add(EventService.events.get(AdminToAllEvents.indexATE).volunteers.get(k));
                EventService.events.get(AdminToAllEvents.indexATE).volunteers.remove(k);
                EventService.persistEvents();
            }
        }
    }

}
