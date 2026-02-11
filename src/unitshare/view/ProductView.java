package unitshare.view;

import unitshare.controller.ProductController;

public class ProductView {
    private ProductView(){}
    private static final ProductView instance = new ProductView();
    public static ProductView getInstance(){return instance;}
    //호출
    private ProductController pc = ProductController.getInstance();




}
