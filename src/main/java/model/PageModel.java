package main.java.model;


public class PageModel {

	private int currentPage;
	
	private int pageSize;//display records each page
	
	private int totalPage;
	
	private int totalRecord;
	
	
	private PageModel(){
		
	}
	
	private PageModel(final int pageSize,final int page,final int totalRecord){
		
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		setTotalPage();
		setCurrentPage(page);
		
	}
	
	public static PageModel newPageModel(final int pageSize,final int page,final int totalRecord){
		return new PageModel(pageSize, page, totalRecord);
	}

	public void setCurrentPage(int page){
		
		currentPage = page;

		if(currentPage<1){
			currentPage =1;
		}
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
	}
	
	private void setTotalPage(){
		if(totalRecord%pageSize==0){
			totalPage = totalRecord/pageSize;
		}else{
			totalPage = totalRecord/pageSize+1;
		}
	}
	
	public int getOffset(){
		return (currentPage-1)*pageSize;
	}
	
	public int getFirst(){
		return 1;
	}
	
	public int getPrevious(){
		return currentPage-1;
	}
	
	public int getNext(){
		return currentPage+1;
	}
	
	public int getLast(){
		return totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}


}
