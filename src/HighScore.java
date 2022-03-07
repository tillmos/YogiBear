import java.sql.Time;

public class HighScore {
    private int Id;
    private String Name;
    private java.sql.Time Time;
    private int Points;

    public HighScore(String name,Time time,int point){
        this.Name = name;
        this.Time = time;
        this.Points = point;
    }

    public int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public Time getTime() {
        return Time;
    }
    public void setTime(Time Time) {
        this.Time = Time;
    }
    public int getPoints() {
        return Points;
    }
    public void setPoints(int Points) {
        this.Points = Points;
    }
}
