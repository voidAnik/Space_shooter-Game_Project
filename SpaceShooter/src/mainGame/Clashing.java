package mainGame;

import java.util.LinkedList;

public class Clashing {
    public static boolean Collision (Entity ent, EntityEnemey entEne){


            if(ent.getBounds().intersects(entEne.getBounds())){
                return true;
        }
        return false;
    }
    public static boolean Collision (EntityEnemey entEne, Entity ent){

            if(entEne.getBounds().intersects(ent.getBounds())){
                return true;
        }
        return false;
    }
}
