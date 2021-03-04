package mainGame;

import FrameWork.Animation;
import FrameWork.Texture;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player extends GameObject implements Entity {

    private Texture tex;

    private double velX = 0;
    private double velY = 0;
    private Animation anim;
    private gameEngine game;
    Controller ctrl;
    Random rnd=new Random();

    public Player(double x, double y, Texture tex, gameEngine game, Controller c)
    {
        super(x,y);
        this.tex = tex;
        this.game = game;
        this.ctrl = c;

        anim = new Animation(5,tex.player[0],tex.player[1]);
    }

    public void tick()
    {
       x += velX;
       y += velY;
       if(x <= 0)
           x = 0;
       if(x  >= 635)
           x = 635;
       if(y <= 0)
           y = 0;
       if(y >= 500)
           y = 500;
       for(int i = 0; i < game.enEN.size(); i++)
       {
           EntityEnemey tempEntity = game.enEN.get(i);
           if (Clashing.Collision(this, tempEntity))
           {
               ctrl.removeEntity(tempEntity);
               ctrl.addEntity((new EnemyShip(rnd.nextInt(650),-50, tex, ctrl, game)));
               System.out.println("Killed! !");
               game.alive--;
               x = 300;
               y = 500;
           }
       }
       anim.runAnimation();
    }
    public void render(Graphics g)
    {
        //g.drawImage(tex.player[0] , (int)x, (int)y,null);
        anim.drawAnimation(g,x,y,0);
    }
    public Rectangle getBounds (){
        return new Rectangle ((int)x,(int)y,50,65);
    }
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public void setX(double x)
    {
        this.x = x;
    }
    public void setY(double y)
    {
        this.y = y;
    }
    public void setVelX(double _velX)
    {
        this.velX = _velX;
    }
    public void setVelY(double _velY)
    {
        this.velY = _velY;
    }
}
