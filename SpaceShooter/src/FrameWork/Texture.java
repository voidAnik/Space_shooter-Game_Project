package FrameWork;

import mainGame.BufferedImageLoader;
import mainGame.gameEngine;

import java.awt.image.BufferedImage;


public class Texture {
    public BufferedImage  ammo;
    public BufferedImage[] player=new BufferedImage[2];
    //public BufferedImage[] ammo=new BufferedImage[2];
    public BufferedImage[] enemy=new BufferedImage[2];

    BufferedImageLoader loader =new BufferedImageLoader();

    public Texture (gameEngine game)
    {
        getInput();
    }
    void getInput()
    {
        try{

            player[0] = loader.loadImage("/Main Ship/RedFighter0.png");
            player[1] = loader.loadImage("/Main Ship/RedFighter1.png");
            ammo = loader.loadImage("/Main Ship/Ammo.png");
            enemy[0] = loader.loadImage("/Enemy Ship/enemy2.png");
            enemy[1] = loader.loadImage("/Enemy Ship/enemy3.png");

        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Image Not Found!00");
        }
    }
}
