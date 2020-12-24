package huluwa;

import huluwa.Game;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Render extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("HuLu Battle");
        Group root = new Group();

        Image background = new Image("file:resource//bg.jpg");
        Canvas canvas = new Canvas(992, 558);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        root.getChildren().add(canvas);
        gc.drawImage(background, 0 , 0);

        int menuBarHeight = 29;

        for(int i = 0; i <= 19; i++)
			gc.strokeLine(50*i+21, 0+menuBarHeight, 50*i+21, 500+menuBarHeight);
		for(int i = 0; i <= 10;i++)
			gc.strokeLine(21, 50*i+menuBarHeight, 971, 50*i+menuBarHeight);

        Game game = new Game();
        game.init(root);

        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}