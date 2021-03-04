package mainGame;

import java.awt.*;

public interface Entity {
    public void tick();
    public void render(Graphics g);
    public Rectangle getBounds() ;
}
