package spring.data.mongodb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import spring.data.mongodb.dto.ScoreDTO;
import spring.data.mongodb.dto.ScoreRepository;

//spring-data 에서 지원되는 Repository와 mongoTemplate클래스를 이용해서 mongodb에 액세스하는 기능을 구현
@Repository
public class ScoreMongoDAOImpl implements ScoreMongoDAO{
	//페이징 처리를 편하게 하기위해 추가
	//CLRUD를 위한 기본 기능도 제공(spring-data의 common 라이브러리에서 지원하는 기능
	@Autowired
	ScoreRepository scoreRepository;
	
	//mongoDB를 연동하기 위한 기능을 제공
	//(spring-data의 common라이브러리에서 지원하는 기능)
	@Autowired
	MongoTemplate mongoTemplate;
	
	//매개변수 3개를 2개로 줄여서 보냈을때
	@Override
	public List<ScoreDTO> findCriteria(String key, String value) {
		String[] data = key.split(",");
		/*
		 * Query클래스의 addCriteria메소드와
		 * Criteria클래스의 where메소드의 활용
		 * Query query = new Query();
		 * //자기 자신의 클래스에 정보만 추가
		 * query.addCriteria(Criteria.where(data[0]).regex(value));
		 * 
		 */		
		
		Criteria criteria = new Criteria();
		
		/*
		 * Criteria org.springframework.data.mongodb.core.query.Criteria.andOperator(Criteria... criteria)
		 * 																			--------------
		 * 																			"..." : 해당 변수를 여러개 연결 가능하다. 
		 */
		criteria.andOperator(Criteria.where(data[0]).is(value),Criteria.where("addr").is("인천"));
		Query query = new Query(criteria);
		List<ScoreDTO> document = mongoTemplate.find(query, ScoreDTO.class, "score");
		return document;
	}
/*	@Override
	public List<ScoreDTO> findCriteria(String key, String value) {
		Criteria criteria = new Criteria(key);
		criteria.is(value);
		Query query = new Query(criteria);
		List<ScoreDTO> document = mongoTemplate.find(query, ScoreDTO.class, "score");
		return document;
	}
*/	
	@Override
	public List<ScoreDTO> findCriteria(String key, String value, String operator) {
		Criteria criteria = new Criteria(key);
		int intVal = Integer.parseInt(value);
		if(operator.equals(">")) { criteria.gt(intVal); }
		else if(operator.equals(">=")) {criteria.gte(intVal);}
		else if(operator.equals("<")) {criteria.lt(intVal);}
		else if(operator.equals("<=")) {criteria.lte(intVal);}
		else {criteria.is(intVal);}
		
		Query query = new Query(criteria);
		List<ScoreDTO> document = mongoTemplate.find(query, ScoreDTO.class,"score");
		return document;
	}

	//mongoDB가 json으로 모든 작업을 처리하므로 key,value로 조건을 정의
	//spring-data-mongodb에서 이러한 조건을 처리하는 객체를 만들어서 제공
	//Criteria : 조건 처리하는 기능을 제공하는 클래스
	@Override
	public ScoreDTO findById(String key, String value) {
		// 1. 조건을 객체로 작성
		Criteria criteria = new Criteria(key);
		// 2. 조건에 대한 설정 - value를 셋팅
		criteria.is(value);
		// 3. Criteria를 이용해서 Query 객체를 작성 - mongodb 내부에서 인식할 조건을 정의하는 객체
		Query query = new Query(criteria);
		// 4. MongoTemplate 클래스의 메소드를 호출하며 Query객체를 매개변수로 전달
		//query, DTO, collection_name
		ScoreDTO document = mongoTemplate.findOne(query, ScoreDTO.class,"score");
		return document;
	}

	@Override
	public List<ScoreDTO> findAll() {
		List<ScoreDTO> mongolist = (List<ScoreDTO>)scoreRepository.findAll();
		System.out.println("size : "+mongolist.size());
		return mongolist;
	}

	@Override
	public List<ScoreDTO> findAll(int pageNo) {
		//PagingAndSortingRepository에서 제공하는 findAll을 호출하면 spring-data 내부에서 페이징 처리가 된 객체를 전달 받을 수 있다.
		//페이징 처리를 내부에서 할 수 있도록 필요한 정보를 findAll메소드에서 Pageable타입의 매개변수로 전달받는다.
		//[정보 전달]
		//Pageable을 구현하고 있는 PageRequest객체를 넘겨준다.
		//					 ---------------
		//					현재 페이지 번호(page), size(화면에 표시할 게시글 갯수)
		//findAll이 처리되면 페이징된 객체를 Page타입으로 리턴한다.
		//PageRequest에 전달한 size와 pageNo를 기준으로 조회한 데이터를 맵핑해서 넘겨준다.
		Page<ScoreDTO> pagelist = scoreRepository.findAll(new PageRequest(pageNo, 5));
		//getContent() : page객체가 갖고있는 paging된 list객체를 반환
		List<ScoreDTO> mongolist = pagelist.getContent();
		return mongolist;
	}

	@Override
	public void insertDocument(ScoreDTO doc) {
		mongoTemplate.insert(doc);
	}

	@Override
	public void insertAllDocument(List<ScoreDTO> docs) {
		mongoTemplate.insertAll(docs);
	}

	@Override
	public void update(ScoreDTO document) {
		Criteria criteria = new Criteria("id");
		criteria.is(document.getId());
		Query query = new Query(criteria);
		
		//수정될 데이터를 셋팅 - RDBMS의 set절 (직접 update라는 객체를 제공해줌)
		Update update = new Update();
		update.set("addr", document.getAddr());
		update.set("dept", document.getDept());

		//mongoTemplate의 수정기능 호출
		// updateFirst : multi option을 주지 않는다. -> 처음 만나는 document만 수정
		// updateMulti : multi option을 준다. -> 모든 document만 수정
		mongoTemplate.updateMulti(query, update, ScoreDTO.class);
	}

}
