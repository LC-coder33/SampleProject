package com.example.practice.vo;

public class Pagevo {
	private int startNo;
	private int endNo;
	private int perPageNum=10;
	private Integer page; // Integer 웹에서 받은 페이지 정보(String)가 없으면 null인데 int는 null을 저장할 수 없다. 오류방지
	private int totalCount;
	private int endPage;
	private int startPage;
	private boolean prev;
	private boolean next;
	// 검색용 변수 2개 추가
	private String searchType;
	private String searchKeyword;

	public int getEndNo() {
		return endNo;
	}

	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	private void calcPage() {
		// Calculate the start number based on the current page
		startNo = (this.page - 1) * perPageNum + 1; // Starting number for the page

		// Calculate the tempEnd page (total pages)
		int totalPageCount = (int) Math.ceil((double) totalCount / perPageNum);
		int tempEnd = (int) Math.ceil((double) page / perPageNum) * perPageNum;

		// Calculate the start page based on tempEnd
		this.startPage = (tempEnd - perPageNum) + 1;

		// Ensure that endPage doesn't exceed the total pages available
		this.endPage = Math.min(tempEnd, totalPageCount);

		// Calculate endNo based on the last page
		this.endNo = startNo + perPageNum - 1;
		if (this.endNo > totalCount) {
			this.endNo = totalCount;  // Correct endNo if it exceeds totalCount
		}

		// Determine if there is a previous or next page
		this.prev = this.startPage > 1;
		this.next = this.endPage < totalPageCount;  // Correct check for next page
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcPage();// totalCount 전제게시물개수가 있어야지 페이지계산을 할 수 있기 때문에
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public int getStartNo() {

		return startNo;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}
}
