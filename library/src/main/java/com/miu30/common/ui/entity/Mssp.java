package com.miu30.common.ui.entity;

public class Mssp {

    private String ZFZH;

    private String MSSPID;

    public String getZFZH() {
        return ZFZH;
    }

    public void setZFZH(String ZFZH) {
        this.ZFZH = ZFZH;
    }

    public String getMSSPID() {
        return MSSPID;
    }

    public void setMSSPID(String MSSPID) {
        this.MSSPID = MSSPID;
    }

    @Override
    public String toString() {
        return "Mssp{" +
                "ZFZH='" + ZFZH + '\'' +
                ", MSSPID='" + MSSPID + '\'' +
                '}';
    }
}
