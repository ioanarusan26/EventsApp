package org.app.events.lists.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.app.events.event.services.EventService;

import java.io.IOException;

public class AdminToAllEvents {

    @FXML
    private Label eventNameLbl,dateLbl, descriptionLbl;
    @FXML
    private Button backBtn;

    public static int indexATE =0;

    @FXML
    public void initialize() throws IOException {
        EventService.loadEventsFromFile();
        if(EventService.events.size() >= 1) {
            eventNameLbl.setText(EventService.events.get(indexATE).getName());
            dateLbl.setText(EventService.events.get(indexATE).getDate());
            descriptionLbl.setText(EventService.events.get(indexATE).getDescription());
        }
        else
        {
            descriptionLbl.setText("NO EVENTS");
        }
    }

    @FXML
    public void changeToNext()
    {
        if(indexATE <(EventService.events.size()-1)) {
            indexATE++;
            eventNameLbl.setText(EventService.events.get(indexATE).getName());
            dateLbl.setText(EventService.events.get(indexATE).getDate());
            descriptionLbl.setText(EventService.events.get(indexATE).getDescription());
        }
    }

    @FXML
    public void deleteEvent()
    {
        EventService.events.remove(indexATE);
        EventService.persistEvents();
        eventNameLbl.setText("");
        dateLbl.setText("");
        descriptionLbl.setText("Event deleted successfully");
    }

    @FXML
    public void modifyEvent() throws IOException
    {
        if(EventService.events.size()>0) {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./modifyEvent/modifyEvent.fxml"));
            stage.setScene(new Scene(root, 400, 300));
            stage.show();
        }
    }

    @FXML
    public void showPending() throws IOException {
        if(EventService.events.get(indexATE).pendingVolunteers.size() > 0)
        {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./showPendingApplications/showPendingApplications.fxml"));
            stage.setScene(new Scene(root, 400, 300));
            stage.show();
        }
    }

    @FXML
    public void changeToPrevious()
    {
        if(indexATE >0) {
            indexATE--;
            eventNameLbl.setText(EventService.events.get(indexATE).getName());
            dateLbl.setText(EventService.events.get(indexATE).getDate());
            descriptionLbl.setText(EventService.events.get(indexATE).getDescription());
        }
    }

    @FXML
    public void changeToMainPage() throws IOException
    {
        Stage stage = (Stage)backBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./mainPage/adminDashboard.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void changeToParticipantsForEvent() throws IOException {
        if(EventService.events.size()>0) {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("participantLists/adminToParticipantsForEvent.fxml"));
            stage.setScene(new Scene(root, 400, 300));
            stage.show();
        }

    }

    @FXML
    public void changeToVolunteersForEvent() throws IOException {
        if(EventService.events.size()>0) {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("showEventVolunteers/showEventVolunteers.fxml"));
            stage.setScene(new Scene(root, 400, 300));
            stage.show();
        }
    }
}
