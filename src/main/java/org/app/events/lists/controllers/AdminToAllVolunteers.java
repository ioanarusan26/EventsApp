package org.app.events.lists.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.app.events.login.services.UserService;

import java.io.IOException;

public class AdminToAllVolunteers {
    @FXML
    private Button  previousBtn;
    @FXML
    private Button nextBtn;
    @FXML
    private Label emailLbl;
    @FXML
    private Label volunteerLastNameLbl;
    @FXML
    private Label volunteerFirstNameLbl;
    @FXML
    private Button backBtn;
    private static int i=0;
    @FXML
    public void changeToMainPage() throws IOException
    {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./mainPage/mainPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   @FXML
    public void initialize() throws IOException {
        UserService.loadUsersFromFile();
        if(UserService.users.size() >=1){
            while (!((UserService.users.get(i).getRole()).equals("Volunteer")) && i<=UserService.users.size()) {
                i++;
                System.out.println(UserService.users.get(i).getRole());
            }
           if((UserService.users.get(i).getRole()).equals("Volunteer"))
            {
                volunteerFirstNameLbl.setText(UserService.users.get(i).getFirstname());
                volunteerLastNameLbl.setText(UserService.users.get(i).getLastname());
                emailLbl.setText(UserService.users.get(i).getEmail());
            }
       }
        else {
            emailLbl.setText("NO VOLUNTEERS");
        }
   }

   @FXML
    public void changeToNext()
   {

       if(i<(UserService.users.size()-1))
       {
           i++;
           if((UserService.users.get(i).getRole()).equals("Volunteer"))
           {
               volunteerFirstNameLbl.setText(UserService.users.get(i).getFirstname());
               volunteerLastNameLbl.setText(UserService.users.get(i).getLastname());
               emailLbl.setText(UserService.users.get(i).getEmail());
           }
           else {
               while (!((UserService.users.get(i).getRole()).equals("Volunteer")) && i<=UserService.users.size()) {
                   i++;
               }

           }
       }
   }

   @FXML
    public void changeToPrevious()
   {
       if(i>0)
       {
           i--;
           if((UserService.users.get(i).getRole()).equals("Volunteer")) {
               volunteerFirstNameLbl.setText(UserService.users.get(i).getFirstname());
               volunteerLastNameLbl.setText(UserService.users.get(i).getLastname());
               emailLbl.setText(UserService.users.get(i).getEmail());
           }
           else {
               while (!((UserService.users.get(i).getRole()).equals("Volunteer")) && i>0) {
                    i--;
               }
           }
       }
   }
}
