package edu.ccrm.domain;

import java.time.LocalDateTime;

public abstract class Person {
    protected final String id;
    protected String fullName;
    protected final LocalDateTime createdAt;
    protected Person(String id, String fullName){
        assert id != null;
        this.id = id;
        this.fullName = fullName;
        this.createdAt = LocalDateTime.now();
    }
    public String getId(){ return id; }
    public String getFullName(){ return fullName; }
    public void setFullName(String name){ this.fullName = name; }
    public abstract String profile();
}
