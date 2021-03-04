package mainGame;

import java.awt.*;

public interface EntityEnemey {
    public void tick();
    public void render(Graphics g);
    public Rectangle getBounds() ;
}
