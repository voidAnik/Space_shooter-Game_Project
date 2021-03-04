package mainGame;
import FrameWork.Animation;
import FrameWork.Texture;

import java.awt.*;
import java.security.PublicKey;
import java.util.Random;

public class EnemyShip extends GameObject implements EntityEnemey {


    private Texture tex;
    Random rnd = new Random();
    private gameEngine game;
    private Controller ctrl;
    Animation anim;

    int speed = rnd.nextInt(3)+1;

    public EnemyShip(double ex, double ey, Texture tex,Controller ctrl,gameEngine game)
    {
        super(ex,ey);
        this.tex = tex;
        this.game = game;
        this.ctrl = ctrl;
        anim = new Animation(5,tex.enemy[0],tex.enemy[1]);
    }
    public void tick()
    {
        y += speed;
        if(y>600)
        {
            y = -50;
            x = rnd.nextInt(630);
            speed = rnd.nextInt(3)+1;
        }
        for(int i = 0; i < game.en.size(); i++)
        {
            Entity tempEntity = game.en.get(i);
            if (Clashing.Collision(this, tempEntity))
            {
                ctrl.removeEntity(tempEntity);
                ctrl.removeEntity(this);
                game.setEnemyKilled(game.getEnemyKilled() + 1);
                game.score++;
            }
        }
        anim.runAnimation();
    }
    public void render(Graphics g)
    {
        anim.drawAnimation(g,x,y,0);
    }
    public Rectangle getBounds (){
        return new Rectangle ((int)x,(int)y,65,75);
    }
    public double getEy(){
        return y;
    }
    public void setEy(double ey){
        this.y = ey;
    }
}
