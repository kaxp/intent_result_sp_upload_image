package com.example.kapil.intentresultspuploadimage.models;

public class Login {

    private String username;
    private String password;

    public Login(String username, String password){
        this.username = username;
        this.password = password;
    }

    public boolean validateLogin(){
        if(!this.username.isEmpty() && !this.password.isEmpty()){
            return true;
        }else {
            return false;
        }
    }


}
