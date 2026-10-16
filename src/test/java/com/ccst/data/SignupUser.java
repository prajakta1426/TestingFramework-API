package com.ccst.data;

public record SignupUser(String name,String email, String password, String country) {

    public static Builder builder(){return new Builder();}

    public static final class Builder
    {
        private String name = "CCST Student";
        private String email = "ccst."+System.currentTimeMillis()+"@example.com";
        private String password = "Test@1234";
        private String country = "India";

        public  Builder name(String v){name=v;return this;}
        public  Builder email(String v){email=v;return this;}

        public SignupUser build()
        {
            return new SignupUser(name,email,password,country);
        }
    }
}
