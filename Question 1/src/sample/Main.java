package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

//main function
public class Main extends Application {

    //image variables
    Image card1;
    Image card2;
    Image card3;

    //miageview variables
    //use these to display images
    ImageView cards1 = new ImageView();
    ImageView cards2 = new ImageView();
    ImageView cards3 = new ImageView();

    @Override
    public void start(Stage primaryStage) throws Exception{

        //create random integers
        int random1 = (int)(Math.random() * 54 + 1);
        int random2 = (int)(Math.random() * 54 + 1);
        int random3 = (int)(Math.random() * 54 + 1);

        //load in a card based on the random number generated
        //the cards are images in a cards folder
        card1 = new Image("file:Cards/" + Integer.toString(random1) + ".png");
        card2 = new Image("file:Cards/" + Integer.toString(random2) + ".png");
        card3 = new Image("file:Cards/" + Integer.toString(random3) + ".png");

        //cards 1 2 adn 3 are set
        cards1.setImage(card1);
        cards1.setFitWidth(100);
        cards1.setPreserveRatio(true);

        cards2.setImage(card2);
        cards2.setFitWidth(100);
        cards2.setPreserveRatio(true);

        cards3.setImage(card3);
        cards3.setFitWidth(100);
        cards3.setPreserveRatio(true);

        //creates a group of nodes
        Group root = new Group();
        //can hold images on them
        HBox box = new HBox();
        //add all the cards to the hbox
        box.getChildren().addAll(cards1, cards2, cards3);
        //add the hbox to the group
        root.getChildren().add(box);

        //set the name of the scene to Hello World!
        primaryStage.setTitle("Hello World");
        //create a new stage with the root group the size of 300 by 275
        primaryStage.setScene(new Scene(root, 300, 275));
        //display
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
