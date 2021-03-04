package FrameWork;
import mainGame.gameEngine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
    gameEngine game;

    public KeyInput(gameEngine _game)
    {
        this.game = _game;
    }
    public void keyPressed(KeyEvent e)
    {
        game.keyPressed(e);
    }
    public void keyReleased(KeyEvent e)
    {
        game.keyReleased(e);
    }
}
