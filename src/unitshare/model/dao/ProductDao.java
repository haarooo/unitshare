package unitshare.model.dao;

import unitshare.model.dto.ProductDto;

import java.sql.*;
import java.util.ArrayList;

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

    //21. 물품등록
    public boolean productAdd(String pname , int pprice , String pcontent , int people , String openchat){
        try {
            String sql = "insert into product(pname , pprice , pcontent , people , openchat)values(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pname);
            ps.setInt(2, pprice);
            ps.setString(3, pcontent);
            ps.setInt(4, people);
            ps.setString(5, openchat);
            int count = ps.executeUpdate();
            if (count == 1) {return true;}
            else {return false;}
        }catch (SQLException e){System.out.println("[시스템오류] SQL 문법 문제발행" + e);}
        return false;
    }

    //22. 전체 공동구매 목록조회
    public ArrayList<ProductDto> findAll(){
        ArrayList<ProductDto> products = new ArrayList<>();
        try{
            String sql = "select * from product";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int pno = rs.getInt("pno");
                String pname = rs.getString("pname");
                int pprice = rs.getInt("pprice");
                String pcontent = rs.getString("pcontent");
                String pdate = rs.getString("pdate");
                String openchat = rs.getString("openchat");
                int people = rs.getInt("people");
                ProductDto productDto = new ProductDto(pno , pname , pprice , pcontent , pdate , openchat , people);
                products.add(productDto);
            }
        }catch(SQLException e){System.out.println("sql 문법문제 2" + e);}
        return products;
    }

}
