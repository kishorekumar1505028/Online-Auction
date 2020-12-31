/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstgraphics;

//import java.awt.Rectangle;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
//import sun.security.util.Password;

/**
 *
 * @author User
 */
public class FirstGraphics extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

      try {
        window1 scene1 = new window1(primaryStage);
        Scene scene = scene1.getscene();
        primaryStage.setTitle("Login page");
        primaryStage.setScene(scene);
        primaryStage.show();

      }catch(Exception e)
      {
          System.out.println("error in start method at main thread in firstgraphics");
          e.printStackTrace();
      }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        try {
            launch(args);
        } catch (Exception e) {
            System.out.println("error in main thread at firstgraphics");
            e.printStackTrace();
        }
    }

}
