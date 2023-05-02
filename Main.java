package Assignment05_000315902;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * Catch the apple game. apple from top of game screen falls and user controls basket to catch apple. Score increments
 * with each catch and each missed apple decreases a life.... known issue - slight delay when resetting game.
 * Label, Canvas and Textfields used... note Textfields come from Basket Class.
 * @ Author Armand Amores 000315902
 */
public class Main extends Application  {
    /** Title */
    private Label title;
    /** Credits on bottom right corner */
    private Label credits;
    /** Controls found on left side of view */
    private Label instructions;
    /** White screen */
    private Canvas gameCanvas;
    /** Basket to catch apples */
    private Basket basket;
    /** Apples users catch */
    private Apple apple;
    /** Helps to control game */
    boolean gameFlag = true;


    /**
     * @param args Launch arguments
     */
    public static void main(String[] args) {
        launch(args);

    }


    /**
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene is set.
     *                     Contains layouts, nodes, and animation.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        /** set title */
        primaryStage.setTitle("Catch the Apples!");
        Pane root = new Pane();

        //Adding layout to our scene and specifying dimensions
        Scene scene = new Scene(root, 900,600);

        //set Scene for stage
        primaryStage.setScene(scene);


        //1. Create GUI components
        title = new Label("Catch The Apples!");
        gameCanvas = new Canvas(450, 300);
        credits = new Label("Created by: Armand Amores");
        instructions = new Label("Use Left and Right\narrow keys to move\n\nPress Space Bar\nto Reset\n\nPress Q to quit");
        Label gameOver = new Label("Game Over!");



        //2. Add GUI elements to layout
        root.getChildren().addAll(title, gameCanvas, credits, instructions);

        //3. configure components
        //positioning
        title.relocate(210, 40);
        credits.relocate(590,585);
        instructions.relocate(5,300);
        gameOver.relocate(300,480);
        //used to help center gameCanvas
        gameCanvas.setLayoutX((root.getWidth() - gameCanvas.getWidth()) / 2);
        gameCanvas.setLayoutY((root.getHeight() - gameCanvas.getHeight())/ 2);



        //Styles
        //Draw Game Screen Canvas
        GraphicsContext gc = gameCanvas.getGraphicsContext2D();
        gc.setFill(Color.WHITESMOKE);
        gc.fillRect( 0,0,450, 350);


        //Title styles
        Font titleFont = Font.loadFont(getClass().getResourceAsStream("/Assignment05_000315902/PressStart2P.ttf"), 30); // imported retro text style
        title.setFont(titleFont);
        title.setStyle("-fx-text-fill: white;");
        credits.setFont(titleFont);
        credits.setStyle("-fx-text-fill: white; -fx-font-size: 12px;");
        instructions.setStyle("-fx-text-fill: white; -fx-font-size: 12px;");
        root.setStyle("-fx-background-color: black;");
        gameOver.setFont(titleFont);
        gameOver.setStyle("-fx-text-fill: white;");



        //4. Create models and attach to game view aka gameCanvas
        basket = new Basket();
        apple = new Apple();

        //add nodes
        root.getChildren().addAll(basket.getModel(),apple.getModel(), basket.getScoreText(), basket.getLivesText());
        basket.getScoreText().setStyle("-fx-text-fill: white;"); // add basket score Textfield



        //5. Attaching event handlers
        // Learned about setting animations with Animation timer.... could not get everything right
        AnimationTimer timer = new AnimationTimer() {
            /**
             * @param now The timestamp of the current frame given in nanoseconds. This
             *            value will be the same for all {@code AnimationTimers} called
             *            during one frame.
             *            Used to play animation... animation designed in apple class.
             */
            @Override
            public void handle(long now) {
                if (gameFlag) {
                    apple.fall(); // used to animate falling apple object
                } else {
                    System.out.println("Stop game");
                    stop();
                }
                // Could not figure out how to fully separate logic from view with animations.
                //checks collision and if missed apples
                if (apple.getModel().getCenterY() > 420 && !basket.intersects(apple)) {
                    System.out.println("Lost point");
                    basket.decreaseLives(); //method to decrease model lives
                } else if (basket.intersects(apple)) {
                    System.out.println("Hit Detected ");
                    basket.incrementScore(); // method to increase score for each apple caught
                }
                // gameover situation
                if (basket.getLives() == 0) {
                    gameFlag = false;
                    root.getChildren().add(gameOver);

                }else{
                    root.getChildren().remove(gameOver);
                }

            }
        };
        timer.start(); // starts animation

        // event handlers
        gameCanvas.setOnKeyPressed(event -> {

            if (event.getCode() == KeyCode.LEFT) {
                basket.moveLeft(); // move basket left if left arrow key pressed
            }
            if (event.getCode() == KeyCode.RIGHT) {
                basket.moveRight(); // move basket right if left arrow key pressed
            }
            //used to reset game.... note slight delay when reset
            if (event.getCode() == KeyCode.SPACE) {
                gameFlag = true;
                basket.setScore(0); // reset score
                basket.setLives(6);// reset lives
                timer.start(); // reset timer

            }
            // quit game
            if (event.getCode() == KeyCode.Q) {
                gameFlag = false;
                System.exit(0);

            }
        });
        // focus with game screen(canvas)
        gameCanvas.requestFocus();


        //end: show stage
        primaryStage.show();


    }

}
