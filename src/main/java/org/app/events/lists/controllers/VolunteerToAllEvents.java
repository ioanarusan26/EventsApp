package org.app.events.lists.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.app.events.event.services.EventService;
import org.app.events.login.controllers.LoginController;
import org.app.events.registration.model.User;
import org.app.events.volunteer.exceptions.VolunteerAlreadyAppliedException;

import java.io.IOException;
import java.text.ParseException;

public class VolunteerToAllEvents {

    @FXML
    private Label eventNameLbl,dateLbl, descriptionLbl;
    @FXML
    private Button backBtn ;
    @FXML
    private Text applyMessage;

    public static int indexVTE =0;

    @FXML
    public void initialize() throws IOException, ParseException {
        indexVTE =0;
        EventService.loadEventsFromFile();
        if (EventService.events.size() >= 1) {
            eventNameLbl.setText(EventService.events.get(indexVTE).getName());
            dateLbl.setText(EventService.events.get(indexVTE).getDate());
            descriptionLbl.setText(EventService.events.get(indexVTE).getDescription());
        } else {
            descriptionLbl.setText("NO EVENTS");
        }
    }
    @FXML
    public void changeToNext()  {

        if(indexVTE <(EventService.events.size()-1)) {
            indexVTE++;
            eventNameLbl.setText(EventService.events.get(indexVTE).getName());
            dateLbl.setText(EventService.events.get(indexVTE).getDate());
            descriptionLbl.setText(EventService.events.get(indexVTE).getDescription());
            applyMessage.setText("");
        }
    }

    @FXML
    public void changeToPrevious() throws ParseException {

        if(indexVTE >0) {
            indexVTE--;
            eventNameLbl.setText(EventService.events.get(indexVTE).getName());
            dateLbl.setText(EventService.events.get(indexVTE).getDate());
            descriptionLbl.setText(EventService.events.get(indexVTE).getDescription());
            applyMessage.setText("");
        }
    }

    @FXML
    public void applyToEvent()
    {
        try {
            for (User user : EventService.events.get(indexVTE).volunteers) {
                if (LoginController.activeUser.getUsername().equals(user.getUsername())) {
                    throw new VolunteerAlreadyAppliedException();
                }
            }
            for (User user : EventService.events.get(indexVTE).pendingVolunteers) {
                if (LoginController.activeUser.getUsername().equals(user.getUsername())) {
                    throw new VolunteerAlreadyAppliedException();
                }
            }
            EventService.events.get(indexVTE).pendingVolunteers.add(LoginController.activeUser);
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
