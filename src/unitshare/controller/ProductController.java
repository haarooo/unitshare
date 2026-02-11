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

}
