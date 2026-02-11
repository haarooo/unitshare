package unitshare.view;

import unitshare.controller.ProductController;
import unitshare.model.dto.ProductDto;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductView {
    private ProductView(){}
    private static final ProductView instance = new ProductView();
    public static ProductView getInstance(){return instance;}
    //호출
    private ProductController pc = ProductController.getInstance();


    //20. 물품등록
    public void productAdd(){
        Scanner scan = new Scanner(System.in);
        System.out.print("제품명 : "); String pname = scan.nextLine();
        System.out.print("가격 : "); int pprice = scan.nextInt();
        scan.nextLine();
        System.out.print("설명 : "); String pcontent = scan.nextLine();
        System.out.print("인원수 : "); int people = scan.nextInt();
        scan.nextLine();
        System.out.print("오픈채팅링크 : "); String openchat = scan.nextLine();
        boolean result = pc.productAdd(pname, pprice ,pcontent ,people ,openchat);
        if(result){
            System.out.println("[안내]물픔등록 완료");
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
            System.out.printf("번호 : %d , 제품명 : %s , 가격 : %d , 설명 : %s , 인원수 : %d , 오픈채팅링크 : %s , 등록일 : %s"
                    ,product.getPno() , product.getPname(), product.getPprice(), product.getPcontent(), product.getPeople(), product.getOpenchat(), product.getPdate() );
        }
    }
    public void test2() {
        System.out.print("삭제하고싶은 게시물 숫자를 입력하세요.");
        int pno = scan.nextInt();
        System.out.print("비밀번호 입력:");
        int pwd = scan.nextInt();
        boolean result = pc.BoardCancel(pno,pwd);


    }
}
