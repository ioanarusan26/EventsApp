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
import java.util.Objects;


public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button backBtn, loginBtn1;
    @FXML
    private Text logInMessage;


    @FXML
    public void handleLogInAction() throws UserDontExistsException, PasswordIsWrongException, IOException {

        try {
            UserService.loadUsersFromFile();

            int role = UserService.logInUser(usernameField.getText(), passwordField.getText());
            Stage stage = (Stage) loginBtn1.getScene().getWindow();
            Parent root;
            if (role == 1) //participant
            {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("./mainPage/participantDashboard.fxml"));
            } else if (role == 2) //voluntar
            {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("./mainPage/volunteerDashboard.fxml"));
            }
//        admin
            else root = FXMLLoader.load(getClass().getClassLoader().getResource("./mainPage/adminDashboard.fxml"));
//        root = FXMLLoader.load(getClass().getClassLoader().getResource("adminDashboard.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch(UserDontExistsException e)
        {
            logInMessage.setText(e.getMessage());
        }
        catch(PasswordIsWrongException e)
        {
            logInMessage.setText(e.getMessage());
        }


    }

    public void changeToLanding() throws IOException
    {
        Stage stage = (Stage)backBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./landing/landing.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
