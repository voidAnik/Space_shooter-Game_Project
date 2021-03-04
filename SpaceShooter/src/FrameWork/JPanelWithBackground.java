package FrameWork;

import mainGame.BufferedImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import mainGame.BufferedImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JPanelWithBackground extends JPanel
{
    //Image image;
    BufferedImageLoader loader = new BufferedImageLoader();
    BufferedImage bc;

    public JPanelWithBackground(String fpath)
    {
        try
        {
            bc = loader.loadImage(fpath);
        }
        catch (Exception e) {
            System.out.println("Not");/*handled in paintComponent()*/ }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(bc, 0,0,this);
    }
}


