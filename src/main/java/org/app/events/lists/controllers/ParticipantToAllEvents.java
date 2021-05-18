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

import java.io.IOException;
import java.text.ParseException;

public class ParticipantToAllEvents {

    @FXML
    public Text bookedMessage;
    @FXML
    private Label eventNameLbl,dateLbl, descriptionLbl;
    @FXML
    private Button previousBtn, nextBtn, bookBtn;
    @FXML
    private Button backBtn ;

    public static int indexPTE =0;

    @FXML
    public void initialize() throws IOException, ParseException {
        EventService.loadEventsFromFile();
        if (EventService.events.size() >= 1) {
            eventNameLbl.setText(EventService.events.get(indexPTE).getName());
            dateLbl.setText(EventService.events.get(indexPTE).getDate());
            descriptionLbl.setText(EventService.events.get(indexPTE).getDescription());
        } else {
            descriptionLbl.setText("NO EVENTS");
        }
    }
    @FXML
    public void changeToNext()  {

        if(indexPTE <(EventService.events.size()-1)) {
            indexPTE++;
            eventNameLbl.setText(EventService.events.get(indexPTE).getName());
            dateLbl.setText(EventService.events.get(indexPTE).getDate());
            descriptionLbl.setText(EventService.events.get(indexPTE).getDescription());
        }
    }



    @FXML
    public void changeToPrevious() {

        if(indexPTE >0) {
            indexPTE--;
            eventNameLbl.setText(EventService.events.get(indexPTE).getName());
            dateLbl.setText(EventService.events.get(indexPTE).getDate());
            descriptionLbl.setText(EventService.events.get(indexPTE).getDescription());
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
    @FXML
    public void handleBookASeat() throws IOException {
        EventService.events.get(indexPTE).participants.add(LoginController.activeUser);
        EventService.persistEvents();
        bookedMessage.setText("You've just booked a seat at "+ EventService.events.get(indexPTE).getName());
        WishList.populateWishList(LoginController.activeUser);


    }
}
