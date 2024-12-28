//package DBConnection;
//
//import lombok.Getter;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//@Getter
//public class DBConnection {
//    private static DBConnection Instance;
//    private Connection connection;
//
//    private DBConnection() throws  SQLException {
////        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234") ;
//    }
//
//    public Connection getConnection() {
//        return connection;
//    }
//
//    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
//        if(Instance==null){
//            Instance = new DBConnection();
//        }
//        return Instance;
//    }
//}
