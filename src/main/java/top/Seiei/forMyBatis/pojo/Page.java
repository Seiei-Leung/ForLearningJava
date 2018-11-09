package top.Seiei.forMyBatis.pojo;

/*
 *	用于分页查询
 *
 */

public class Page {

	private int pageSize; // 每页显示页数
	private int dataCount; // 数据库数据的总条数
	private int pagesCount; // 总页数
	private int pageIndex; // 第几页
	private int firstIndex; // 第某页的第一条数据对应数据库查询的索引

	public Page(int dataCount, int pageSize,int pageInex) {
		this.pageSize = pageSize;
		this.dataCount = dataCount;
		setPageIndex(pageInex);
	}
	
	public int getDataCount() {
		return dataCount;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPagesCount() {
		int isAddaPage = dataCount%pageSize > 0 ? 1 : 0;
		pagesCount = dataCount/pageSize + isAddaPage;
		return pagesCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		if (0<pageIndex && pageIndex<=pagesCount) {
			this.pageIndex = pageIndex;
		} else if (pageIndex <= 0) {
			this.pageIndex = 1;
		} else if (pageIndex > pagesCount) {
			this.pageIndex = pagesCount;
		}
	}

	public int getFirstIndex() {
		if (pageIndex <= 1) {
			firstIndex = 0;
		}
		else {
			firstIndex = (pageIndex-1) * pageSize + 1;
		}
		return firstIndex;
	}
}
