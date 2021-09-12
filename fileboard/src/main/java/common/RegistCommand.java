package common;

public class RegistCommand {
	private Integer num;
	private String writer;
	private String title;
	private String content;
	private String password;
	private int ref;
	private int depth;
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public boolean isAnswer() {
		if(num!=null && num!=0) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public String toString() {
		return "RegistCommand [num=" + num + ", writer=" + writer + ", title=" + title + ", content=" + content
				+ ", password=" + password + ", ref=" + ref + ", depth=" + depth + "]";
	}
	
	
}
