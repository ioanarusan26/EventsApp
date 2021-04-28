import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application
{
        Button register, logIn;
        Stage window;
        Scene landingScene , logInScene, registerScene;
        @Override
        public void start(Stage primaryStage) throws Exception{

            window=primaryStage;
            //landing
            Label label1 = new Label("Welcome to Events App!");

            primaryStage.setTitle("Events App");
            register = new Button("Register");
            register.setOnAction(e -> window.setScene(registerScene));

            logIn = new Button("Log In");
            logIn.setOnAction(e-> window.setScene(logInScene));

            GridPane layout1 = new GridPane();
            layout1.add(register,12, 5, 1, 1);
            layout1.add(logIn,12, 10, 1, 1);
            layout1.setHgap(10);
            layout1.setVgap(10);
            landingScene=new Scene(layout1, 300, 250);



            //scene 2 - login
            Button back = new Button("back");
            back.setOnAction(e-> window.setScene(landingScene));
            GridPane layout2 = new GridPane();
            layout2.setAlignment(Pos.CENTER);
            layout2.setHgap(10);
            layout2.setVgap(10);
            layout2.setPadding(new Insets(25, 25, 25, 25));



            Text scenetitle = new Text("Welcome");
            scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            layout2.add(scenetitle, 0, 0, 2, 1);

            Label userName = new Label("User Name:");
            layout2.add(userName, 0, 1);

            TextField userTextField = new TextField();
            layout2.add(userTextField, 1, 1);

            Label pw = new Label("Password:");
            layout2.add(pw, 0, 2);

            PasswordField pwBox = new PasswordField();
            layout2.add(pwBox, 1, 2);

            Button btn = new Button("Log in");
            HBox hbBtn = new HBox(10);
            hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtn.getChildren().add(btn);
            layout2.add(hbBtn, 1, 4);

            final Text actiontarget = new Text();
            layout2.add(actiontarget, 1, 6);
            layout2.add(back, 1, 10);

            logInScene= new Scene(layout2, 300, 250);
            window.setScene(landingScene);
            window.setTitle("Events App");
            window.show();


        }

        public static void main(String[] args) {
            launch();
        }

}


