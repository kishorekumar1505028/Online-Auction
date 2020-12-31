/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstgraphics;

import static firstgraphics.window1.loginwarning;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 *
 * @author User
 */
public class registerwindow {

    Scene scene;
    Client client;
    static Label warning;

    public registerwindow(Client client) {
        Label registernow = new Label();
        registernow.setText("Register Now");
        registernow.setLayoutX(300);
        registernow.setLayoutY(50);
        registernow.setTextFill(Color.web("#00ff99"));
        this.client = client;
        registerwindow clone = this;
        TextField username = new TextField();
        warning = new Label();
        warning.setLayoutX(300);
        warning.setLayoutY(380);
        warning.setTextFill(Color.web("#ffffff"));
        
        username.setLayoutX(50);
        username.setLayoutY(60);
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("password");
        username.setLayoutX(300);
        username.setLayoutY(160);
        password.setLayoutX(300);
        password.setLayoutY(250);
        Button register = new Button();
        register.setLayoutX(300);
        register.setLayoutY(340);

        register.setText("Register Now");
        register.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String clientname = username.getText();
                String clientpassword = password.getText();

                try {
                    client.register(clientname, clientpassword);
                    client.receive(clientname);
                } catch (Exception e) {
                    System.out.println(e);
                }

                }

        });
        username.setStyle("-fx-font: 20 arial; -fx-base: #00CCFF;");
        password.setStyle("-fx-font: 20 arial; -fx-base: #00CCFF;");
        register.setStyle("-fx-font: 20 arial; -fx-base: #99FF00;");
        registernow.setStyle("-fx-font: 40 arial; -fx-base: #00CCFF;");
        warning.setStyle("-fx-font: 20 arial; -fx-base: #99FF00;");
         AnchorPane container = new AnchorPane();
         container.setStyle("-fx-base: #ff8f8f;");
        //String image = window1.class.getResource("background17.jpg").toExternalForm();
        //container.setStyle("-fx-background-image: url('"+image+"');");
        container.getChildren().addAll(username, password, register, warning,registernow);
        scene = new Scene(container, 800, 600, Color.BURLYWOOD);

    }

    Scene getscene() {
        return scene;

    }

}
