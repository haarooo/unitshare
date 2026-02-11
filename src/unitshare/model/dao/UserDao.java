package unitshare.model.dao;

import unitshare.model.dto.UserDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.ArrayList;

public class UserDao {
    //싱글톤 생성
    private UserDao() {connect(); // <--- 아!!!!!!!!!! 객체가 생성될 때 DB 연동을 시작합니다.
    }

    public static final UserDao instance = new UserDao();

    public static UserDao getInstance() {
        return instance;
    }

    private ArrayList<UserDto> UserDtos = new ArrayList<>();

    //db연동
    private String url = "jdbc:mysql://localhost:3306/unishare";
    private String user = "root";
    private String pw = "1234";
    private Connection conn;

    private void connect() { // conn 변수에 DB 연결 정보가 담김.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("연동성공");
        } catch (Exception e) {
            System.out.println("연동실패" + e);
        }
    }

    private ArrayList< UserDto > users = new ArrayList<>(); // 0211 수정

    private int currentUno = 1; // 0211 수정
    // 04. 회원가입 Dao
    public boolean signup(String id, String pwd, String name, String phone ) {
       try{ String sql = "insert into user(id, pwd, name, phone) values(?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, pwd);
            ps.setString(3, name);
            ps.setString(4, phone);
            int count = ps.executeUpdate();
           if( count == 1 ){ return true; }
           else{ return false; }
        } catch (SQLException e) {System.out.println("[시스템오류] 회원가입 SQL 실행 중 실패 : " + e);}
            return false;
        }
    // [1] end // 0211 수정
    // 로그인(현재 정보와 기존 정보를 비교)
    public boolean login(String id, String pwd) {
        System.out.println("UserDao.login");
        try { // SQL 작성 : 입력받은 id와 pwd가 일치하는 레코드가 있는지 확인
            String sql = "select * from user where id = ? AND pwd = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, pwd);

            ResultSet rs = ps.executeQuery(); // 쿼리 실행 결과 받기

            if (rs.next()) {
                return true; // 레코드가 하나라도 존재하면 로그인 성공
            }
        }catch (Exception e){
            System.out.println("[경고] 로그인 처리 중 에러 : " + e);
        }
        return false;
    } // m END
}
