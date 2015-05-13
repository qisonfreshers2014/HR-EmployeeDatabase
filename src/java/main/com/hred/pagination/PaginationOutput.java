package com.hred.pagination;
import java.util.List;
/**
* @author Nikitha
*
*/
public class PaginationOutput<T> extends Paginator<T> {
private int start;
private int end;
private int pageNo;
public PaginationOutput() {
}
public PaginationOutput(List<T> list, long count, int pageNo, int pageSize) {
super(list, count);
this.pageNo = pageNo;
this.start = (this.getList().size() == 0) ? 0
: ((this.pageNo - 1) * pageSize) + 1;
this.end = (this.start == 0) ? 0
: (this.start + this.getList().size() - 1);
}
public PaginationOutput(Paginator<T> paginator, int pageNo, int pageSize) {
super(paginator);
this.pageNo = pageNo;
this.start = (this.getList().size() == 0) ? 0
: ((this.pageNo - 1) * pageSize) + 1;
this.end = (this.start == 0) ? 0
: (this.start + this.getList().size() - 1);
}
/**
* @return the start
*/
public int getStart() {
return start;
}
/**
* @param start
* the start to set
*/
public void setStart(int start) {
this.start = start;
}
/**
* @return the end
*/
public int getEnd() {
return end;
}
/**
* @param end
* the end to set
*/
public void setEnd(int end) {
this.end = end;
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
}