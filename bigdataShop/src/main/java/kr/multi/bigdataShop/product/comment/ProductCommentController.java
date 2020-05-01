package kr.multi.bigdataShop.product.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductCommentController {
	
	@Autowired
	ProductCommentService service;
	
	@RequestMapping("/comment/write.do")
	public ModelAndView writeComment(ProductCommentDTO comment) {
		ModelAndView mav = new ModelAndView();
		
		int result = service.writeComment(comment);
		mav.addObject("result",result);
		mav.setViewName("redirect:/product/read.do?prd_no="+comment.getPrd_no());
		
		return mav;
	}
	
	@RequestMapping("/comment/result.do")
	public ModelAndView analyzeResult() {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("wordlist",service.callResult());
		mav.setViewName("comment/result");
		
		return mav;
	}
}
