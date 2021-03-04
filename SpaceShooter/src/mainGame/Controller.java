package mainGame;

import FrameWork.Texture;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Controller {

    private LinkedList<Entity> en = new LinkedList<Entity>();
    private LinkedList<EntityEnemey> enEN = new LinkedList<EntityEnemey>();

    Entity ent;
    EntityEnemey entEne;
    private Texture tex;
    Random rnd =new Random();
    private gameEngine game;

    public Controller(gameEngine game , Texture tex){
          this.game = game;
          this.tex = tex;
    }
    public void creatEnemy(int enemyCount){
        for (int i =0;i<enemyCount;i++){
            addEntity(new EnemyShip(rnd.nextInt(630),-50,tex,this,game));
        }
    }
    public void tick(){
        for(int i = 0; i<en.size();i++) {
            ent = en.get(i);
            ent.tick();
        }

        //Enemey Entity
        for(int i = 0; i<enEN.size();i++) {
            entEne = enEN.get(i);
            entEne.tick();
        }
    }
    public void render(Graphics g){
        for(int i = 0; i<en.size();i++)
        {
            ent = en.get(i);
            ent.render(g);
        }

        //Enemy entity
        for(int i = 0; i<enEN.size();i++)
        {
            entEne = enEN.get(i);
            entEne.render(g);
        }
    }
    public void addEntity (Entity block){
        en.add(block);
    }
    public void removeEntity (Entity block){
        en.remove(block);
    }

    //Enemy entity
    public void addEntity (EntityEnemey block){
        enEN.add(block);
    }
    public void removeEntity (EntityEnemey block){
        enEN.remove(block);
    }
    public LinkedList<Entity> getEn(){
        return en;
    }
    public LinkedList<EntityEnemey> getEnEN(){
        return enEN;
    }

}
