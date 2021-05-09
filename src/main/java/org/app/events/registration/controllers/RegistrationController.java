package org.app.events.registration.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.app.events.registration.exceptions.UsernameAlreadyExistsException;
import org.app.events.registration.services.UserService;

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
}
