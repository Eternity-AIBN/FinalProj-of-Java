package huluwa.Client;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import huluwa.Game;
import huluwa.Render;

public class PlayerClient {
    private NetClient nc = new NetClient(this);
    public boolean goodReady = false, badReady = false;
    private boolean goodOrBad;
    private Game game;
    private Render render;

    public static void main(String[] args) {
        PlayerClient pc = new PlayerClient();
        pc.startTheThread();    //自己启动一个线程
        pc.connectToServer();   //连接服务器
        game = new Game(goodOrBad);
    }
    
    private void startTheThread(){
        new Thread(new GameThread()).start();
    }

    private void connectToServer(){
        String serverIP = "127.0.0.1";
        nc.connect(serverIP);
    }

    class GameThread implements Runnable {
        public void run() {
            while(true) {
                //TODO轮到自己操作生物
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean getGoodBad(){
        return this.goodOrBad;
    }

    public void setGoodBad(boolean flag){
        this.goodOrBad = flag;
    }

    public void gameOver(){
        
    }

}
