package huluwa;

import huluwa.Creature.Creature;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

//将战场上的一个(有生物体的)格子看成该类的一个对象，实际上是一个VBOX盒子
public class BattlefieldGrid {
    private VBox vbox;
    private Button button;  //用鼠标点击该格子即选中之（若存在生物），之后可控制生物进行移动或攻击
    private ProgressBar hpBar;  //进度条控件，显示生物当前血量情况
    //private String name;  //这个格子上对应的生物的姓名
    private Creature creature;  //在这个格子上的生物体
    
    public BattlefieldGrid(Creature c) {
        creature = c;
        vbox = new VBox();
        button = new Button();
        hpBar = new ProgressBar();
        
        //设置Button控件
        String fileName = "file:resource//" + creature.getName() + ".png";
        Image image = new Image(fileName);
        ImageView imageview = new ImageView(image);
        imageview.setFitHeight(40);
        imageview.setFitWidth(50);
        button.setGraphic(imageview);

        DropShadow shadow = new DropShadow();  //当鼠标移动到Button上时添加阴影效果
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            button.setEffect(shadow);
        });
        button.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            button.setEffect(null);
        });

        button.setOnAction((ActionEvent e) -> {
            //TODO 移动、攻击，死亡了要干啥干啥
        });

        button.setMinSize(50, 40);
        button.setMaxSize(50, 40);
        Tooltip tp = new Tooltip(); //添加工具提示控件
        tp.setText(
            "FullHP: " + creature.getFullHP() + 
            "\nCurrentHP: " + creature.getCurHP() + 
            "\nDefence: "  + creature.getDefence() + 
            "\nBullet: " + creature.getBullet().getPower() 
        );
        button.setTooltip(tp);

        //设置ProgressBar控件即血条
        hpBar.setProgress(creature.getCurHP()/creature.getFullHP());
        hpBar.setMinSize(50, 10);
        hpBar.setMaxSize(50, 10);
        hpBar.setStyle("-fx-accent: green;");

        vbox.setMinSize(50, 40);
        vbox.setMaxSize(50, 40);
        vbox.getChildren().addAll(button, hpBar);

        vbox.setLayoutX(21 + 50*(creature.getPosX() - 1));
        vbox.setLayoutY(29 + 50*(creature.getPosY() - 1));
    }

    public VBox getVBox(){
        return this.vbox;
    }

    public void update(){  //一个生物体的状态发生改变时相应的更新其在战场上的表现（位置，血量等）

    }
}
