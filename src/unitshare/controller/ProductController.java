package unitshare.controller;

import unitshare.model.dao.ProductDao;

public class ProductController {
    // 싱글톤 생성
    private ProductController(){}
    private static final ProductController instance = new ProductController();
    public static ProductController getInstance(){return instance;}

    private ProductDao pd = ProductDao.getInstance();

}
