/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxrectangle;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author shubh
 */
public class FxRectangle extends Application {
    
    
    
    double orgSceneY;//Used to help keep up with change in mouse position
    double orgrecY = 200;
    double orgrecheight = 50;
    @Override
    public void start(Stage primaryStage)
    {
        Text text = new Text();      
      
      //Setting the text to be added. 
      text.setText("Enlarge the rectangle by using the circle of its top right"); 
       
      //setting the position of the text 
      text.setX(25); 
      text.setY(25); 
        double RATIO = .5;//The ration of height to width is 1/2

        Rectangle rectangle = new Rectangle(100, orgrecheight);
        rectangle.setY(orgrecY);
        
        rectangle.setX(50);
        rectangle.setFill(Color.AQUAMARINE);
        

        //Circles will be used to do the event handling/movements
        
        Circle topAnchor = new Circle(0, 0, 5);
        
        topAnchor.centerXProperty().bind(rectangle.xProperty().add(rectangle.widthProperty()));
        topAnchor.centerYProperty().bind(rectangle.yProperty());
        
        
        
        topAnchor.setOnMouseDragEntered((event) -> {
            topAnchor.getScene().setCursor(Cursor.MOVE);
        });
        topAnchor.setOnMousePressed((event) -> {
            orgSceneY = event.getSceneY();//store current mouse position
        });
        topAnchor.setOnMouseDragged((event) -> {
            
            double offSetY = event.getSceneY() - orgSceneY;
            if(event.getSceneY()< (orgrecheight + orgrecY))
            {
            rectangle.setY(event.getSceneY());//move rectangle top side with mouse
            rectangle.setHeight(rectangle.getHeight() - offSetY);//Change rectangle's height with movement of mouse
            rectangle.setWidth(rectangle.getHeight() * (1 / RATIO));//Change the width so that it meets the ratio requirements
            orgSceneY = event.getSceneY();
            }
            
            
            
            
            
        });
        topAnchor.setOnMouseExited((event) -> {
            topAnchor.getScene().setCursor(null);
        });

        Pane root = new Pane();
        root.getChildren().addAll(rectangle, topAnchor,text);

        Scene scene = new Scene(root, 600, 400,Color.BURLYWOOD);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
