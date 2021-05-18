package org.app.events.volunteer.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.app.events.event.services.EventService;
import org.app.events.lists.controllers.AdminToAllEvents;
import org.app.events.registration.model.User;

public class showPendingApplicationController
{
    @FXML
    private Label volunteerFirstNameLbl, volunteerLastNameLbl, emailLbl;

    public static int k;

    @FXML
    public void initialize()
    {
        k=0;
        volunteerFirstNameLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).pendingVolunteers.get(k).getFirstname());
        volunteerLastNameLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).pendingVolunteers.get(k).getLastname());
        emailLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).pendingVolunteers.get(k).getEmail());
    }

    @FXML
    public void changeToNextVolunteer()
    {
        if(k < (EventService.events.get(AdminToAllEvents.indexATE).pendingVolunteers.size()-1)) {
            k++;
            volunteerFirstNameLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).pendingVolunteers.get(k).getFirstname());
            volunteerLastNameLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).pendingVolunteers.get(k).getLastname());
            emailLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).pendingVolunteers.get(k).getEmail());
        }
    }

    @FXML
    public void changeToPreviousVolunteer()
    {
        if(k > 0) {
            k--;
            volunteerFirstNameLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).pendingVolunteers.get(k).getFirstname());
            volunteerLastNameLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).pendingVolunteers.get(k).getLastname());
            emailLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).pendingVolunteers.get(k).getEmail());
        }
    }

    @FXML
    public void acceptVolunteer()
    {
        User aux = EventService.events.get(AdminToAllEvents.indexATE).pendingVolunteers.get(k);
        EventService.events.get(AdminToAllEvents.indexATE).volunteers.add(aux);
        EventService.events.get(AdminToAllEvents.indexATE).pendingVolunteers.remove(k);
        EventService.persistEvents();
        if(EventService.events.get(AdminToAllEvents.indexATE).pendingVolunteers.size()-1 >= k )
        {
            volunteerFirstNameLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).pendingVolunteers.get(k).getFirstname());
            volunteerLastNameLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).pendingVolunteers.get(k).getLastname());
            emailLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).pendingVolunteers.get(k).getEmail());
        }
        else {
            volunteerFirstNameLbl.setText("");
            volunteerLastNameLbl.setText("");
            emailLbl.setText("No pending applications");
        }
    }

    @FXML
    public void rejectVolunteer()
    {
        EventService.events.get(AdminToAllEvents.indexATE).pendingVolunteers.remove(k);
        EventService.persistEvents();
        if(EventService.events.get(AdminToAllEvents.indexATE).pendingVolunteers.size()-1 >= k )
        {
            volunteerFirstNameLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).pendingVolunteers.get(k).getFirstname());
            volunteerLastNameLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).pendingVolunteers.get(k).getLastname());
            emailLbl.setText(EventService.events.get(AdminToAllEvents.indexATE).pendingVolunteers.get(k).getEmail());
        }
        else
        {
            volunteerFirstNameLbl.setText("");
            volunteerLastNameLbl.setText("");
            emailLbl.setText("No pending applications");
        }
    }
}
