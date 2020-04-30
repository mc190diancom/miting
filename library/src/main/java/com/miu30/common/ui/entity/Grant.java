package com.miu30.common.ui.entity;

public class Grant {
    /**
     * ID : e60a5d49ce43499ca6b1162dad3135e7
     * MASTER : 00001
     * SLAVE : 11130123
     * ENDUTC : 2019-08-10 14:22:02
     * CURRNETUTC: 1563724800
     * STATE : 1
     */

    private String ID;
    private String MASTER;
    private String SLAVE;
    private String ENDUTC;
    private String CURRNETUTC;
    private int STATE;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMASTER() {
        return MASTER;
    }

    public void setMASTER(String MASTER) {
        this.MASTER = MASTER;
    }

    public String getSLAVE() {
        return SLAVE;
    }

    public void setSLAVE(String SLAVE) {
        this.SLAVE = SLAVE;
    }

    public String getENDUTC() {
        return ENDUTC;
    }

    public void setENDUTC(String ENDUTC) {
        this.ENDUTC = ENDUTC;
    }

    public int getSTATE() {
        return STATE;
    }

    public void setSTATE(int STATE) {
        this.STATE = STATE;
    }

    public String getCURRNETUTC() {
        return CURRNETUTC;
    }

    public void setCURRNETUTC(String CURRNETUTC) {
        this.CURRNETUTC = CURRNETUTC;
    }

    @Override
    public String toString() {
        return "Grant{" +
                "ID='" + ID + '\'' +
                ", MASTER='" + MASTER + '\'' +
                ", SLAVE='" + SLAVE + '\'' +
                ", ENDUTC='" + ENDUTC + '\'' +
                ", CURRNETUTC='" + CURRNETUTC + '\'' +
                ", STATE=" + STATE +
                '}';
    }
}
