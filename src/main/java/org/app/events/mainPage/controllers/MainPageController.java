package org.app.events.mainPage.controllers;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.stage.Stage;
import org.app.events.event.services.EventService;
import org.app.events.lists.controllers.VolunteerToAllEvents;
import org.app.events.lists.controllers.WishList;
import org.app.events.login.controllers.LoginController;
import org.app.events.registration.model.User;

import java.io.IOException;

public class MainPageController {



    @FXML
    private Button LogOutBtn, adminToAllVolunteersBtn, adminToAllEventsBtn, addEventBtn , volunteerToAllEventsBtn, participantToAllEventsBtn, participantToWishlistBtn;
    @FXML
    public void changeToLanding() throws IOException
    {
        LoginController.activeUser= new User();
        Stage stage = (Stage)LogOutBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./landing/landing.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void changeToAdminToAllVolunteers() throws IOException {
        Stage stage = (Stage)adminToAllVolunteersBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("volunteerLists/adminToAllVolunteers.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void changeToAdminToAllEvents() throws IOException {
        EventService.loadEventsFromFile();
        Stage stage = (Stage)adminToAllEventsBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("eventLists/adminToAllEvents.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void changeToAddEvent() throws IOException
    {
        EventService.loadEventsFromFile();
        Stage stage = (Stage)addEventBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("addEvent/addEvent.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void changeToVolunteertoAllEvents() throws IOException {
        EventService.loadEventsFromFile();
        Stage stage = (Stage)volunteerToAllEventsBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("eventLists/volunteerToAllEvents.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void changeToParticipantToAllEvents() throws IOException {
        EventService.loadEventsFromFile();
        Stage stage = (Stage)participantToAllEventsBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("eventLists/participantToAllEvents.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void changeWishList() throws IOException {
        EventService.loadEventsFromFile();
        Stage stage = (Stage)participantToWishlistBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("eventLists/wishList.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
