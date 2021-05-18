package org.app.events.lists.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.app.events.event.model.Event;
import org.app.events.event.services.EventService;
import org.app.events.login.controllers.LoginController;
import org.app.events.registration.model.User;
import org.app.events.volunteer.exceptions.VolunteerAlreadyAppliedException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.*;
import java.util.Date;

public class VolunteerToAllEvents {

    @FXML
    private Label eventNameLbl,dateLbl, descriptionLbl;
    @FXML
    private Button backBtn ;
    @FXML
    private Text applyMessage;

    public static int i=0;

    @FXML
    public void initialize() throws IOException, ParseException {
        i=0;
        EventService.loadEventsFromFile();
        if (EventService.events.size() >= 1) {
            eventNameLbl.setText(EventService.events.get(i).getName());
            dateLbl.setText(EventService.events.get(i).getDate());
            descriptionLbl.setText(EventService.events.get(i).getDescription());
        } else {
            descriptionLbl.setText("NO EVENTS");
        }
    }
    @FXML
    public void changeToNext()  {

        if(i<(EventService.events.size()-1)) {
            i++;
            eventNameLbl.setText(EventService.events.get(i).getName());
            dateLbl.setText(EventService.events.get(i).getDate());
            descriptionLbl.setText(EventService.events.get(i).getDescription());
            applyMessage.setText("");
        }
    }

    @FXML
    public void changeToPrevious() throws ParseException {

        if(i>0) {
            i--;
            eventNameLbl.setText(EventService.events.get(i).getName());
            dateLbl.setText(EventService.events.get(i).getDate());
            descriptionLbl.setText(EventService.events.get(i).getDescription());
            applyMessage.setText("");
        }
    }

    @FXML
    public void applyToEvent()
    {
        try {
            for (User user : EventService.events.get(i).volunteers) {
                if (LoginController.activeUser.getUsername().equals(user.getUsername())) {
                    throw new VolunteerAlreadyAppliedException();
                }
            }
            for (User user : EventService.events.get(i).pendingVolunteers) {
                if (LoginController.activeUser.getUsername().equals(user.getUsername())) {
                    throw new VolunteerAlreadyAppliedException();
                }
            }
            EventService.events.get(i).pendingVolunteers.add(LoginController.activeUser);
            EventService.persistEvents();
            applyMessage.setText("Applied to this event!");
        }
        catch(VolunteerAlreadyAppliedException e)
        {
            applyMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void changeToMainPage() throws IOException
    {
        Stage stage = (Stage)backBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./mainPage/volunteerDashboard.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
