package com.hred.pagination;
import java.util.ArrayList;
import java.util.List;

/** * @author RAMMOHAN * */
public class Paginator<T> {
	private List<T> list;
	private long count;

	public Paginator() {
	}

	public Paginator(List<T> list, long count) {
		this.list = (list == null) ? new ArrayList<T>() : list;
		this.count = count;
	}

	public Paginator(Paginator<T> paginator) {
		this.list = (paginator.list == null) ? new ArrayList<T>()
				: paginator.list;
		this.count = paginator.count;
	}

	/** * @return the list */
	public List<T> getList() {
		return list;
	}

	/** * @param list * the list to set */
	public void setList(List<T> list) {
		this.list = list;
	}

	/** * @return the totalcount */
	public long getCount() {
		return count;
	}

	/** * @param totalcount * the totalcount to set */
	public void setCount(long count) {
		this.count = count;
	}
}
