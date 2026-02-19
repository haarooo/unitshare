package unitshare.controller;

import unitshare.model.dao.ProductDao;
import unitshare.model.dto.ProductDto;

import java.util.ArrayList;

public class ProductController {
    // 싱글톤 생성
    private ProductController(){}
    private static final ProductController instance = new ProductController();
    public static ProductController getInstance(){return instance;}

    private ProductDao pd = ProductDao.getInstance();
    private UserController uc = UserController.getInstance();


    //20. 물품등록
    public boolean productAdd(String pname , int pprice , String pcontent , int people , String openchat){
        int uno = uc.getLoginSession();
        boolean result = pd.productAdd(pname, pprice, pcontent, people, openchat , uno);
        return result;
    }
    //21. 전체 공동구매 목록조회
    public ArrayList<ProductDto> findAll(){
        ArrayList<ProductDto> result = pd.findAll();
        return result;
    }

    //공동구매 취소
    public boolean  GroupCancel(int pno,String pwd) {
        boolean result = pd.GroupCancel(pno,pwd);
        if (result) {
            System.out.println("공동구매가 정상적으로 취소되었습니다.");
        } else {
            System.out.println("취소"); //인원이 다차서 취소할수없습니다로 바꿀예정
        }
        return result;
    }


    //자신이 등록한 물품 등록취소 함수
    public boolean BoardCancel(int pno, String pwd) {
        boolean result = pd.BoardCancel(pno, pwd);
        return result;
    }


    // 내 구매 신청 목록 조회(mylist)
    public ArrayList<ProductDto> mylist(){
        // 현재 로그인된 유저 번호를 가져온다.
        int loginNo = UserController.getInstance().getLoginSession();

        return pd.mylist(loginNo);
    }

    // 내가 등록한 물품 목록 조회
    public ArrayList<ProductDto> myUpList(){
        int loginNo = UserController.getInstance().getLoginSession();

        return pd.myuplist(loginNo);
    }

    //공동구매 신청
    public boolean groupBuying(int pno){
        int uno = uc.getLoginSession();
        if(uno == 0) return false;
        return pd.groupBuying(pno , uno);
    }


    // 1. 포인트 입금(전송) 컨트롤러
    public int payPoint(int pno , int uno) {
        return pd.payPoint(pno , uno);
    }

}
