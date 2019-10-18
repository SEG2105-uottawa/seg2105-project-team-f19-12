package com.samarthsaxena.walkinclinicapp;

public class Member {
    private String name;
    private  String pass;
    private  String rpass;
    private String typesel;

public Member() {

}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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