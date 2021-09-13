package common;

public class UpdateCommand {
	private int num;
	private String title;
	private String content;
	private String confirmPassword;
	private Integer[] deleteNum;

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public Integer[] getDeleteNum() {
		return deleteNum;
	}
	public void setDeleteNum(Integer[] deleteNum) {
		this.deleteNum = deleteNum;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
