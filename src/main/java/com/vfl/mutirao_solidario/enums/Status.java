package com.vfl.mutirao_solidario.enums;

public enum Status {

    ORGANIZING("organizing"),
    CONFIRMED("confirmed"),
    DONE("done"),
    CANCELED("canceled"),
    POSTPONED("postponed");

    private String status;

    Status(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
