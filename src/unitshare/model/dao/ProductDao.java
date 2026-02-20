package unitshare.model.dao;

import unitshare.controller.ProductController;
import unitshare.model.dto.ProductDto;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import unitshare.model.dto.ProductDto;


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
    public int productAdd(String pname , int pprice , String pcontent , int people , String openchat , int uno){
        try {

            String sql = "insert into product(pname , pprice , pcontent , people , openchat , uno)values(?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pname);
            ps.setInt(2, pprice);
            ps.setString(3, pcontent);
            ps.setInt(4, people);
            ps.setString(5, openchat);
            ps.setInt(6 , uno);
            int count = ps.executeUpdate();
            if(count ==1){

                String sql2 = "select * from product order By pno desc limit 1";
                PreparedStatement ps1 = conn.prepareStatement( sql2 );
                ResultSet rs = ps1.executeQuery();
                if( rs.next() ){
                    return rs.getInt( "pno" );
                }
            }
            else{return 0;}
        }catch (SQLException e){System.out.println("[시스템오류] SQL 문법 문제발행" + e);}
        return 0;
    }



    //공동구매 참여취소:
    public boolean GroupCancel(int pno,String pwd) {
        try {
            String sql = "delete p from participant p inner join user u on p.uno=u.uno where p.pno=? and u.pwd=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pno);
            ps.setString(2,pwd);
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
    public boolean BoardCancel(int pno, String pwd) {
        try {
            String sql = "delete p from product p inner join user u on p.uno=u.uno where p.pno=? and u.pwd=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps = conn.prepareStatement(sql);
            ps.setInt(1, pno);
            ps.setString(2, pwd);
            int count = ps.executeUpdate();
            if (count == 1) {
                System.out.println("삭제 성공");
            } else {
                System.out.println("삭제실패");
            }
        } catch (SQLException e) {
            System.out.println("sql오류" + e);
            return false;
        }
        return false;
    }


    //22. 전체 공동구매 목록조회
    public ArrayList<ProductDto> findAll(){
        ArrayList<ProductDto> products = new ArrayList<>();
        try{
            String sql = "SELECT *, (SELECT COUNT(*) FROM participant WHERE participant.pno = product.pno) AS cpeople FROM product";
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
                int cpeople = rs.getInt("cpeople");
                ProductDto productDto = new ProductDto(pno , pname , pprice , pcontent , pdate , openchat , people, cpeople);
                products.add(productDto);

            }
        }catch(SQLException e){System.out.println("sql 문법문제 2" + e);}
        return products;
    }



    // 내 구매 신청 목록 조회
    public ArrayList<ProductDto> mylist(int uno) {
        ArrayList<ProductDto> products = new ArrayList<>();

        String sql = "SELECT p.* FROM product p INNER JOIN participant t ON p.pno = t.pno WHERE t.uno = ?";

            try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, uno );


            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDto productDto = new ProductDto(
                        rs.getInt("pno"),
                    rs.getString("pname"),
                    rs.getInt("pprice"),
                    rs.getString("pdate"),
                    rs.getString("openchat")
                );

                products.add(productDto);
            } // whi END
        }catch (SQLException e){
            System.out.println("[시스템 오류] sql 문법문제 발생" + e);
        }
        return products;
    } // m END

    // 내가 등록한 물품 목록 조회
    public ArrayList<ProductDto> myuplist(int uno){
        ArrayList<ProductDto> products = new ArrayList<>();
        String sql ="SELECT * FROM product WHERE uno = ? ";

        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, uno );


            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDto productDto = new ProductDto(
                        rs.getInt("pno"),
                        rs.getString("pname"),
                        rs.getInt("pprice"),
                        rs.getString("pdate"),
                        rs.getString("openchat")
                );

                products.add(productDto);
            } // whi END
        }catch (SQLException e){
            System.out.println("[시스템 오류] sql 문법문제 발생" + e);
        }
        return products;
    } // m END

    //등록자 참여자 신청
    public int myGroupBuying(int pno , int uno){
        try{
            String sql4 = "insert into participant(pno , uno , status)values (?,?,1)";
            PreparedStatement ps4 = conn.prepareStatement(sql4);
            ps4.setInt(1 ,pno);
            ps4.setInt(2 , uno);
            if(ps4.executeUpdate() ==1)return 1;
        }catch (Exception e){System.out.println("sql오류");}
        return 0;
    }

    //공동구매 신청
    public int groupBuying(int pno , int uno){
        try{

            //등록자와 신청자가 같을시 2반환
            String sql = "select uno,people from product where pno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1 , pno);
            ResultSet rs = ps.executeQuery();
            int addUno = -1 ;
            int maxPeople = 0;
            if(rs.next()){
                addUno = rs.getInt("uno");
                maxPeople = rs.getInt("people");
            }if(addUno == uno)return 2;

            //현재 신청 인원이 최대인원보다 크거나 같으면 4반환
            String sql2 = "select count(*) from participant where pno = ?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, pno);
            ResultSet rs2 = ps2.executeQuery();
            if(rs2.next()){
                int npeople = rs2.getInt(1);
                if(npeople == maxPeople) return 4;
            }
            //내가 이미 신청한 게시물이면 3반환
            String sql3 = "select count(*) from  participant where pno = ? and uno = ?";
            PreparedStatement ps3 = conn.prepareStatement(sql3);
            ps3.setInt(1 , pno);
            ps3.setInt(2 , uno);
            ResultSet rs3 = ps3.executeQuery();
            if(rs3.next()){
                int myApply = rs3.getInt(1);
                if(myApply > 0)return 3;
            }

            String sql4 = "insert into participant(pno , uno , status)values (?,?,1)";
            PreparedStatement ps4 = conn.prepareStatement(sql4);
            ps4.setInt(1 ,pno);
            ps4.setInt(2 , uno);
            if(ps4.executeUpdate() ==1)return 1;

        }catch (Exception e){System.out.println("sql오류");}
        return 0;
    }



    //포인트 입금 함수
    public int payPoint(int pno, int uno) {
        try {
            // 1. 이 제품의 1인당 가격이 얼마인지 가져오기
            String sql = "SELECT pprice, people FROM product WHERE pno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pno);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int perPrice = rs.getInt("pprice") / rs.getInt("people");
                String sql2 = "UPDATE user SET point = point - ? WHERE uno = ? AND point >= ?";
                ps = conn.prepareStatement(sql2);
                ps.setInt(1, perPrice); ps.setInt(2, uno); ps.setInt(3, perPrice);

                if (ps.executeUpdate() == 1) {
                    // 3. 차감 성공했으면 참여자 테이블의 입금 상태(is_paid)를 1로 변경
                    String sql3 = "UPDATE participant SET is_paid = 1 WHERE pno = ? AND uno = ?";
                    ps = conn.prepareStatement(sql3);
                    ps.setInt(1, pno); ps.setInt(2, uno);
                    ps.executeUpdate();

                    return 1; // 입금 성공
                } return 2; // 포인트 부족
            }
        } catch (Exception e) { e.printStackTrace(); }
        return 0; // 기타 오류 (이미 냈거나 등)
    }


}
