import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DataStructuresReachingClass {
    PreparedStatement insertStatement;
    Connection connection;

    public DataStructuresReachingClass() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user","root");
        connectionProps.put("password","asdasd123123");
        connectionProps.put("serverTimezone", "UTC");
        String dbURL = "jdbc:mysql://localhost:3306/yogibear_highscores";
        connection = DriverManager.getConnection(dbURL, connectionProps);

        String insertQuery = "INSERT INTO HIGHSCORES (NAME, TIME, POINTS) VALUES (?, ?, ?)";
        insertStatement = connection.prepareStatement(insertQuery);

    }
    public ArrayList<HighScore> getHighScores() throws SQLException{
        String query = "SELECT * FROM HIGHSCORES ORDER BY points DESC, time ASC LIMIT 10";
        ArrayList<HighScore> highScores = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet results = stmt.executeQuery(query);
        while(results.next()){
            String name = results.getString("NAME");
            int points = results.getInt("POINTS");
            Time time = results.getTime("TIME");
            highScores.add(new HighScore(name, time, points));
        }
        return highScores;

    }
    public void insertScore(HighScore score) throws SQLException{
        insertStatement.setString(1, score.getName());
        insertStatement.setTime(2, score.getTime());
        insertStatement.setInt(3, score.getPoints());
        insertStatement.executeUpdate();
    }
}
