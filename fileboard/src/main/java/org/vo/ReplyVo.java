package org.vo;

import java.sql.Timestamp;

public class ReplyVo {
	private int num;
	private int bNum;
	private String writer;
	private String content;
	private Timestamp regdate;
	private int depth;
	private int ref;
	private int step;
	
	public ReplyVo() {}

	public ReplyVo(int num, int bNum, String writer, String content, Timestamp regdate, int depth, int ref) {
		super();
		this.num = num;
		this.bNum = bNum;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
		this.depth = depth;
		this.ref = ref;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

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

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getStep() {
		return step;
	}
	
	public void setStep(int step) {
		this.step = step;
	}

	@Override
	public String toString() {
		return "ReplyVo [num=" + num + ", bNum=" + bNum + ", writer=" + writer + ", content=" + content + ", regdate="
				+ regdate + ", depth=" + depth + ", ref=" + ref + ", step=" + step + "]";
	}
	
}
