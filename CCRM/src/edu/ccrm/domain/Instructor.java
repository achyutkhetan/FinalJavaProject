package edu.ccrm.domain;

public class Instructor extends Person {
    private String email;
    public Instructor(String id, String name, String email){
        super(id, name);
        this.email = email;
    }
    @Override
    public String profile(){
        return String.format("Instructor[id=%s, name=%s, email=%s]", id, fullName, email);
    }
}
