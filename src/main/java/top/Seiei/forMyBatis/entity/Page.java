package top.Seiei.forMyBatis.entity;

/*
 *	用于分页查询，主要是为了拦截器服务
 *	在获取数据库数据的总条数时，重新刷新属性
 *
 */

public class Page {

	private int pageSize; // 每页显示页数
	private int dataCount; // 数据库数据的总条数
	private int pagesCount; // 总页数
	private int pageIndex; // 当前第几页
	private int firstIndex; // 第某页的第一条数据对应数据库查询的索引

	public Page(int pageSize, int pageIndex) {
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
		int isAddOne = dataCount % pageSize > 0 ? 1 : 0;
		this.pagesCount = dataCount / pageSize + isAddOne;
		if (this.pagesCount <= 0) {
			this.pagesCount = 1;
		}
		if (this.pageIndex > this.pagesCount) {
			this.pageIndex = this.pagesCount;
		}
		this.firstIndex = (this.pageIndex - 1) * pageSize;
	}

	public int getPagesCount() {
		return pagesCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getDataCount() {
		return dataCount;
	}

	public void setPagesCount(int pagesCount) {
		this.pagesCount = pagesCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}
}
