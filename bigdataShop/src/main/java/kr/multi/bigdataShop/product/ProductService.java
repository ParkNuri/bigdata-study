package kr.multi.bigdataShop.product;

import java.util.List;

import kr.multi.bigdataShop.product.comment.ProductCommentDTO;

public interface ProductService {
	List<ProductDTO> productlist(String category);
	List<ProductDTO> hitproduct();
	List<ProductDTO> newproduct();
	ProductDTO read(String prd_no);
	List<ProductCommentDTO> commentList(String prd_no);
}
