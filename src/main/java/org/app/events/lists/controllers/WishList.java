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
import org.app.events.login.controllers.LoginController;
import org.app.events.login.services.UserService;
import org.app.events.registration.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WishList {
    public static List<Event> wishList = new ArrayList<Event>();
    @FXML
    private Label eventNameLbl,dateLbl, descriptionLbl;
    @FXML
    private Button backBtn , previousBtn, nextBtn, doneBtn;

    private int index =0;
    @FXML
    public void initialize() throws IOException {
        EventService.loadEventsFromFile();
        if(wishList.size()>=1)
        {
            eventNameLbl.setText(wishList.get(0).getName());
            dateLbl.setText(wishList.get(0).getDate());
            descriptionLbl.setText(wishList.get(0).getDescription());
        }
        else {
            descriptionLbl.setText("NO EVENTS");
        }
    }
    @FXML
    public void changeToNext()
    {
        if(index<(wishList.size()-1))
        {
            index++;
            eventNameLbl.setText(wishList.get(index).getName());
            dateLbl.setText(wishList.get(index).getDate());
            descriptionLbl.setText(wishList.get(index).getDescription());
        }
    }

    @FXML
    public void changeToPrevious() {

        if(index>0) {
            index--;
            eventNameLbl.setText(wishList.get(index).getName());
            dateLbl.setText(wishList.get(index).getDate());
            descriptionLbl.setText(wishList.get(index).getDescription());
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
    public static void populateWishList(User _activeUser) throws IOException {
        EventService.loadEventsFromFile();
        for(Event event: EventService.events)
        {
            for(User participant : event.participants)
            {
                if((_activeUser.getUsername()).equals(participant.getUsername()))
                {
                    wishList.add(event);
                }
            }
        }
    }

    @FXML
    public void markAsDone() {
        wishList.remove(index);
        for(int i=0; i<EventService.events.size(); i++)
        {
            EventService.events.get(i).participants.removeIf(participant -> (LoginController.activeUser.getUsername()).equals(participant.getUsername()));
        }
        eventNameLbl.setText("");
        dateLbl.setText("");
        descriptionLbl.setText("We hope you liked the event! ^_^");
    }
}
