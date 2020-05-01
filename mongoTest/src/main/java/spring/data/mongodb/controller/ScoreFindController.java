package spring.data.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.data.mongodb.dto.ScoreDTO;
import spring.data.mongodb.service.ScoreMongoService;

@Controller
public class ScoreFindController {
	
	@Autowired
	ScoreMongoService service;
	
	@RequestMapping(value="/score/search", method=RequestMethod.GET)
	public String searchPage() {
		
		return "search";
	}

	//매개변수 3개를 2개로 만들어서 넘기기 (매개변수가 많을때) 
	@RequestMapping(value="/score/search", method=RequestMethod.POST)
	public ModelAndView search(String field, String value, String operator) {
		List<ScoreDTO> doclist = null;
	
		doclist = service.findCriteria(field+","+operator, value);
		return new ModelAndView("list", "mongolist", doclist);
	}
/*	@RequestMapping(value="/score/search", method=RequestMethod.POST)
	public ModelAndView search(String field, String value, String operator) {
		List<ScoreDTO> doclist = null;
		if(field.equals("java")||field.equals("spring")||field.equals("servlet")||field.equals("bonus")) {
			
			doclist = service.findCriteria(field, value, operator);
		}else {			
			doclist = service.findCriteria(field, value);
		}
		return new ModelAndView("list", "mongolist", doclist);
	}
*/
	//update와 read를 위해 필요한 컨트롤러
	@RequestMapping(value="/score/detail", method=RequestMethod.GET)
	public ModelAndView findById(String key, String value, String action) {
		ScoreDTO doc = service.findById(key, value);
		String view = "";
		if(action.equals("READ")) {
			view = "mongo_detail";
		}
		else {
			view = "mongo_update";
		}
		return new ModelAndView(view, "document", doc);
	}
}
