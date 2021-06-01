package com.twovet.base.common;

public class ParamBean<T> {
	
	private String currentPage;
	private T data;
	private SearchAdvanceParamBean<T> advanceParam;
	
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public SearchAdvanceParamBean<T> getAdvanceParam() {
		return advanceParam;
	}
	public void setAdvanceParam(SearchAdvanceParamBean<T> advanceParam) {
		this.advanceParam = advanceParam;
	}
	
	
}
