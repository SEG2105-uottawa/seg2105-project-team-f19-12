package com.samarthsaxena.walkinclinicapp;

public class Member {
    private String username;
    private  String pass;
    private  String rpass;
    private String typesel;
    private String email;

public Member() {

}


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRpass() {
        return rpass;
    }

    public void setRpass(String rpass) {
        this.rpass = rpass;
    }

    public String getTypesel() {
        return typesel;
    }

    public void setTypesel(String typesel) {
        this.typesel = typesel;
    }
}