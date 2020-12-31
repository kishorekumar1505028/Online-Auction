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
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class Mybidwindow {
    Scene scene ;
    Stage stage;
    public Mybidwindow(String s,Stage stage)
    {
        this.stage =stage;
        Label header = new Label();
        header.setText("Bids You Won");
        header.setLayoutX(320);
        header.setLayoutY(50);
        header.setStyle("-fx-font: 40 arial; -fx-base: #3300ff;");
       Button show = new Button();
        show.setLayoutX(400);
        show.setLayoutY(150); 
        show.setText(s);
        show.setStyle("-fx-font: 20 arial; -fx-base: #ff99ff;");
        show.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("kaj kore");
            }
        });
        Button home = new Button ();
        home.setLayoutX(400);
        home.setLayoutY(200 + 90);
        home.setText("Go to homepage");
        home.setStyle("-fx-font: 25 arial; -fx-base: #33ffff;");
        Button back = new Button ();
        back.setLayoutX(400);
        back.setLayoutY(260 + 90);
        back.setText("View Running Bids");
        back.setStyle("-fx-font: 25 arial; -fx-base: #99FF00;");
       home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(window2.scene);
                stage.show();
            }
        });
        ;
        AnchorPane container = new AnchorPane();
        container.setStyle("-fx-base: #ff8f8f;");
        //String image = window1.class.getResource("background11.jpg").toExternalForm();
       // container.setStyle("-fx-background-image: url('"+image+"');");
        
    container.getChildren().addAll(show,home,header);
         scene = new Scene (container,820,600);
        
   }
    Scene getscene() {
        return scene;

    }
        
   
}
