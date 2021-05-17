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

import java.io.IOException;

public class AdminToAllEvents {

    @FXML
    private Label eventNameLbl,dateLbl, descriptionLbl;
    @FXML
    private Button previousBtn, nextBtn;
    @FXML
    private Button backBtn ;

    private static int i=0;

    @FXML
    public void initialize() throws IOException {
        EventService.loadEventsFromFile();
        if(EventService.events.size() >= 1) {
            eventNameLbl.setText(EventService.events.get(0).getName());
            dateLbl.setText(EventService.events.get(0).getDate());
            descriptionLbl.setText(EventService.events.get(0).getDescription());
        }
        else
        {
            descriptionLbl.setText("NO EVENTS");
        }
    }

    @FXML
    public void changeToNext()
    {
        if(i<(EventService.events.size()-1)) {
            i++;
            eventNameLbl.setText(EventService.events.get(i).getName());
            dateLbl.setText(EventService.events.get(i).getDate());
            descriptionLbl.setText(EventService.events.get(i).getDescription());
        }
    }

    @FXML
    public void deleteEvent()
    {
        EventService.events.remove(i);
        EventService.persistEvents();
        eventNameLbl.setText("");
        dateLbl.setText("");
        descriptionLbl.setText("Event deleted successfully");
    }

    @FXML
    public void changeToPrevious()
    {
        if(i>0) {
            i--;
            eventNameLbl.setText(EventService.events.get(i).getName());
            dateLbl.setText(EventService.events.get(i).getDate());
            descriptionLbl.setText(EventService.events.get(i).getDescription());
        }
    }

    @FXML
    public void changeToMainPage() throws IOException
    {
        Stage stage = (Stage)backBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./mainPage/mainPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
