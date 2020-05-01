package kr.multi.bigdataShop.product.comment;

public class CommentAnalyzeDTO {
	private String word;
	private String hit;
	
	//toString
	@Override
	public String toString() {
		return "CommentAnalyzeDTO [word=" + word + ", hit=" + hit + "]";
	}

	//constructor
	public CommentAnalyzeDTO() {
	
	}
	public CommentAnalyzeDTO(String word, String hit) {
		super();
		this.word = word;
		this.hit = hit;
	}

	//getter & setter
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}
	
	
}
