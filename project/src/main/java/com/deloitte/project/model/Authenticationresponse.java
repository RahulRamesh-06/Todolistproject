package com.deloitte.project.model;

public class Authenticationresponse {

    private final String jwt;

    public String getJwt() {
        return jwt;
    }

    public Authenticationresponse(String jwt) {
        this.jwt=jwt;
    }
}
