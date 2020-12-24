package huluwa;

import java.util.ArrayList;
import java.util.List;

import huluwa.Bullet.Bullet;
import huluwa.Creature.Creature;
import huluwa.Creature.Grandpa;
import huluwa.Creature.Hulu;
import huluwa.Creature.Pangolin;
import huluwa.Creature.Snack;
import huluwa.Creature.Scorpion;
import huluwa.Creature.LittleSoldier;
import javafx.scene.Group;

public class Game {
    static List<Creature> goodMan, badMan;
    static List<BattlefieldGrid> goodManGrid, badManGrid; //good: 爷爷+穿山甲+七个葫芦娃, bad: 蛇精+蝎子精+十个小喽啰 

    //BattlefieldGrid gpGrid,plGrid,hulu1Grid,hulu2Grid,hulu3Grid,hulu4Grid,hulu5Grid,hulu6Grid,hulu7Grid;
    //BattlefieldGrid snackGrid,scorpionGrid,ls1Grid,ls2Grid,ls3Grid,ls4Grid,ls5Grid,ls6Grid,ls7Grid,ls8Grid,ls9Grid,ls10Grid;

    public Game(){
        goodMan = new ArrayList<Creature>();
        badMan = new ArrayList<Creature>();
        goodManGrid = new ArrayList<BattlefieldGrid>();
        badManGrid = new ArrayList<BattlefieldGrid>();
    }

    public void init(Group root){
        goodMan.add(new Grandpa("grandpa", 20, 1, "One", 2 ,4));
        goodMan.add(new Pangolin("pangolin", 15, 2, "One", 2, 7));
        goodMan.add(new Hulu("one", 10, 3, "One", 5, 2));
        goodMan.add(new Hulu("two", 10, 3, "One", 5, 3));
        goodMan.add(new Hulu("three", 10, 3, "One", 5, 4));
        goodMan.add(new Hulu("four", 10, 3, "One", 5, 5));
        goodMan.add(new Hulu("five", 10, 3, "One", 5, 6));
        goodMan.add(new Hulu("six", 10, 3, "One", 5, 7));
        goodMan.add(new Hulu("seven", 10, 3, "One", 5, 8));

        badMan.add(new Snack("snack", 20, 2, "One", 18, 7));
        badMan.add(new Scorpion("scorpion", 15, 3, "One", 18, 4));
        badMan.add(new LittleSoldier("littleSoldier1", 10, 2, "One", 15, 1));
        badMan.add(new LittleSoldier("littleSoldier2", 10, 2, "One", 15, 2));
        badMan.add(new LittleSoldier("littleSoldier3", 10, 2, "One", 15, 3));
        badMan.add(new LittleSoldier("littleSoldier4", 10, 2, "One", 15, 4));
        badMan.add(new LittleSoldier("littleSoldier5", 10, 2, "One", 15, 5));
        badMan.add(new LittleSoldier("littleSoldier6", 10, 2, "One", 15, 6));
        badMan.add(new LittleSoldier("littleSoldier7", 10, 2, "One", 15, 7));
        badMan.add(new LittleSoldier("littleSoldier8", 10, 2, "One", 15, 8));
        badMan.add(new LittleSoldier("littleSoldier9", 10, 2, "One", 15, 9));
        badMan.add(new LittleSoldier("littleSoldier10", 10, 2, "One", 15, 10));

        for(int i=0; i<goodMan.size(); ++i){
            goodManGrid.add(new BattlefieldGrid(goodMan.get(i)));
        }
        for(int i=0; i<badMan.size(); ++i){
            badManGrid.add(new BattlefieldGrid(badMan.get(i)));
        }

        for(int i=0; i<goodManGrid.size(); ++i){
            root.getChildren().add(goodManGrid.get(i).getVBox());
        }
        for(int i=0; i<badManGrid.size(); ++i){
            root.getChildren().add(badManGrid.get(i).getVBox());
        }
    }

    public static void shoot(Creature c, Bullet b){  //(x,y)处的生物发射一个子弹, game模块负责找到命中目标并更新命中目标的情况，不负责绘图
        int startX = c.getPosX();
        int startY = c.getPosY();
        int endX = -1, endY = -1;
        boolean goodBad = c.getGoodOrBad();
        //TODO暂时规定子弹只能直线发射
        endY = startY;
        if(goodBad){   //葫芦娃阵营发射子弹
            endX = 21;
            for(int xi = startX+1; xi<19; ++xi){
                for(int i = 0; i<badMan.size();++i){
                    if(badMan.get(i).getPosX() == xi && badMan.get(i).getPosY() == endY ){  //找到目标
                        badMan.get(i).beAttacked(b);
                        endX = xi;
                        break;
                    }
                }
                if(endX!=21)break;
            }
        }else{   //蛇精阵营发射子弹
            endX = -1;
            for(int xi = startX-1; xi>0; --xi){
                for(int i = 0; i<goodMan.size();++i){
                    if(goodMan.get(i).getPosX() == xi && goodMan.get(i).getPosY() == endY ){
                        goodMan.get(i).beAttacked(b);
                        endX = xi;
                        break;
                    }
                }
                if(endX!=-1)break;
            }
        }

        Render.drawBullet(startX, startY, endX, endY);
    }

}
