package com.miu30.common.ui.entity;

public class PrinterInfo {
    /**
     * MODEL : 华为
     * VERSION : 1.2.0
     * SN : 10000
     * PRINTLOG : addPrinterLog
     * FLAG : 65432346
     */

    private String MODEL;
    private String VERSION;
    private String SN;
    private String PRINTLOG;
    private String FLAG;
    private String ANROID_VERSION;
    private String ZFZH;
    private String DEVICE_ID;

    public String getMODEL() {
        return MODEL;
    }

    public void setMODEL(String MODEL) {
        this.MODEL = MODEL;
    }

    public String getVERSION() {
        return VERSION;
    }

    public void setVERSION(String VERSION) {
        this.VERSION = VERSION;
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    public String getPRINTLOG() {
        return PRINTLOG;
    }

    public void setPRINTLOG(String PRINTLOG) {
        this.PRINTLOG = PRINTLOG;
    }

    public String getFLAG() {
        return FLAG;
    }

    public void setFLAG(String FLAG) {
        this.FLAG = FLAG;
    }

    public String getANROID_VERSION() {
        return ANROID_VERSION;
    }

    public void setANROID_VERSION(String ANROID_VERSION) {
        this.ANROID_VERSION = ANROID_VERSION;
    }

    public String getZFZH() {
        return ZFZH;
    }

    public void setZFZH(String ZFZH) {
        this.ZFZH = ZFZH;
    }

    public String getDEVICE_ID() {
        return DEVICE_ID;
    }

    public void setDEVICE_ID(String DEVICE_ID) {
        this.DEVICE_ID = DEVICE_ID;
    }
}
