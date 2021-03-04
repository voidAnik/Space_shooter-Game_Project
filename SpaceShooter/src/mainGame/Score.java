package mainGame;

public class Score implements Comparable<Score> {
    public String name;
    public int score;
    public Score(String n, int s)
    {
        this.name = n;
        this.score = s;
    }
    public int compareTo(Score s)
    {
        return s.score-this.score;
    }
}
