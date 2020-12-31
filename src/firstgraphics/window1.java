/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstgraphics;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class window1 {

    Scene scene;
    boolean flag;
    Stage stage;
    //Client client;
    static Label loginwarning;

    public window1(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        // this.client = client;
        flag = false;
        loginwarning = new Label();
        loginwarning.setLayoutX(300);
        loginwarning.setLayoutY(340);
        loginwarning.setFont(Font.font("Calibri", 25));
        loginwarning.setTextFill(Color.web("#161b38"));
        Button register = new Button();
        Button login = new Button();
        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("password");
        //username.setT(Color.web("#ffffff"));
        //register.set(Color.MAROON);
        username.setStyle("-fx-font: 20 arial; -fx-base: #00CCFF;");
        password.setStyle("-fx-font: 20 arial; -fx-base: #00CCFF;");
        register.setStyle("-fx-font: 20 arial; -fx-base: #99FF00;");
        login.setStyle("-fx-font: 20 arial; -fx-base: #00CCFF;");

        register.setTextFill(Color.MAROON);
        register.setLayoutX(440);
        register.setLayoutY(330);
        login.setLayoutX(300);
        login.setLayoutY(300);
        username.setLayoutX(300);
        username.setLayoutY(160);
        password.setLayoutX(300);
        password.setLayoutY(250);
        register.setText("Don't have an Account?" + "\n" + "Register Now");
        login.setText("Login");
        Client client = new Client("12367", "cse", "127.0.0.1", 5051, stage, this);
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String clientname = username.getText();
                String clientpassword = password.getText();
                try {
                    client.login(clientname, clientpassword);
                    client.receive(clientname);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        });

        register.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                registerwindow registernow = new registerwindow(client);
                Scene scenerc = registernow.getscene();
                stage.setScene(scenerc);
                stage.show();
            }

        });

        Label header = new Label("Login to Enter");
        Label auction = new Label("Online Auction");
        header.setFont(Font.font("Verdana", 20));
        header.setLayoutX(305);
        header.setLayoutY(120);
        auction.setFont(Font.font(40));
        auction.setLayoutX(305);
        auction.setLayoutY(30);
        auction.setTextFill(Color.web("#ffffff"));
        header.setTextFill(Color.web("#ffff00"));
        AnchorPane container = new AnchorPane();
        container.setStyle("-fx-base: #ff8f8f;");
        //container.setStyle("-fx-background-color:#00ff00;");
        //String image = window1.class.getResource("background15.jpg").toExternalForm();
        //container.setStyle("-fx-background-image: url('" + image + "');");

        container.getChildren().addAll(username, password, auction, header, register, login, loginwarning);
        scene = new Scene(container, 800, 600, Color.BURLYWOOD);

    }

    public boolean getflag() {
        flag = true;
        return flag;
    }

    Scene getscene() {
        return scene;

    }

    void setwarning(String s) {
        loginwarning.setText(s);
    }

}
