package com.ccst.data;

import java.util.Map;

//record is a lightweight version of a class which only contains data
public record TestUser(String email, String password,String expectedMessage) {

    public static TestUser fromRow(Map<String,String>row){
        return new TestUser(row.get("email"), row.get("password"), row.get("expected") );

    }

    @Override
    public String toString(){
        return email;
    }
}
