package org.app.events.lists.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.app.events.event.model.Event;
import org.app.events.event.services.EventService;
import org.app.events.registration.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VolunteerToItsEvents {
    public static List<Event>  volunteerEvents = new ArrayList<Event>();
    @FXML
    private Label eventNameLbl,dateLbl, descriptionLbl ;
    @FXML
    private Button previousBtn, backBtn, nextBtn;

    private int index =0;

    @FXML
    public void initialize() throws IOException{
        if(volunteerEvents.size()>=1)
        {
            eventNameLbl.setText(volunteerEvents.get(0).getName());
            dateLbl.setText(volunteerEvents.get(0).getDate());
            descriptionLbl.setText(volunteerEvents.get(0).getDescription());
        }
        else {
            descriptionLbl.setText("NO EVENTS");
        }
    }
    @FXML
    public void changeToPrevious() {
        if(index>0)
        {
            index--;
            eventNameLbl.setText(volunteerEvents.get(index).getName());
            dateLbl.setText(volunteerEvents.get(index).getDate());
            descriptionLbl.setText(volunteerEvents.get(index).getDescription());
        }
    }
    @FXML
    public void changeToMainPage() throws IOException {
        Stage stage = (Stage)backBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./mainPage/volunteerDashboard.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void changeToNext() {
        if(index<(volunteerEvents.size()-1))
        {
            index++;
            eventNameLbl.setText(volunteerEvents.get(index).getName());
            dateLbl.setText(volunteerEvents.get(index).getDate());
            descriptionLbl.setText(volunteerEvents.get(index).getDescription());
        }
    }

    public static void populateVolunteerEvents(User _activeUser) throws IOException{
        EventService.loadEventsFromFile();
        for(Event event : EventService.events)
        {
            for(User volunteer: event.volunteers)
            {
                if((_activeUser.getUsername()).equals(volunteer.getUsername()))
                {
                    volunteerEvents.add(event);
                }

            }
        }
    }
}
