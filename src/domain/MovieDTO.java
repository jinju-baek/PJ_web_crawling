package domain;

public class MovieDTO {
	private String bno;
	private String title;
	private String content;
	private String writer;
	private int score;
	private String loc;
	private int regdate;
	
	public MovieDTO() {}

	public MovieDTO(String bno, String title, String content, String writer, int score, String loc, int regdate) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.score = score;
		this.loc = loc;
		this.regdate = regdate;
	}

	public MovieDTO(String title, String content, String writer, int score, String loc, int regdate) {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.score = score;
		this.loc = loc;
		this.regdate = regdate;
	}

	public String getBno() {
		return bno;
	}

	public void setBno(String bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public int getRegdate() {
		return regdate;
	}

	public void setRegdate(int regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "MovieDTO [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer + ", score="
				+ score + ", loc=" + loc + ", regdate=" + regdate + "]";
	}
	
	
	
}
