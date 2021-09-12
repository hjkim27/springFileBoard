package common;

import java.sql.Timestamp;

public class DeleteCommand {
	private int num;
	private String password;
	private String confirmPassword;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
	
	public boolean checkPassword() {
		if(password.equals(confirmPassword)) {
			return true;
		} else {
			return false;
		}
	}
	
}
