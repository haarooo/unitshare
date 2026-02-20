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
                System.out.println("\n[ UNIT SHARE FOR SOLO ]");
                System.out.println("--------------------------------------------------");
                System.out.print(" 1.로그아웃 🏠   ");
                System.out.print(" 2.물품등록 📦   ");
                System.out.println(" 3.구매신청 🛒");

                System.out.print(" 4.공구신청목록 📜   ");
                System.out.print(" 5.내가등록한물품 📋   ");
                System.out.println(" 6.내가올린글삭제 ❌");
                System.out.println(" 7.구매취소 🚫");
                System.out.println("--------------------------------------------------");
                System.out.print("선택 > ");
                int ch = scan.nextInt();
                if (ch == 1) {UserView.getInstance().logout();}
                else if (ch == 2) {productAdd();}
                else if (ch == 3) {findAll();}
                else if (ch == 4) {mylist();}
                else if (ch == 5) {myUpList();}
                else if(ch==6){BoardCancel();}
                else if(ch==7){GroupCancel();}
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


    Scanner scan = new Scanner(System.in);

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
        System.out.print("신청할 공동구매 목록 번호(뒤로가기 0) : "); int apply = scan.nextInt();
        if(apply == 0){index2();
        }int result = pc.groupBuying(apply);
        if(result ==1 ){
            System.out.println("공동구매 신청 성공");
        }else if (result == 2) {
            System.out.println("[경고] 본인이 등록한 물품은 신청할 수 없습니다.");
        } else if (result == 3) {
            System.out.println("[경고] 이미 신청한 물품입니다.");
        } else if (result == 4) {
            System.out.println("[경고] 신청 실패: 모집 인원이 이미 가득 찼습니다.");
        } else {
            System.out.println("[오류] 알 수 없는 이유로 신청에 실패했습니다.");
        }
    }


    //공동구매 참여취소
    public void GroupCancel() {
        ArrayList<ProductDto> products = pc.mylist();

        System.out.println("========================== 내 구매 신청 목록 ==========================");
        for(ProductDto product : products){
            System.out.printf(" 번호 : %d , 제품명 : %s , 가격 : %d , 등록일 : %s , 오픈채팅방링크 : %s \n",
                    product.getPno() , product.getPname() , product.getPprice() , product.getPdate() , product.getOpenchat());
        }
        System.out.println("====================================================================");
        System.out.print("취소하고싶은 게시물 번호를 입력하세요.");
        int pno = scan.nextInt();
        System.out.print("비밀번호 입력:");
        String pwd = scan.next();
        boolean result = pc.GroupCancel(pno,pwd);
    }


    //내가 올린 게시물 삭제
    public void BoardCancel() {
        ArrayList<ProductDto> products = pc.myUpList();

        System.out.println("========================== 내가 등록한 물품 목록 ==========================");
        for(ProductDto product : products){
            System.out.printf(" 번호 : %d , 제품명 : %s , 가격 : %d , 등록일 : %s , 오픈채팅방링크 : %s \n",
                    product.getPno() , product.getPname() , product.getPprice() , product.getPdate() , product.getOpenchat());
        }
        System.out.println("====================================================================");

        System.out.println("삭제할 게시물 번호를 입력해주세요.");
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

    // 내가 등록한 물품 목록 조회
    public void myUpList(){
        ArrayList<ProductDto> products = pc.myUpList();

        System.out.println("========================== 내가 등록한 물품 목록 ==========================");
        for(ProductDto product : products){
            System.out.printf(" 번호 : %d , 제품명 : %s , 가격 : %d , 등록일 : %s , 오픈채팅방링크 : %s \n",
                    product.getPno() , product.getPname() , product.getPprice() , product.getPdate() , product.getOpenchat());
        }
        System.out.println("====================================================================");
    }



    public void productDetail(int pno , int  uno){

        System.out.println("1. 입금 | 2. 거래완료 | 0. 되돌아가기");
        System.out.print("선택 > ");
        int ch = scan.nextInt();

        if (ch == 0) { return; }
        if (ch == 1) {
            int result = pc.payPoint(pno , uno);
            if(result == 1) {
                System.out.println("[안내] 입금 성공! 상태가 갱신되었습니다.");
            } else if(result == 2) {System.out.println("[경고] 잔액 부족");
            } else {System.out.println("[경고] 이미 입금했거나 처리할 수 없는 상태입니다.");}

        }

    }

} // class END
