package huluwa;

import org.w3c.dom.css.RGBColor;

import huluwa.Game;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Render extends Application {
    static Group root;
    //variable for storing actual frame

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("HuLu Battle");
        root = new Group();

        Image background = new Image("file:resource//bg.jpg");
        Canvas canvas = new Canvas(992, 558);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        root.getChildren().add(canvas);
        gc.drawImage(background, 0, 0);

        int menuBarHeight = 29;

        for (int i = 0; i <= 19; i++)
            gc.strokeLine(50 * i + 21, 0 + menuBarHeight, 50 * i + 21, 500 + menuBarHeight);
        for (int i = 0; i <= 10; i++)
            gc.strokeLine(21, 50 * i + menuBarHeight, 971, 50 * i + menuBarHeight);

        Game game = new Game();
        game.init(root);

        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void drawBullet(int startX, int startY, int endX, int endY) { // 给定起点(发射子弹的生物)和终点(子弹命中目标)(的战场坐标)，绘制子弹运动
        Circle bullet = new Circle(5, Color.rgb(148, 0, 211));
        bullet.setCenterX(50 * startX - 4);
        bullet.setCenterY(50 * startY + 4);
        root.getChildren().add(bullet);

        double flyTime = Math.abs(endX - startX - 1) * 200; // 假定子弹飞行一格需要200ms

        Timeline timeline = new Timeline();
        KeyValue xValue  = new KeyValue(bullet.centerXProperty(), 50*endX - 4); 
        KeyValue yValue  = new KeyValue(bullet.centerYProperty(), 50*endY + 4);
        KeyFrame keyFrame  = new KeyFrame(Duration.millis(flyTime), xValue, yValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        timeline.setOnFinished(event -> bullet.setCenterX(1000));
        
    }
}
