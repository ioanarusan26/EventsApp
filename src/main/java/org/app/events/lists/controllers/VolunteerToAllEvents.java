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

    public static int i=0;

    @FXML
    public void initialize() throws IOException, ParseException {
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
        }
    }

    @FXML
    public void changeToPrevious() throws ParseException {

        if(i>0) {
            i--;
            eventNameLbl.setText(EventService.events.get(i).getName());
            dateLbl.setText(EventService.events.get(i).getDate());
            descriptionLbl.setText(EventService.events.get(i).getDescription());
        }
    }

    @FXML
    public void applyToEvent()
    {
        EventService.events.get(i).volunteers.add(LoginController.activeUser);
        EventService.persistEvents();
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
