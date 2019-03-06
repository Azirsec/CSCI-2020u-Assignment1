package sample;

//import the necessary files for our program
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//Main function
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
		//creates a grid pane for us to put objects onto
        GridPane pane = new GridPane();
		//set the settings for the gridpane
        pane.setHgap(5);
        pane.setVgap(5);
		//creates 4 textfield objects
        TextField Investment = new TextField();
        TextField Years = new TextField();
        TextField percent = new TextField();
        TextField tfResult = new TextField();
		//set where we want our columns to appear
        Investment.setPrefColumnCount(1);
        Years.setPrefColumnCount(2);
        tfResult.setPrefColumnCount(3);

		//set this text field to not be editable by the user
        tfResult.setEditable(false);

		//adding labels directly to our pane
        pane.add(new Label("Investment Amount: "), 0, 0);
        pane.add(Investment, 1, 0);
        pane.add(new Label("Years: "), 0, 1);
        pane.add(Years, 1, 1);
        pane.add(new Label("Annual Interest Rate: "), 0, 2);
        pane.add(percent, 1, 2);
        pane.add(new Label("Future Value: "), 0, 3);
        pane.add(tfResult, 1, 3);



        // Creates an hbox and adds a button to it
        HBox hBox = new HBox(5);
        Button btAdd = new Button("Calculate");
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(btAdd);

		//creates a borderpane and aligns our pane and hbox to it
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(hBox);
        BorderPane.setAlignment(hBox, Pos.TOP_CENTER);

        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 250, 150);
        primaryStage.setTitle("Question 2"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

		//create an action event for our calculate button
        btAdd.setOnAction(e -> {
			//our button performs calculations based on what was entered in our textfields
			//in our case, it determines the amount of interest generated
			//based on investment, interest rate, and number of years
            tfResult.setText(Math.floor(
                    Double.parseDouble(Investment.getText()) *
                    (Math.pow(1 + Double.parseDouble(percent.getText()) / (100 * 12),
                            Double.parseDouble(Years.getText()) * 12))
                    * 100) / 100 + "");
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
