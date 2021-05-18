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
        volunteerFirstNameLbl.setText(EventService.events.get(AdminToAllEvents.i).pendingVolunteers.get(k).getFirstname());
        volunteerLastNameLbl.setText(EventService.events.get(AdminToAllEvents.i).pendingVolunteers.get(k).getLastname());
        emailLbl.setText(EventService.events.get(AdminToAllEvents.i).pendingVolunteers.get(k).getEmail());
    }

    @FXML
    public void changeToNextVolunteer()
    {
        if(k < (EventService.events.get(AdminToAllEvents.i).pendingVolunteers.size()-1)) {
            k++;
            volunteerFirstNameLbl.setText(EventService.events.get(AdminToAllEvents.i).pendingVolunteers.get(k).getFirstname());
            volunteerLastNameLbl.setText(EventService.events.get(AdminToAllEvents.i).pendingVolunteers.get(k).getLastname());
            emailLbl.setText(EventService.events.get(AdminToAllEvents.i).pendingVolunteers.get(k).getEmail());
        }
    }

    @FXML
    public void changeToPreviousVolunteer()
    {
        if(k > 0) {
            k--;
            volunteerFirstNameLbl.setText(EventService.events.get(AdminToAllEvents.i).pendingVolunteers.get(k).getFirstname());
            volunteerLastNameLbl.setText(EventService.events.get(AdminToAllEvents.i).pendingVolunteers.get(k).getLastname());
            emailLbl.setText(EventService.events.get(AdminToAllEvents.i).pendingVolunteers.get(k).getEmail());
        }
    }

    @FXML
    public void acceptVolunteer()
    {
        User aux = EventService.events.get(AdminToAllEvents.i).pendingVolunteers.get(k);
        EventService.events.get(AdminToAllEvents.i).volunteers.add(aux);
        EventService.events.get(AdminToAllEvents.i).pendingVolunteers.remove(k);
        EventService.persistEvents();
        emailLbl.setText("Accepted volunteer!");
    }

    @FXML
    public void rejectVolunteer()
    {
        EventService.events.get(AdminToAllEvents.i).pendingVolunteers.remove(k);
        EventService.persistEvents();
        emailLbl.setText("Rejected volunteer!");
    }
}
