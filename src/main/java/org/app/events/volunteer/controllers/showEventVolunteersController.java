package org.app.events.volunteer.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.app.events.event.services.EventService;
import org.app.events.lists.controllers.AdminToAllEvents;

public class showEventVolunteersController
{
    @FXML
    private Label volunteerFirstNameLbl, volunteerLastNameLbl, emailLbl;

    public static int k;

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

}

