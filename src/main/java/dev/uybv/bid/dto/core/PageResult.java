package dev.uybv.bid.dto.core;

import java.util.List;

public class PageResult<M> {
	private List<M> items;
	private int pageSize;
	private int currentPage;
	private int totalPages;
	private long totalItems;

	public List<M> getItems() {
		return items;
	}

	public void setItems(List<M> items) {
		this.items = items;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}
}
