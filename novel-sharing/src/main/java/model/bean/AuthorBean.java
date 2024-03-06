package model.bean;

public class AuthorBean {
	
	private int authorId;
	private String authorName;
	private String furigana;
	
	public AuthorBean() {
		super();
	}

	public AuthorBean(int authorId, String authorName, String furigana) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.furigana = furigana;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getFurigana() {
		return furigana;
	}

	public void setFurigana(String furigana) {
		this.furigana = furigana;
	}


}
