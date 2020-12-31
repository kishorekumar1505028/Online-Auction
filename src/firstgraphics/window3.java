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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class window3 {

    String list;
   static  Scene scene;
    Client client;
    Stage stage;

    public window3(String list, Client client,Stage stage) {
        window3 clone = this;
        AnchorPane container = new AnchorPane();
        this.list = list;
        this.client = client;
        this.stage = stage;
        Label header = new Label();
        header.setText("Running Bids");
        header.setStyle("-fx-font: 40 arial; -fx-base: #99FF00;");
        header.setLayoutX(300);
        header.setLayoutY(10);
        
        int i = 0;
        char c = '\n';
        int previous_idx = 0;
        System.out.println(list + 29);
        //String[] n =new String [2];

        int j = 0, num = 0;
        for (i = 0; i < list.length(); i++) {
            if (list.charAt(i) == '\n') {
                num++;
            }
        }
        System.out.println("number of buttons: "+num);
        Button refresh = new Button ();
        refresh.setLayoutX(60);
        refresh.setLayoutY(10);
        refresh.setText("Refresh");
        refresh.setStyle("-fx-font: 22 arial; -fx-base: #99FF00;");
        Button[] show = new Button[num];
        Button btn = new Button();
        int sorter=0;
        int add=0;
        for (int p = 0; p < num; p++) {
            show[p] = new Button();
            show[p].setStyle("-fx-font: 20 arial; -fx-base: #99FF00;");
        }
        try {
            for (i = 0; i < list.length(); i++) {
                System.out.println(list.charAt(i));
                if (list.charAt(i) == '\n') {
                    System.out.println(j);
                    System.out.println("dhukse");
                    System.out.println(list.charAt(previous_idx) + " " + 2 + " " + list.charAt(i));
                    String n = list.substring(previous_idx, i);
                    System.out.println(2 + n + 3);
                    show[j].setText(n);
                    show[j].setId(n);
                    if (80+sorter*40>=200){
                        add+=80;
                        sorter =0;
                    }
                    show[j].setLayoutX(200+add);
                    show[j].setLayoutY(80 + sorter *40);
                    previous_idx = i + 1;
                    j++;
                    sorter++;

                }
            }
        } catch (Exception e) {
            System.out.println("error in window3");
            e.printStackTrace();
        }

        //     show.setText(list);
        System.out.println(j);
        int k;
        for (k = 0; k < num; k++) {
            show[k].setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Button x = (Button) event.getSource();
                    String name = x.getText();
                    System.out.println(name);
                    System.out.println("ok 2");
                    try {
                        client.send(name);
                        client.receive3(clone,name);
                    } catch (Exception e) {
                        System.out.println("error");
                    }
                }

            });
        }
       
        scene = new Scene(container, 820, 600);
        refresh.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    //String text1= show[k].getText();
                    try {
                       client.sendreq();
                        client.receiverefresh();
                    } catch (Exception e) {
                        System.out.println("error in window3 refresh button");
                    }
                }

            });
        Button back = new Button ();
        back.setLayoutX(700);
        back.setLayoutY(10);
        back.setText("back");
        back.setStyle("-fx-font: 25 arial; -fx-base: #99FF00;");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(window2.scene);
                stage.show();
            }
        });
        container.setStyle("-fx-base: #ff8f8f;");
        //String image = window1.class.getResource("background11.jpg").toExternalForm();
        //container.setStyle("-fx-background-image: url('"+image+"');");
        
         container.getChildren().addAll(refresh,back,header);
        for (i=0;i<num;i++){
            System.out.println(i+1);
        container.getChildren().addAll(show[i]);
        }
    }

    Scene getscene() {
        return scene;

    }

}
