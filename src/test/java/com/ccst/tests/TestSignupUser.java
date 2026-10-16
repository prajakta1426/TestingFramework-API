package com.ccst.tests;

import com.ccst.data.SignupUser;

public class TestSignupUser {
    public static void main(String[] args) {
        SignupUser asha = SignupUser.builder().name("asha").build();
        System.out.println(asha);
        System.out.println(asha.password());

        SignupUser jeet = SignupUser.builder().name("jeet").email("abc@gmail.com").build();
        System.out.println(jeet);
    }
}
