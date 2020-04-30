package com.miu30.common.ui.entity;

public class IdCardInfo {
    private String name;

    private String idCard;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public IdCardInfo(String name, String idCard){
        this.name = name;
        this.idCard = idCard;
    }

    public IdCardInfo() {
    }
}
