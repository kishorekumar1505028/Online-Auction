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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class window2 {

    //Stage stage;
    static Scene scene;
    String username;
    String list;
    Stage stage;
    Client client;

    public window2(String username, String list, Stage stage, Client client) {
        this.username = username;
        this.list = list;
        this.stage = stage;
        this.client = client;
        System.out.println(list + 27);
        AnchorPane container = new AnchorPane();
        Label welcome = new Label("Welcome " + username);
        welcome.setTextFill(Color.web("#ffff00"));
        Button showlist = new Button("View  Running Bids");
        Button mybids = new Button("Bids you won");
        //stage.setScene(scene);
        welcome.setFont(Font.font(40));
        welcome.setLayoutX(290);
        welcome.setLayoutY(30);
        showlist.setLayoutX(305);
        showlist.setLayoutY(250);
        mybids.setLayoutX(305);
        mybids.setLayoutY(350);
        showlist.setStyle("-fx-font: 30 arial; -fx-base: #0000ff;");
        mybids.setStyle("-fx-font: 30 arial; -fx-base: #0000ff;");

        showlist.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    client.sendreq();
                    client.receiverefresh();
                } catch (Exception e) {
                    System.out.println("error showlist burron");
                }
            }

        });
        mybids.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    client.mybid();
                    client.recievemybids();
                } catch (Exception e) {
                    System.out.println("error in mubids button");
                }

            }

        });
        //String image = window1.class.getResource("background17.jpg").toExternalForm();
        container.setStyle("-fx-base: #ff8f8f;");
        //container.setStyle("-fx-background-image: url('" + image + "');");

        container.getChildren().addAll(welcome, showlist, mybids);
        scene = new Scene(container, 820, 600);

    }

    static Scene getscene() {
        return scene;

    }

    public void design() {

    }

}
