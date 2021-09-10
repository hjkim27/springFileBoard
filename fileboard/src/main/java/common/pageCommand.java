package common;

public class pageCommand {
	private int currentPage;
	private int start;
	private int end;
	private int count;
	private int pageSize;
	private int number;
	
	public pageCommand(int currentPage, int start, int end, int count, int pageSize, int number) {
		super();
		this.currentPage = currentPage;
		this.start = start;
		this.end = end;
		this.count = count;
		this.pageSize = pageSize;
		this.number = number;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
}
