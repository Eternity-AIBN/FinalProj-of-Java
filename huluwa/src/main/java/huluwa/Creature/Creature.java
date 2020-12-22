package huluwa.Creature;

import huluwa.Bullet.Bullet;
import huluwa.Bullet.BulletFactory;

public class Creature{
    private String name;  //名字
    private int fullHP;   //总HP值
    private int curHP;    //当前HP值
    private int defence;  //防御力
    private BulletFactory bulletFactory;
    private Bullet bullet;   //自己的子弹类型
    private int posX, posY;  //在场上坐标
    

    Creature(String name, int fullHP, int defence, String bulletType){
        bulletFactory = new BulletFactory();
        this.name = name;
        this.fullHP = fullHP;
        this.defence = defence;
        this.bullet = bulletFactory.getShape(bulletType);
    }
    public String getName(){
        return this.name;
    }
    public int getHP(){
        return this.curHP;
    }
    public void setHP(int newHP){
        this.curHP = newHP;
    }
}