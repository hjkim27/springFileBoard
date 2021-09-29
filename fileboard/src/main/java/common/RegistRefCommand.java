package common;

public class RegistRefCommand {
	private int bNum;
	private String writer;
	private String content;
	private int ref;
	private Integer depth;
	
	public int getbNum() {
		return bNum;
	}
	public void setbNum(int bNum) {
		this.bNum = bNum;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	public boolean isNewReply() {
		if(depth!=null && depth==0) {
			return false;
		}else {
			return true;
		}
	}
}
