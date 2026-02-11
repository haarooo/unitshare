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


    //20. 물품등록
    public boolean productAdd(String pname , int pprice , String pcontent , int people , String openchat){
        boolean result = pd.productAdd(pname, pprice, pcontent, people, openchat);
        return result;
    }
    //21. 전체 공동구매 목록조회
    public ArrayList<ProductDto> findAll(){
        ArrayList<ProductDto> result = pd.findAll();
        return result;
    }

    //공동구매 취소
    public boolean  GroupCancel(int pno) {
        boolean result = pd.GroupCancel(pno);
        if (result) {
            System.out.println("공동구매가 정상적으로 취소되었습니다.");
        } else {
            System.out.println("취소"); //인원이 다차서 취소할수없습니다로 바꿀예정
        }
        return result;
    }

    public boolean BoardCancel(int pno, int pwd) {
        boolean result = pd.BoardCancel(pno, pwd);
        return result;
    }
}
