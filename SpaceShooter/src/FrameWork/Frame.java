package FrameWork;

import mainGame.BufferedImageLoader;
import mainGame.Player;
import mainGame.Score;
import mainGame.gameEngine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Frame extends JFrame implements ActionListener
{
    public static final int WIDTH = 700 , HEIGHT = 600;
    public ImageIcon icon;
    public CardLayout cl=new CardLayout();
    public JPanel mainPanel,playPage;
    JPanelWithBackground welcomePage,instructPage,scorePage;
    public JLabel welcome_label,name_label,score_label;//Labels
    public JButton bt1,bt2,bt3,pBack,iBack,sBack;
    public static Font wF,bt_F;
    public JTextField nameField;
    public Cursor cursor;
    public static boolean startGame = false;
    JTable table;
    JScrollPane scroll;
    DefaultTableModel model;
    String coloum[] = {"Name", "Score"};
    String row[] = new String[2];
    Graphics graphics =getGraphics();

    public static ArrayList<Score> scores = new ArrayList<>();
    FileWork scoreFile = new FileWork();

    gameEngine game;
    public Frame(){
        mainPanel=new JPanel();
        icon=new ImageIcon(getClass().getResource("/Icon.png"));
        wF=new Font("welcome_font",Font.BOLD,30);
        bt_F=new Font("welcome_font",Font.BOLD,15);
        welcome_label=new JLabel();
        bt1=new JButton("Start Game");
        bt2=new JButton("Instruction");
        bt3=new JButton("High Scores");
        cursor=new Cursor(12);
        scoreFile.read(scores);
        for(Score elem : scores)
        {
            System.out.println(elem.name+" " + elem.score);
        }
        components();
    }
    public void components()
    {

        mainPanel.setLayout(cl);
        setIconImage(icon.getImage());
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Space Shooter");
        setResizable(false);
        welcome_label.setFont(wF);
        welcome_label.setText("Welcome To Space Shooter");
        welcome_label.setBounds(150,30,400,50);
        welcome_label.setForeground(Color.blue);
        bt1.setFont(bt_F);
        bt1.setBackground(Color.red);
        bt1.setCursor(cursor);
        bt1.addActionListener(this);
        bt2.setFont(bt_F);
        bt2.setBackground(Color.yellow);
        bt2.setCursor(cursor);
        bt2.addActionListener(this);
        bt3.setFont(bt_F);
        bt3.setBackground(Color.green);
        bt3.setCursor(cursor);
        bt3.addActionListener(this);
        bt1.setBounds(270,300,150,30);
        bt2.setBounds(270,350,150,30);
        bt3.setBounds(270,400,150,30);

        nameField= new JTextField();
        nameField.setBounds(250,200,200,50);
        //Font f = new Font();
        nameField.setBackground(Color.cyan);
        nameField.setForeground(Color.BLACK);
        nameField.setFont(new Font("field FOnt",Font.BOLD,20));
        name_label = new JLabel("INPUT NAME HERE:");
        name_label.setBounds(250,150,200,50);
        name_label.setForeground(Color.CYAN);
        //graphics.drawString("HELLO",100,100);
        welcomePage = new JPanelWithBackground("/background.png");
       // welcomePage=new BgPanel(); //Welcome page making
        welcomePage.setLayout(null);
        welcomePage.add(welcome_label);
        welcomePage.add(name_label);


        welcomePage.add(bt1);
        welcomePage.add(bt2);
        welcomePage.add(bt3);
        welcomePage.add(nameField);

        playPage=new JPanel(); //Play page making
        playPage.setBackground(Color.green);

        pBack=new JButton("Back"); //Back button to return welcome page
        pBack.addActionListener(this);
        //playPage.add(game);
        playPage.add(pBack);

        instructPage = new JPanelWithBackground("/instructionBC.jpeg"); //Play page instruction page
        instructPage.setLayout(null);
        instructPage.setBackground(Color.blue);
        iBack=new JButton("Back"); //Back button to return welcome page
        iBack.setBounds(10,10,80,40);
        iBack.setBackground(Color.yellow);
        iBack.addActionListener(this);
        instructPage.add(iBack);

        scorePage=new JPanelWithBackground("/background.png"); //Score page making
        scorePage.setLayout(null);
        table = new JTable();
        model = new DefaultTableModel();
        model.setColumnIdentifiers(coloum);
        table.setModel(model);
        table.setSelectionBackground(Color.BLUE);
        table.setBackground(Color.WHITE);
        table.setRowHeight(30);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(50,150,600,255);

        sBack=new JButton("Back"); //Back button to return welcome page\
        sBack.setBounds(10,10,80,40);
        sBack.addActionListener(this);
        //score_label = new JLabel();
        //score_label.setBounds();
        scorePage.add(sBack);
        scorePage.add(scroll);

        mainPanel.add(welcomePage,"welcomePage");
        mainPanel.add(playPage,"playPage");

        mainPanel.add(instructPage,"instructPage");
        mainPanel.add(scorePage,"scorePage");
        add(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==bt1) {
            String n =nameField.getText();
            game = new gameEngine(this,welcomePage,mainPanel,scores,n,scoreFile);
            mainPanel.add(game,"gamingPage");
           cl.show(mainPanel, "gamingPage");
           //game.stop();
           game.alive = 3;
           game.score = 0;
           game.start();
           //startGame=true;
        }
        if(e.getSource()==bt2)
        {
            cl.show(mainPanel,"instructPage");
        }
        if(e.getSource()==bt3) {
            cl.show(mainPanel, "scorePage");
            for(int i = 0;i<scores.size();i++)
            {
                row[0] = scores.get(i).name;
                row[1] = Integer.toString(scores.get(i).score);
                model.addRow(row);
            }
        }
        if(e.getSource()==pBack)
        {
            cl.show(mainPanel,"welcomePage");
            startGame = false;
        }
        if(e.getSource()==iBack)
        {
            cl.show(mainPanel,"welcomePage");
        }
        if(e.getSource()==sBack)
        {
            cl.show(mainPanel,"welcomePage");
        }
    }
}

