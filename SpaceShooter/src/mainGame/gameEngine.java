package mainGame;
import FrameWork.*;
import FrameWork.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class gameEngine extends Canvas implements Runnable {
    private boolean running = false;
    private Thread thread;

    BufferedImageLoader loader = new BufferedImageLoader();
    BufferedImage background = loader.loadImage("/background.png");
    BufferedImage aliveIcon = loader.loadImage("/Alive_icon.png");
    int alive_x[]={650, 610, 570};
    int alive_y[]={15, 15, 15};
    public int alive = 3;
    private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    private BufferedImage plImage;
    Font score_font = new Font("Score font",Font.BOLD,15);
    private Player p;
    private Controller ctrl;
    private Texture tex;
    private boolean isShooting = false;
    private int enemyCount = 5;
    private int enemyKilled = 0;
    public int score;
    Frame gFrame;
    JPanel mp;
    JPanelWithBackground wp;
    ArrayList<Score> scores;
    FileWork scoreFile;
    String name;

    public LinkedList<Entity> en ;
    public LinkedList<EntityEnemey> enEN ;
    public gameEngine(Frame fr, JPanelWithBackground w, JPanel m, ArrayList<Score> scores, String name, FileWork f)
    {
        this.gFrame = fr;
        this.wp = w;
        this.mp = m;
        this.scores = scores;
        this.name = name;
        this.scoreFile = f;
    }

    public void init()
    {
        requestFocus();

        addKeyListener(new KeyInput(this));
        tex = new Texture(this);
        ctrl = new Controller(this, tex);
        p=new Player(300,500, tex,this,ctrl);

        en = ctrl.getEn();
        enEN = ctrl.getEnEN();
        ctrl.creatEnemy(enemyCount);
    }

    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    public void run() {
        //Game loop.
        init();
        long lastTime = System.nanoTime();
        final double ticks = 60.0;
        double nanoS = 1000000000 / ticks;
        double delta = 0;
        int updates = 0, frames = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoS;
            lastTime = now;
            if (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(updates + "Ticks, Fps " + frames);
                updates = 0;
                frames = 0;
            }

        }
        stop();
    }

    private void tick() {
        p.tick();
        ctrl.tick();
        if(enemyKilled >= enemyCount)
        {
            enemyKilled = 0;
            enemyCount += 2;
            ctrl.creatEnemy(enemyCount);
        }
        if(alive==0)
        {
            gFrame.add(mp);
            gFrame.mainPanel.add(wp,"welcomePage");
            gFrame.cl.show(mp,"welcomePage");
            scores.add(new Score(name,score));
            Collections.sort(scores);

            for(int i = 6;i < scores.size();i++)
                scores.remove(i);

            scoreFile.write(scores);
            gFrame.nameField.setText("");
            stop();
        }
    }

    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null)
        {
            createBufferStrategy(3);
            return;
        }
        Graphics graphics = bs.getDrawGraphics();
        //Will draw here
        graphics.drawImage(background,0,0,this);
        graphics.setColor(Color.RED);
        graphics.setFont(score_font);
        String scrs =new String();
        scrs = Integer.toString(score);
        graphics.drawString("SCORE:",15,30);
        graphics.drawString(scrs,85,30);
        for(int i = 0; i < alive; i++)
        {
            graphics.drawImage(aliveIcon,alive_x[i],alive_y[i],this);
        }

        p.render(graphics);
        ctrl.render(graphics);
        /////
        graphics.dispose();
        bs.show();
    }
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT)
        {
            p.setVelX(5);
        }
        else if(key == KeyEvent.VK_LEFT)
        {
            p.setVelX(-5);
        }
        else if(key == KeyEvent.VK_DOWN)
        {
            p.setVelY(5);
        }
        else if(key == KeyEvent.VK_UP)
        {
            p.setVelY(-5);
        }
        else if (key==KeyEvent.VK_SPACE && !isShooting)
        {
            isShooting = true;
            ctrl.addEntity(new Ammo(p.getX()+9 ,p.getY()-10,tex, this, ctrl));
        }
       // else if(key == KeyEvent.VK_END)
         //   exit();
    }
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT)
        {
            p.setVelX(0);
        }
        else if(key == KeyEvent.VK_LEFT)
        {
            p.setVelX(0);
        }
        else if(key == KeyEvent.VK_DOWN)
        {
            p.setVelY(0);
        }
        else if(key == KeyEvent.VK_UP)
        {
            p.setVelY(0);
        }
        else if (key==KeyEvent.VK_SPACE )
        {
            isShooting = false;
        }
    }
   public BufferedImage getplImage()
   {
       return plImage;
   }
   public int getEnemyCount(){
        return enemyCount;
   }
   public void setEnemyCount(int enemyCount){
       this.enemyCount = enemyCount;
   }
    public int getEnemyKilled(){
        return enemyKilled;
    }
    public void setEnemyKilled(int enemyKilled){
        this.enemyKilled = enemyKilled;
    }
}
