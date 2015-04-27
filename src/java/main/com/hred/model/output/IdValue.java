package com.hred.model.output;


import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by IntelliJ IDEA.
 * User: Vamsi Kuchi
 * Date: 7/22/14
 * Time: 7:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class IdValue {
    
    private long id;
    private String value;

    public IdValue() {
    }

    public IdValue(long id, String value) {
      this.id = id;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
