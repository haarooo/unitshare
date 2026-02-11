package unitshare.view;

import unitshare.controller.ProductController;

import java.util.Scanner;

public class ProductView {
    private ProductView(){}
    private static final ProductView instance = new ProductView();
    public static ProductView getInstance(){return instance;}
    //호출
    private ProductController pc = ProductController.getInstance();


    //테스트용/////////////////////////////////////////////////////
    Scanner scan = new Scanner(System.in);
    public void test() {
        System.out.print("숫자를 입력;");
        int pno = scan.nextInt();
        boolean result=pc.GroupCancel(pno);
    }
    ///////////////////////////////////////////////////////////////////////

    public void test2() {
        System.out.print("삭제하고싶은 게시물 숫자를 입력하세요.");
        int pno = scan.nextInt();
        System.out.print("비밀번호 입력:");
        int pwd = scan.nextInt();
        boolean result = pc.BoardCancel(pno,pwd);


    }
}
