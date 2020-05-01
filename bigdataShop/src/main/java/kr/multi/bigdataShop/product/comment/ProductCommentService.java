package kr.multi.bigdataShop.product.comment;

import java.util.List;

public interface ProductCommentService {
	int writeComment(ProductCommentDTO comment);
	List<CommentAnalyzeDTO> callResult();
}
