package sample;

//import the necessary files for our program
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.File;
import java.util.Scanner;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

		//create a variable for the pane height
        double paneHeight = 250;

		//create a pane object
        Pane pane = new Pane();
		//create a text object
        Text label = new Text(15, paneHeight - 15, "Filename:");
		//cretes a button
        Button graph = new Button("Graph");
		//set values for our variables
        graph.setTranslateX(450);
        graph.setTranslateY(paneHeight - 30);
		//create a textfield for a file name
        TextField file = new TextField();
		//set the values for the textfield
        file.setTranslateX(175);
        file.setTranslateY(paneHeight - 30);
        file.setScaleX(2);
        file.setAlignment(Pos.CENTER);

		//an array for the amount of times a letter shows up in the message
        int count[] = new int[26];

		//an array for the bars in our bar graph
        Rectangle[] bars = new Rectangle[26];
		//an array of text objects for the letters
        Text[] letters = new Text[26];

		//loop though 26 times
        for (int i = 0; i < 26; i++)
        {
			//sets the properties of each bar 
            bars[i] = new Rectangle(15 * i + 60, paneHeight - 80, 10, 10);
            bars[i].setStroke(Color.BLACK);
            bars[i].setFill(Color.WHITE);

			//set the properties of each character
            letters[i] = new Text(15 * i + 60, paneHeight - 50, Character.toString((char)(i+65)));
        }

		//add the bars to the pane
        pane.getChildren().addAll(bars);
		//add the letters to the pane
        pane.getChildren().addAll(letters);
		//add the label, file, and graph to the pane
        pane.getChildren().addAll(label, file, graph);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 500, paneHeight);
        primaryStage.setTitle("Question5"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage


		//actionEvent that happens when you hit the button
        graph.setOnAction(e -> {
			//a counter to find the highest amount
			//a letter shows up in the file
            int highest = 1;

			//creates a new file
            File f = new File(file.getText());
			//try block for using the file
            try (Scanner scanner = new Scanner(f)) {

				//while there is still more to read...
                while (scanner.hasNextLine())
                {
					//read the next line
                    String string = scanner.nextLine();
					//loop though the whole line...
                    for(int i = 0; i < string.length(); i++)
                    {
						//look at each character individually
                        char curr = string.charAt(i);
						//if the char isn't a space...
                        if (curr != ' ')
                        {
							//convert it to uppercase
                            curr = Character.toUpperCase(curr);

							//add 1 to the array at the alias location for our char
                            count[((int)(curr))-65]++;

							//if that alias becomes the highest number, replace the value of highest
                            if (count[(int)(curr)-65] > highest)
                            {
                                highest = count[(int)(curr)-65];
                            }
                        }
                    }
                }
				//loop through each bar
                for (int i = 0; i < 26; i++)
                {
					//set the height of each bar depending on the occurences of their respective letters
					//divide the number of occurences but the highest occurence to get a percentage
					//multiply that by 100 to get the amount of pixels the bars should becomes
					// (the highest amount will always be 100 pixels tall)
                    bars[i].setHeight(((double)count[i] / (double)highest) * 100);
					//set the where the bars will br drawn vertically based on their height
                    bars[i].setY(paneHeight - 80 - (bars[i].getHeight()));
                }
				//catch an exception thrown by the try block
            } catch (Exception e2) {
				//print an error message
                System.out.println(e2.toString());
                }

        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}