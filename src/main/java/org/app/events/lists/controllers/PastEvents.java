package org.app.events.lists.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.app.events.event.model.Event;
import org.app.events.event.services.EventService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PastEvents {
    public static List<Event> pastEventsList = new ArrayList<Event>();
    @FXML
    private Label eventNameLbl,dateLbl, descriptionLbl;
    @FXML
    private Button backBtn , previousBtn, nextBtn, doneBtn;

    private int index =0;
    @FXML
    public void initialize() throws IOException {
        EventService.loadEventsFromFile();
        if(pastEventsList.size()>=1)
        {
            eventNameLbl.setText(pastEventsList.get(0).getName());
            dateLbl.setText(pastEventsList.get(0).getDate());
            descriptionLbl.setText(pastEventsList.get(0).getDescription());
        }
        else {
            descriptionLbl.setText("NO EVENTS");
        }
    }
    @FXML
    public void changeToNext()
    {
        if(index<(pastEventsList.size()-1))
        {
            index++;
            eventNameLbl.setText(pastEventsList.get(index).getName());
            dateLbl.setText(pastEventsList.get(index).getDate());
            descriptionLbl.setText(pastEventsList.get(index).getDescription());
        }
    }
    @FXML
    public void changeToPrevious() {
        if(index>0) {
            index--;
            eventNameLbl.setText(pastEventsList.get(index).getName());
            dateLbl.setText(pastEventsList.get(index).getDate());
            descriptionLbl.setText(pastEventsList.get(index).getDescription());
        }

    }
    @FXML
    public void changeToMainPage() throws IOException
    {
        Stage stage = (Stage)backBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./mainPage/participantDashboard.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
