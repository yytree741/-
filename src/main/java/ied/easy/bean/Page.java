package ied.easy.bean;

import java.util.List;
import java.util.Map;

public class Page<T> {
	private T o;
	//总页数，通过总记录数和每页记录数计算得到
	private Integer totalPageCount = 0;
	//每页记录数，默认为20
	private Integer pageSize = 20;
	//总记录数
	private Integer totalCount = 0;
	//当前页码，默认为1
	private Integer currentPage = 1;
	//当前页的记录列表
	private List<T> list;
	//分页从第几条记录开始查询
	private Integer offset;
	//排序
	private String order;
	//额外的查询字段
	private Map<String,Object> ext;

	public T getO() {
		return o;
	}

	public void setO(T o) {
		this.o = o;
	}

	public Integer getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(Integer totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Map<String, Object> getExt() {
		return ext;
	}

	public void setExt(Map<String, Object> ext) {
		this.ext = ext;
	}
}
