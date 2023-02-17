package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
    public static Connections instance;

    static {
        instance = new Connections();
    }

    private Connection myConnection;

    public static Connections getInstance() {
        return instance;
    }

    public Connections() {
        napraviKonekciju();
    }

    public Connection napraviKonekciju()
    {
        try {
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shift_generator", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myConnection;
    }
}
