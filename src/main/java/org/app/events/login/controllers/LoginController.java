package org.app.events.login.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.app.events.login.exceptions.PasswordIsWrongException;
import org.app.events.login.exceptions.UserDontExistsException;
import org.app.events.login.services.UserService;

import java.io.IOException;


public class LoginController {


    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Text logInMessage;
    @FXML
    private Button backBtn;


    @FXML
    public void handleLogInAction() throws UserDontExistsException, PasswordIsWrongException, IOException {
        UserService.loadUsersFromFile();
        UserService.logInUser(usernameField.getText(), passwordField.getText(),"");
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
