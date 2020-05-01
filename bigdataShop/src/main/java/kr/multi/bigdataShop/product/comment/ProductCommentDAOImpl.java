package kr.multi.bigdataShop.product.comment;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("productcommentdao")
public class ProductCommentDAOImpl implements ProductCommentDAO{
	
	@Autowired
	SqlSession sqlSession;
	
	public int writeComment(ProductCommentDTO comment) {
		
		int result = sqlSession.insert("kr.multi.bigdataShop.product.comment.writeComment", comment);
		
		return result;
	}

	@Override
	public List<CommentAnalyzeDTO> callResult() {
		return sqlSession.selectList("kr.multi.bigdataShop.product.comment.analyzeresult");
	}
	
	
}
