package com.example.convidados.model;

public class Feedback {

    public Feedback(String message){
        this.mMessage = message;
    }

    public Feedback(String message, boolean sucess){
        this.mMessage = message;
        this.mSucess = sucess;
    }

    public boolean isSucess(){
        return this.mSucess;
    }

    public String getMessage(){
        return this.mMessage;
    }

    private boolean mSucess = true;
    private String mMessage = "";
}
