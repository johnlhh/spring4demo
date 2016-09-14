import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCMoreQuick {
    private static String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/test";
    private static String JDBC_USER = "root";
    private static String JDBC_PASS = "root";
    private static int COUNT = 1000000;
    private static int BATCH_SIZE = 1000;
    private static int COMMIT_SIZE = 50000;
    private static String DATA = "test";


    public static void main(String[] args) throws ClassNotFoundException,
            SQLException {
        long start = System.currentTimeMillis();
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(JDBC_URL
                + "?rewriteBatchedStatements=true", JDBC_USER, JDBC_PASS);
        conn.setAutoCommit(false);
        PreparedStatement pstmt = conn
                .prepareStatement("insert into test1 (id,shop_id,name,mobile,order_num,semo) values (?, ?,?,?,?,?)");
        for (int i = 1; i <= COUNT; i++) {
            pstmt.setInt(1, i);
            pstmt.setInt(2, i / 500);
            pstmt.setString(3, "john" + i % 10);
            pstmt.setString(4,"1332805286"+i % 10);
            pstmt.setString(5,"JC"+i);
            pstmt.setString(6,"SEMO"+i);
            pstmt.addBatch();
            if(i%BATCH_SIZE==0){
                pstmt.executeBatch();
                System.out.println(i);
            }
        }
        pstmt.executeBatch();
        conn.commit();
        pstmt.close();
        conn.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}