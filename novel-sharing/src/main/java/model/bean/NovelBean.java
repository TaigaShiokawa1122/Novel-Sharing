package model.bean;

import java.sql.Timestamp;

public class NovelBean {

	private AuthorBean author;
	private GenreBean genre;
	private int novelId;
	private String title;
	private String summary;
	private Timestamp posted_at;
	private String image;

	public NovelBean() {
		super();
	}

	public NovelBean(AuthorBean author, GenreBean genre, int novelId, String title, String summary, Timestamp posted_at,
			String image) {
		super();
		this.author = author;
		this.genre = genre;
		this.novelId = novelId;
		this.title = title;
		this.summary = summary;
		this.posted_at = posted_at;
		this.image = image;
	}

	public AuthorBean getAuthor() {
		return author;
	}

	public void setAuthor(AuthorBean author) {
		this.author = author;
	}

	public GenreBean getGenre() {
		return genre;
	}

	public void setGenre(GenreBean genre) {
		this.genre = genre;
	}

	public int getNovelId() {
		return novelId;
	}

	public void setNovelId(int novelId) {
		this.novelId = novelId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Timestamp getPosted_at() {
		return posted_at;
	}

	public void setPosted_at(Timestamp posted_at) {
		this.posted_at = posted_at;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
