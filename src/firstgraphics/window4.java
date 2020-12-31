/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstgraphics;

import java.net.DatagramPacket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import static javafx.scene.text.FontPosture.REGULAR;
import javafx.stage.Stage;

public class window4 {

    static Scene scene;
    Client client;
    window3 wc;
    String update;

    String initial_price;
    String current_price;
    String matter_winner2;
    static Stage stage;
    String matter_name;
    boolean backclicked;
    static AnchorPane container;
    static Service<Void> service = null;
    static Service<Void> service1 = null;
    static Service<Void> service2 = null;

    public window4(Client client, String s, window3 wc, Stage stage, String matter_name) throws Exception {
        this.client = client;
        this.wc = wc;
        this.stage = stage;
        this.matter_name = matter_name;
        backclicked = false;
        //this.scene 
        update = new String();
        initial_price = new String();
        current_price = new String();
        String matter_winner = new String(s.substring(s.indexOf('{') + 1, s.indexOf('}')));
        TextField matwinner = new TextField();
        TextField matiprice = new TextField();
        TextField matcprice = new TextField();
        TextField remtime = new TextField();
        TextField endtime = new TextField();
        TextField setprize = new TextField();
        TextField show_prize = new TextField();
        Label matname = new Label();
        Button Set = new Button();
        Button refresh = new Button();
        Button back = new Button();

        back.setLayoutX(100);
        back.setLayoutY(400 + 90);
        remtime.setLayoutX(100);
        remtime.setLayoutY(130);
        endtime.setLayoutX(100);
        endtime.setLayoutY(170);
        matwinner.setLayoutX(500);
        matwinner.setLayoutY(130);
        Set.setLayoutX(300);
        Set.setLayoutY(380);
        setprize.setLayoutX(300);
        setprize.setLayoutY(340);
        refresh.setLayoutX(300);
        refresh.setLayoutY(430);
        matiprice.setLayoutX(300);
        matiprice.setLayoutY(200 + 50);
        matcprice.setLayoutX(300);
        matcprice.setLayoutY(295);
        show_prize.setLayoutX(300);
        show_prize.setLayoutY(200 + 50);
        matname.setLayoutX(340);
        matname.setLayoutY(50);
        matwinner.setEditable(false);
        remtime.setEditable(false);
        endtime.setEditable(false);
        matiprice.setEditable(false);
        matcprice.setEditable(false);
        refresh.setStyle("-fx-font:20  arial; -fx-base: #3399ff;");
        Set.setStyle("-fx-font:20  arial; -fx-base: #00ffcc;");
        matwinner.setStyle("-fx-font: 20 arial; -fx-base: #0000cc;");
        matname.setStyle("-fx-font: 40 arial; -fx-base: #cc33ff;");
        remtime.setStyle("-fx-font: 20 arial; -fx-base: #cc33ff;");
        endtime.setStyle("-fx-font: 20 arial; -fx-base: #cc33ff;");
        setprize.setStyle("-fx-font:20  arial; -fx-base: #cc33ff;");
        refresh.setStyle("-fx-font:20  arial; -fx-base: #3399ff;");
        back.setStyle("-fx-font:20  arial; -fx-base: #3399ff;");
        show_prize.setStyle("-fx-font:8  arial; -fx-base: #3399ff;");
        matiprice.setStyle("-fx-font: 20 arial; -fx-base: #cc33ff;");
        matcprice.setStyle("-fx-font: 20 arial; -fx-base: #cc33ff;");
        setprize.setPromptText("Set your price");
        back.setText("back");
        Set.setText("SET");
        refresh.setText("Refresh");
        matwinner.setText("Current Bid winner: " + matter_winner);
        matiprice.setMinSize(90, 40);
        matcprice.setMinSize(300, 40);
        matwinner.setMinSize(300, 40);
        System.out.println("original:" + s);
        String firstpart = s.substring(0, s.indexOf("matter name"));
        String mattername = s.substring(s.indexOf("matter name") + 11, s.indexOf("matter winner"));
        matname.setText(mattername);
        matname.setFont(Font.font(50));
        //matname.setFont(Font.font(Times New Roman, FontPosture.REGULAR, 0));
        String endtimetxt = s.substring(s.indexOf(';') + 1, s.length());
        System.out.println("p1:" + firstpart + "p2:" + endtimetxt);//+"final:"+finalendtime);
        final long endtimelong = Long.parseLong(endtimetxt);
        System.out.println(endtimelong);
        long hh = (TimeUnit.MILLISECONDS.toHours(endtimelong) + 6) % 24;
        long mm = TimeUnit.MILLISECONDS.toMinutes(endtimelong) % 60;
        long ss = TimeUnit.MILLISECONDS.toSeconds(endtimelong) % 60;
        String finalendtime = Long.toString(hh) + " : " + Long.toString(mm) + " : " + Long.toString(ss);
        System.out.println("p1:" + firstpart + "p2:" + endtimetxt + "final:" + finalendtime);
        //show_prize.setText(firstpart);
        endtime.setText("Endtime:" + finalendtime);
        matiprice.setText(initial_price);
        matcprice.setText(current_price);
        matwinner.setText(matter_winner2);

        service = new Service<Void>()  {
            @Override
            protected Task<Void> createTask() {

                return new Task<Void>() {

                    @Override
                     protected Void call() throws Exception {
                        //String prev = firstpart;
                        while (!backclicked) {
                            try {
                                System.out.println(backclicked + " Printing time...");
                                Calendar cal = Calendar.getInstance();
                                long current_time = cal.getTimeInMillis();
                                long remaining_time = endtimelong - current_time;
                                System.out.println(remaining_time + " " + current_time + " " + endtimelong);
                                if (remaining_time < 0) {
                                    remtime.setText("Time Over");
                                    Set.setVisible(false);
                                    setprize.setVisible(false);
                                    break;
                                }
                                long chh = (TimeUnit.MILLISECONDS.toHours(remaining_time)) % 24;
                                long cmm = TimeUnit.MILLISECONDS.toMinutes(remaining_time) % 60;
                                long css = TimeUnit.MILLISECONDS.toSeconds(remaining_time) % 60;

                                String cfinalendtime = Long.toString(chh) + " : " + Long.toString(cmm) + " : " + Long.toString(css);
                                remtime.setText("Remaining:" + cfinalendtime);

                                //show_prize.setText(snew);
                            } catch (Exception e) {
                                System.out.println("exception in first part of thread");
                            }
                            try {
                                client.send(matter_name);

                                update = client.receive5(wc, matter_name);
                                //snew = update.substring(0, update.indexOf("matter name"));
                                initial_price = update.substring(0, update.indexOf("\nCurrent"));
                                current_price = update.substring(update.indexOf("\nCurrent"), update.indexOf("matter name"));
                                matter_winner2 = "Current Bid Winner: " + update.substring(update.indexOf('{') + 1, update.indexOf('}'));
                                //System.out.println("Updated price " + snew);
                                matiprice.setText(initial_price);
                                matcprice.setText(current_price);
                                matwinner.setText(matter_winner2);
                                System.out.println("{" + initial_price + "}" + "{" + current_price + "}" + "{" + matter_winner2 + "}");

                                Thread.sleep(1000);
                            } catch (Exception e) {
                                System.out.println("error in thread");
                                e.printStackTrace();
                            }
                        }
                        return null;
                    }
                };
            }

            @Override
            protected void cancelled() {
                super.cancelled();
            }
        };
        Set.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String matter_prize = setprize.getText();
                System.out.println(matter_prize + matter_name);
                try {
                    client.sendprize(matter_prize + "&" + matter_name + "{" + client.username);
                    update = client.receive5(wc, matter_name);
                    //snew = update.substring(0, update.indexOf("matter name"));
                    initial_price = update.substring(0, update.indexOf("\nCurrent"));
                    current_price = update.substring(update.indexOf("\nCurrent"), update.indexOf("matter name"));
                    matter_winner2 = "Current Bid Winner: " + update.substring(update.indexOf('{') + 1, update.indexOf('}'));
                    //System.out.println("Updated price " + snew);
                    matiprice.setText(initial_price);
                    matcprice.setText(current_price);
                    matwinner.setText(matter_winner2);

                    //client.receive3(wc, matter_name);
                } catch (Exception e) {
                    System.out.println("error");
                    e.printStackTrace();
                }
            }
        });

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                backclicked = true;
                service.getException();
                stage.setScene(wc.getscene());
                stage.show();
            }
        });
        Label advice = new Label();
        advice.setText("Please Refresh The Page" + "\n" + "Before Setting Price");
        advice.setLayoutX(300);
        advice.setLayoutY(500);
        advice.setStyle("-fx-font: 30 arial; -fx-base: #000099;");
        container = new AnchorPane();
        //String image = window1.class.getResource("background13.jpg").toExternalForm();
        //container.setStyle("-fx-background-image: url('" + image + "');");
        container.setStyle("-fx-base: #ff8f8f;");
        container.getChildren().addAll(matname, matiprice, matcprice, back, setprize, Set, remtime, endtime, matwinner);
        scene = new Scene(container, 820, 600);

    }

    Scene getscene() {
        return scene;

    }

    void refreshing() {
        try {
            client.send(matter_name);
            client.receive3(wc, matter_name);

        } catch (Exception e) {
            System.out.println("error");
        }
    }
}
