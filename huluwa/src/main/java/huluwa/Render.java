package huluwa;

import huluwa.Bullet.Bullet;
import huluwa.Creature.Creature;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.Pos;

public class Render extends Application {
    static Group root;
    static Label label;
    // variable for storing actual frame

    @Override
    public void start(Stage primaryStage) {
        System.out.println(ClassLoader.getSystemResource(""));
        primaryStage.setTitle("HuLu Battle");
        root = new Group();

        Image background = new Image("file:resource//bg.jpg");
        Canvas canvas = new Canvas(992, 558);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        root.getChildren().add(canvas);
        gc.drawImage(background, 0, 0);

        int menuBarHeight = 39;

        for (int i = 0; i <= 19; i++)
            gc.strokeLine(50 * i + 21, 0 + menuBarHeight, 50 * i + 21, 500 + menuBarHeight);
        for (int i = 0; i <= 10; i++)
            gc.strokeLine(21, 50 * i + menuBarHeight, 971, 50 * i + menuBarHeight);

        HBox hbox = new HBox();

        label = new Label("点击左上角菜单选择");
        label.setTextFill(Color.web("#ff0000"));
        label.setPrefSize(992, 558); // 设置标签的推荐宽高
        label.setFont(new Font("Arial", 50));
        label.setAlignment(Pos.CENTER); // 设置标签的对齐方式

        hbox.setSpacing(10);
        hbox.getChildren().add((label));
        root.getChildren().add(hbox);

        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        root.getChildren().add(menuBar);
        
        Menu fileMenu = new Menu("File");
        MenuItem startMenuItem = new MenuItem("Start");
        MenuItem replayMenuItem = new MenuItem("Replay");
        MenuItem exitMenuItem = new MenuItem("Exit");
        fileMenu.getItems().addAll(startMenuItem, replayMenuItem, exitMenuItem);
        startMenuItem.setOnAction(actionEvent -> {Game game = new Game(); game.init(root); label.setText("");});
        replayMenuItem.setOnAction(actionEvent -> Platform.exit());
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());

        menuBar.getMenus().addAll(fileMenu);

        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void drawBullet(Creature c, Bullet b, int tmp, int startX, int startY, int endX, int endY) { // 给定起点(发射子弹的生物)和终点(子弹命中目标)(的战场坐标)，绘制子弹运动
        Circle bullet = new Circle(5, Color.rgb(148, 0, 211));
        bullet.setCenterX(50 * startX - 4);
        bullet.setCenterY(50 * startY + 14);
        root.getChildren().add(bullet);

        double flyTime = Math.abs(endX - startX) * 200; // 假定子弹飞行一格需要200ms

        Timeline timeline = new Timeline();
        KeyValue xValue = new KeyValue(bullet.centerXProperty(), 50 * endX - 4);
        KeyValue yValue = new KeyValue(bullet.centerYProperty(), 50 * endY + 14);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(flyTime), xValue, yValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        timeline.setOnFinished(event -> {bullet.setCenterX(1000); Game.updateHp(c, b, tmp);});

    }

    public static void removeDead(BattlefieldGrid deadOne){
        root.getChildren().remove(deadOne.getVBox());
    }
    public static void gameIsOver(){
        int flag = Game.gameOver();
        if(flag==1){  //葫芦娃获胜
            label.setText("葫芦娃获胜！！！");
        }else if(flag==-1){  //蛇精获胜
            label.setText("蛇精获胜！！！");
        }
    }
}
