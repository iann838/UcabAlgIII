package com.ucabalg3.taller2;

public abstract class Print {

    private String title;
    private long pageNum;
    
    public Print() {
    	super();
    }

    public Print(String title, long pageNum) {
		super();
		this.title = title;
		this.pageNum = pageNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getPageNum() {
		return pageNum;
	}

	public void setPageNum(long pageNum) {
		this.pageNum = pageNum;
	}

	@Override
	public abstract String toString();

	public static Print fromStdin() { return null; }

	public static String tableHead() { return null; }

	public abstract String tableRow();

}
