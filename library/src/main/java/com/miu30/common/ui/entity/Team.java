package com.miu30.common.ui.entity;

public class Team {
    private String ZFDWMC;

    public String getZFDWMC() {
        return ZFDWMC;
    }

    public void setZFDWMC(String ZFDWMC) {
        this.ZFDWMC = ZFDWMC;
    }

    @Override
    public String toString() {
        return "Team{" +
                "ZFDWMC='" + ZFDWMC + '\'' +
                '}';
    }
}
