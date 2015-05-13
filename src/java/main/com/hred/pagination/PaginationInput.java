package com.hred.pagination;


/**
* @author Nikitha
*
*/
public class PaginationInput {
private int pageNo;
private int pageSize;
public PaginationInput() {
}
public PaginationInput(int pageNo, int pageSize) {
this.pageNo = pageNo;
this.pageSize = pageSize;
}
/**
* @return the pageNo
*/
public int getPageNo() {
return pageNo;
}
/**
* @param pageNo
* the pageNo to set
*/
public void setPageNo(int pageNo) {
this.pageNo = pageNo;
}
/**
* @return the pageSize
*/
public int getPageSize() {
return pageSize;
}
/**
* @param pageSize
* the pageSize to set
*/
public void setPageSize(int pageSize) {
this.pageSize = pageSize;
}
}