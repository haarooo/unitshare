package unitshare.model.dao;

import unitshare.model.dto.ProductDto;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDao {
    //싱글톤 생성
    private ProductDao(){connect();}
    private static final ProductDao instance = new ProductDao();
    public static ProductDao getInstance() {
        return instance;
    }

    //db 연동
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

    //공동구매 참여취소:
    public boolean GroupCancel(int pno) {
        try {
            String sql = "delete from participant where pno=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pno);
            int count = ps.executeUpdate();
            if (count == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("sql문법 오류발생"+e);
        }
        return false;
    }


    //내가 올린 물품 등록 취소
    public boolean BoardCancel(int pno, int pwd) {
        try {
            String sql = "delete from product,user where pno=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pno);
            ps.setInt(2, pwd);
            int count = ps.executeUpdate();
            if (count == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("sql 오류");
            return false;
        }
    }



}
