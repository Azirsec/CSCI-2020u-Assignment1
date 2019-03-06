package sample;

//import the necessary files for our program
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//main function
public class Main extends Application {

	//creating float variables and an int variables
	//these will be used later for our circles
    float c1X, c1Y, c2X, c2Y, c3X, c3Y;
    int radius = 100;

	//creates the big circle in the middle
    Circle circle = new Circle(150, 137, 100);
	//create the pane that we draw our circles on
    Pane pane = new Pane();
	//create the 3 small red circles
    Circle c1 = new Circle(50, 137, 5);
    Circle c2 = new Circle(250, 137, 5);
    Circle c3 = new Circle(150, 37, 5);

	//creates the 3 lines that are drawn to make the triangle
    Line line1 = new Line ();
    Line line2 = new Line ();
    Line line3 = new Line ();

	//creates double variables
    double originalX, originalY;
    double translateX, translateY;
    double positionX, positionY;

	//creates the angle variables
    int angle1 = 33;
    int angle2 = 22;
    int angle3 = 11;

	//creates the text boxes that will display the angles of the triangle
    Text num1 = new Text(100, 50, Integer.toString(angle1));
    Text num2 = new Text(110, 50, Integer.toString(angle2));
    Text num3 = new Text(130, 50, Integer.toString(angle3));

    @Override
    public void start(Stage primaryStage) throws Exception{
		
        giveCircleColours();
        giveRandomPositions();

        c1.setOnMouseDragged(mouseDrag1);

        c2.setOnMouseDragged(mouseDrag2);

        c3.setOnMouseDragged(mouseDrag3);

        pane.getChildren().addAll(circle, line1, line2, line3, c1, c2, c3, num1, num2, num3);

        primaryStage.setTitle("Question 3");
        primaryStage.setScene(new Scene(pane, 300, 275));
        primaryStage.show();

    }

	//this function will give the circles their colours
    void giveCircleColours()
    {
		//the big circle is white with a black outline
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);

		//teh 3 small circles are red with a black outline
        c1.setStroke(Color.BLACK);
        c1.setFill(Color.RED);

        c2.setStroke(Color.BLACK);
        c2.setFill(Color.RED);

        c3.setStroke(Color.BLACK);
        c3.setFill(Color.RED);
    }

	//calles at the start of the program
	//this function gives the 3 red circles a random position on the bigger one
    void giveRandomPositions()
    {
		//create a pseudo mouse position
        int randomX = (int)(Math.random() * 1000 - 500);
        int randomY = (int)(Math.random() * 1000 - 500);
        double mousePositionX, mousePositionY;
        mousePositionX = randomX;
        mousePositionY = randomY;
		//calls the changeCirclePosition function to move the first circle
        changeCirclePosition(c1, mousePositionX, mousePositionY, line1, line3, num1);

		//create another pseudo mouse position
        randomX = (int)(Math.random() * 1000 - 500);
        randomY = (int)(Math.random() * 1000 - 500);
        mousePositionX = randomX;
        mousePositionY = randomY;
		//calls the changeCirclePosition function to move the second circle
        changeCirclePosition(c2, mousePositionX, mousePositionY, line2, line1, num2);

		//creates another pseudo mouse position
        randomX = (int)(Math.random() * 1000 - 500);
        randomY = (int)(Math.random() * 1000 - 500);
        mousePositionX = randomX;
        mousePositionY = randomY;
		//calls the changeCirclePosition function to move the last circle
        changeCirclePosition(c3, mousePositionX, mousePositionY, line3, line2, num3);
    }

	//when the mouse drags the first red circle around
    EventHandler<MouseEvent> mouseDrag1 = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
			//get the current mouse position on the screen
            double mousePositionX, mousePositionY;
            mousePositionX = event.getSceneX();
            mousePositionY = event.getSceneY();
			//calls the changeCirclePosition function to move the first circle
            changeCirclePosition(c1, mousePositionX, mousePositionY, line1, line3, num1);
        }
    };

    EventHandler<MouseEvent> mouseDrag2 = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
			//get the current mouse position on the screen
            double mousePositionX, mousePositionY;
            mousePositionX = event.getSceneX();
            mousePositionY = event.getSceneY();
			//calls the changeCirclePosition function to move the second circle
            changeCirclePosition(c2, mousePositionX, mousePositionY, line2, line1, num2);
        }
    };

    EventHandler<MouseEvent> mouseDrag3 = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
			//get the current mouse position on the screen
            double mousePositionX, mousePositionY;
            mousePositionX = event.getSceneX();
            mousePositionY = event.getSceneY();
			//calls the changeCirclePosition function to move the last circle
            changeCirclePosition(c3, mousePositionX, mousePositionY, line3, line2, num3);
        }
    };

	//changeCirclePosition function
    void changeCirclePosition(Circle c, double mouseX, double mouseY, Line l1, Line l2, Text t)
    {
		//creates a Vec2
        double[] diff = new double[2];

		//finds the difference between the mouse position and the center of the large circle
        diff[0] = mouseX - circle.getCenterX();
        diff[1] = mouseY - circle.getCenterY();

		//find the length/magnitude of the difference vector
		//C^2 = A^2 + B^2
        double magnitude = Math.sqrt(diff[0] * diff[0] + diff[1] * diff[1]);

		//normalize the difference vector by dividing the elements by the length/magnitude of that smae vector
		//this vector allows us to get the direction of the vector
        diff[0] /= magnitude;
        diff[1] /= magnitude;

		//multiply the directional vector by the radius of the large circle
		//doing that allows us to find a point directly on the edge of the circle
		//add the product to the center of teh big circle to have a point directly
		//on the edge of the circle
        double circlePositionX = diff[0] * radius + circle.getCenterX();
        double circlePositionY = diff[1] * radius + circle.getCenterY();

		//set the center of the given circle (the one provided as an argument) 
        c.setCenterX(circlePositionX);
        c.setCenterY(circlePositionY);

		//move the start end end points of the given lines to the new circle position
        l1.setStartX(circlePositionX);
        l1.setStartY(circlePositionY);
        l2.setEndX(circlePositionX);
        l2.setEndY(circlePositionY);

		//multiply the directional vector by the radius of the large circle * a constant smaller than 1
		//doing that allows us to find a point slightly inwards of the circle
		//add the product to the center of teh big circle to have a point inside the circle
        double numPosX = diff[0] * radius * 0.75 + circle.getCenterX();
        double numPosY = diff[1] * radius * 0.75 + circle.getCenterY();

		//set the angle text on the calculated points
        t.setX(numPosX);
        t.setY(numPosY);

		//this following section is used to calculate the angles of the lines
		//connected to the small red circles
		
		//we store the start points of the first line as a Vec2
        diff[0] = line1.getStartX();
        diff[1] = line1.getStartY();

		//then subtract the end to give us the line as a Vec2
        diff[0] -= line1.getEndX();
        diff[1] -= line1.getEndY();

		//calculate the length/magnitude of the first line
		//C^2 = A^2 + B^2
        double mag1 = Math.sqrt(diff[0] * diff[0] + diff[1] * diff[1]);

		//we store the start points of the second line as a Vec2
        diff[0] = line2.getStartX();
        diff[1] = line2.getStartY();

		//then subtract the end to give us the line as a Vec2
        diff[0] -= line2.getEndX();
        diff[1] -= line2.getEndY();

		//calculate the length/magnitude of the second line
		//C^2 = A^2 + B^2
        double mag2 = Math.sqrt(diff[0] * diff[0] + diff[1] * diff[1]);

		//we store the start points of the last line as a Vec2
        diff[0] = line3.getStartX();
        diff[1] = line3.getStartY();

		//then subtract the end to give us the line as a Vec2
        diff[0] -= line3.getEndX();
        diff[1] -= line3.getEndY();

		//calculate the length/magnitude of the last line
		//C^2 = A^2 + B^2
        double mag3 = Math.sqrt(diff[0] * diff[0] + diff[1] * diff[1]);

		//creates 3 ints to store the angles to display later
        int intAngle1, intAngle2, intAngle3;

		//calculate the angle A (in Radians)
		//cosine law
		//A = acos((a * a - b * b - c * c) / (-2 * b * c)
        double angle1 = Math.acos(((mag2 * mag2) - (mag1 * mag1) - (mag3 * mag3)) / (-2 * mag1 * mag3));
		//convert the angle from radians to degrees
        angle1  = angle1 * (180 / Math.PI);
		//convert the angle from a double to an int
        intAngle1 = (int)angle1;

		//calculate the angle B (in Radians)
		//cosine law
		//B = acos((b * b - a * a - c * c) / (-2 * a * c)
        double angle2 = Math.acos(((mag1 * mag1) - (mag2 * mag2) - (mag3 * mag3)) / (-2 * mag2 * mag3));
		//convert the angle from radians to degrees
        angle2  = angle2 * (180 / Math.PI);
		//convert the angle from a double to an int
        intAngle2 = (int)angle2;

		//calculate the angle C (in Radians)
		//cosine law
		//C = acos((c * c - b * b - a * a) / (-2 * a * b)
        double angle3 = Math.acos(((mag3 * mag3) - (mag1 * mag1) - (mag2 * mag2)) / (-2 * mag2 * mag1));
		//convert the angle from radians to degrees
        angle3  = angle3 * (180 / Math.PI);
		//convert the angle from a double to an int
        intAngle3 = (int)angle3;

		//manually round angles instead of truncating them
		
		//check if the decimal of the double angle is greater than or equal to 0.5
        if (angle1 % 1 >= 0.5)
        {
			//if it is, then add 1 to the int angle
            intAngle1 = (int)angle1 +1;
        }

		//check if the decimal of the double angle is greater than or equal to 0.5
        if (angle2 % 1 >= 0.5)
        {
			//if it is, then add 1 to the int angle
            intAngle2 = (int) angle2 + 1;
        }

		//check if the decimal of the double angle is greater than or equal to 0.5
        if (angle3 % 1 >= 0.5)
        {
			// if it is, then add 1 to the int angle
            intAngle3 = (int)angle3 + 1;
        }
		//manually rounding like this can minimize erros
		//due to truncating the decimal of our angles

		//print the total of the angles in the console window
        System.out.print(intAngle1 + intAngle2 + intAngle3);

		//set the textboxes to display hte angles
        num1.setText(Integer.toString(intAngle1));
        num3.setText(Integer.toString(intAngle2));
        num2.setText(Integer.toString(intAngle3));
    }

    public static void main(String[] args) {
        launch(args);
    }
}