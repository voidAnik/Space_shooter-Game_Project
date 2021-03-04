package FrameWork;

import mainGame.Score;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileWork {
    Scanner input;
    PrintWriter writeFile;
    public void read(ArrayList<Score> scores) {
        try {
            input = new Scanner(new File("res/File/ScoresDOC.txt"));

        } catch (Exception e) {
            System.out.println("File not found for reading!");
        }
        while (input.hasNext()) {
            scores.add(new Score(input.next(),(Integer.parseInt(input.next()))));
        }
        input.close();
    }
    public void write(ArrayList<Score> scores)
    {
        //FileWriter fw = new FileWriter("res/StudentsInfo.txt");
        try {
            writeFile = new PrintWriter("res/File/ScoresDOC.txt");
        }catch(IOException ex)
        {
            System.out.println("File not found for writing!");
        }
        for(int i=0;i<scores.size();i++) {
            writeFile.println(scores.get(i).name + " " + scores.get(i).score);
            //System.out.println(students.get(i).name + " " + students.get(i).id + " " + students.get(i).cgpa);
        }
        writeFile.close();
    }
}
