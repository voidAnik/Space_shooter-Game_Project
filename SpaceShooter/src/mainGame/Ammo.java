package mainGame;
import FrameWork.Texture;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Ammo extends GameObject implements Entity {


    private  Texture tex;
    private gameEngine game;
    private Controller ctrl;

    //BufferedImage ammo;
    public Ammo(double ax, double ay, Texture tex, gameEngine game,Controller c)
    {
        super(ax,ay);
        this.tex = tex;
        this.game= game;
        this.ctrl = c;
    }

    public void tick(){
        y -= 10;
        //if(Clashing.Collision(this, game.enEN)){
           //ctrl.removeEntity(this);
       // }
    }

    public void render(Graphics g){
        g.drawImage(tex.ammo, (int)x, (int)y, null);
    }
    public Rectangle getBounds (){
        return new Rectangle ((int)x,(int)y,30,32);
    }

    public double getAy(){
        return y;
    }
}
