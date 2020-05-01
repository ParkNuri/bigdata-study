package kr.multi.bigdataShop.product.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProductCommentServiceImpl implements ProductCommentService{
	@Autowired
	@Qualifier("productcommentdao")
	ProductCommentDAO dao;
	
	public int writeComment(ProductCommentDTO comment) {
		int result = dao.writeComment(comment);
		
		return result;
	}

	@Override
	public List<CommentAnalyzeDTO> callResult() {
		return dao.callResult();
	};
}
