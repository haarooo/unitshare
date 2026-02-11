package unitshare.model.dao;

import unitshare.model.dto.UserDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

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

    private ArrayList< UserDto > users = new ArrayList<>(); // 0211 수정

    private int currentUno = 1; // 0211 수정
    // [1] 회원가입 Dao
    public boolean signup(String id, String pwd, String name, String phone ){
        UserDto userDto = new UserDto( currentUno, id, pwd, phone, name);
        boolean result = users.add(userDto);
        if(result){currentUno++;}
        return result;
    }// [1] end // 0211 수정

} //class end
