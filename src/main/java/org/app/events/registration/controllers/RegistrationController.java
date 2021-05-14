package org.app.events.registration.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.app.events.registration.exceptions.UsernameAlreadyExistsException;
import org.app.events.registration.services.UserService;

import java.io.IOException;

public class RegistrationController {

    @FXML
    private Text registrationMessage;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ChoiceBox role;
    @FXML
    private Button backBtn;

    @FXML
    public void initialize()
    {
        role.getItems().addAll("Participant", "Volunteer");
    }

    @FXML
    public void handleRegisterAction()
    {
        try
        {
            UserService.addUser(usernameField.getText(),
                    firstnameField.getText(),
                    lastnameField.getText(),
                    emailField.getText(),
                    passwordField.getText(),
                    (String) role.getValue());
            registrationMessage.setText("Account created successfully!");
        }
        catch(UsernameAlreadyExistsException e)
        {
            registrationMessage.setText(e.getMessage());
        }
    }

    public void changeToLanding() throws IOException
    {
        Stage stage = (Stage)backBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("landing.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
