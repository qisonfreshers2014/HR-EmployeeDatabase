package com.hred.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Nikitha
 */

@Entity
@Table(name = "ID_COUNTER")
public class IdCounter {

	public static final String NAME = "name";
	public IdCounter(){
		
	}
	
	public IdCounter(String name, long counter) {
		this.name = name;
		this.counter = counter;
	}

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	private String name;
	private long counter;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCounter() {
		return counter;
	}

	public void setCounter(long counter) {
		this.counter = counter;
	}

}
