package unitshare.view;

import unitshare.controller.ProductController;
import unitshare.model.dto.ProductDto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductView {
    private ProductView(){}
    private static final ProductView instance = new ProductView();
    public static ProductView getInstance(){return instance;}
    //호출
    private ProductController pc = ProductController.getInstance();

    //로그인 이후 페이지
    public void index2() {
        for (; ; ) {
            try {
                System.out.println("================================ Unit share for solo ================================");
                System.out.println("1.로그아웃 2. 물품 등록 3.전체 공동구매 목록 조회 및 신청 4. 내 구매 신청 목록 조회 5.내가 등록한 물품 글 삭제" +
                        "6.참여한 공동구매 취소");
                System.out.println("=====================================================================================");
                System.out.println("선택>");
                int ch = scan.nextInt();
                if (ch == 1) {UserView.getInstance().logout();}
                else if (ch == 2) {productAdd();}
                else if (ch == 3) {findAll();}
                else if (ch == 4) {mylist();}
                else if (ch == 5) {GroupCancel();}
                else if(ch==6){}
                else {System.out.println("[경고] 없는 기능 번호입니다.");}
            } catch (InputMismatchException e) {
                System.out.println("[경고] 잘못된 입력 방식입니다.");
                scan = new Scanner(System.in); // 입력객체 초기화
            } catch (Exception e) {
                System.out.println("[시스템 오류] 관리자에게 문의하십시오.");
            }
        }
    }


    //20. 물품등록
    public void productAdd(){
        Scanner scan = new Scanner(System.in);
        System.out.print("제품명 : "); String pname = scan.nextLine();
        System.out.print("가격 : "); int pprice = scan.nextInt();
        scan.nextLine();
        System.out.print("설명 : "); String pcontent = scan.nextLine();
        System.out.print("인원수 : "); int people = scan.nextInt();
        if(people > 5){System.out.println("최대 4명까지 가능합니다");return;}
        scan.nextLine();
        System.out.print("오픈채팅링크 : "); String openchat = scan.nextLine();
        boolean result = pc.productAdd(pname, pprice ,pcontent ,people ,openchat);
        if(result){
            System.out.println("[안내] 물품등록 완료");
        }else{
            System.out.println("[경고] 물품등록 실패");
        }
    }//product Add e
    //테스트용/////////////////////////////////////////////////////
    Scanner scan = new Scanner(System.in);
    public void test() {
        System.out.print("숫자를 입력;");
        int pno = scan.nextInt();
        boolean result=pc.GroupCancel(pno);
    }
    ///////////////////////////////////////////////////////////////////////

    //21 전체 공동구매 목록조회
    public void findAll(){
        ArrayList<ProductDto> products = pc.findAll();
        for(ProductDto product : products){
            int cpeople =product.getPeople() - product.getCpeople();
            if(cpeople <0 ){cpeople = 0;}
            System.out.printf("번호 : %d , 제품명 : %s , 가격 : %d , 설명 : %s , 인원수 : %d(남은자리)/%d(총인원) , 오픈채팅링크 : %s , 등록일 : %s \n"
                    ,product.getPno() , product.getPname(), product.getPprice(), product.getPcontent(), cpeople, product.getPeople(), product.getOpenchat(), product.getPdate() );
        }
        System.out.println("======================");
        System.out.print("신청할 공동구매 목록 번호 : "); int apply = scan.nextInt();
        if(pc.groupBuying(apply)){
            System.out.println("[안내] 신청 성공");
        }else{
            System.out.println("[경고]신청실패(인원수가 다 찼습니다)");
        }
    }
    public void GroupCancel() {
        ArrayList<ProductDto> products = pc.findAll();
        for(ProductDto product : products){
            System.out.printf("번호 : %d , 제품명 : %s , 가격 : %d , 설명 : %s , 인원수 : %d , 오픈채팅링크 : %s , 등록일 : %s\n"
                    ,product.getPno() , product.getPname(), product.getPprice(), product.getPcontent(), product.getPeople(), product.getOpenchat(), product.getPdate() );
        }
        System.out.print("삭제하고싶은 게시물 숫자를 입력하세요.");
        int pno = scan.nextInt();
        System.out.print("비밀번호 입력:");
        String pwd = scan.next();
        boolean result = pc.BoardCancel(pno,pwd);
    }

    // 내 구매 신청 목록 조회
    public void mylist(){
        ArrayList<ProductDto> products = pc.mylist();

        System.out.println("========================== 내 구매 신청 목록 ==========================");
        for(ProductDto product : products){
            System.out.printf(" 번호 : %d , 제품명 : %s , 가격 : %d , 등록일 : %s , 오픈채팅방링크 : %s \n",
                    product.getPno() , product.getPname() , product.getPprice() , product.getPdate() , product.getOpenchat());
        }
        System.out.println("====================================================================");
    }
} // class END
