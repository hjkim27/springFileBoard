package common;

public class UpdateCommand {
	private int num;
	private String title;
	private String content;
	private String password;
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	public Integer[] getDeleteNum() {
		return deleteNum;
	}


	public void setDeleteNum(Integer[] deleteNum) {
		this.deleteNum = deleteNum;
	}


	public boolean checkPassword() {
		if(password.equals(confirmPassword)) {
			return true;
		} else {
			return false;
		}
	}
}
