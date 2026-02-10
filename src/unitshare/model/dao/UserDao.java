package unitshare.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class UserDao {
    //싱글톤 생성
    private UserDao(){}
    public static final UserDao instance = new UserDao();
    public static UserDao getInstance() {
        return instance;
    }


    //db연동
    private String url="jdbc:mysql://localhost:3306/unishare";
    private String user = "root";
    private String pw = "1234";
    private Connection conn;

    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("연동성공");
        } catch (Exception e) {
            System.out.println("연동실패");
        }
    }


} //class end
