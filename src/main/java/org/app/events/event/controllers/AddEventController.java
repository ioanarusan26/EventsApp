package org.app.events.event.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.app.events.event.exceptions.EventAlreadyExistsException;
import org.app.events.event.services.EventService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddEventController
{
    @FXML
    private TextField name;
    @FXML
    private DatePicker date;
    @FXML
    private TextArea description;
    @FXML
    private Button backBtn;
    @FXML
    private Text addMessage;

    public void handleAddEventAction()
    {
        String pattern = "d-MMM-yyyy";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate dp = date.getValue();
        //dateLbl.setText(dateFormatter.format(dp));

        try
        {
            EventService.addEvent(name.getText(),
                    dateFormatter.format(dp),
                    description.getText());
            addMessage.setText("Event added successfully!");
        }
        catch(EventAlreadyExistsException e)
        {
            addMessage.setText(e.getMessage());
        }
    }

    public void changeToMainPage() throws IOException
    {
        Stage stage = (Stage)backBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./mainPage/MainPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
