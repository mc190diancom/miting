package com.miu30.common.ui.entity;

public class ZFRYByDWMC {
	private String ZFDWMC;
	private String ID;
	private String NAME;
	private String ZFZH;
	private String ENDUTC;
	private String CURRNETUTC;
	private int STATE = 2;

	public String getZFZH() {
		return ZFZH;
	}

	public void setZFZH(String zFZH) {
		ZFZH = zFZH;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getZFDWMC() {
		return ZFDWMC;
	}

	public void setZFDWMC(String zFDWMC) {
		ZFDWMC = zFDWMC;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
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
		return "ZFRYByDWMC{" +
				"ZFDWMC='" + ZFDWMC + '\'' +
				", ID='" + ID + '\'' +
				", NAME='" + NAME + '\'' +
				", ZFZH='" + ZFZH + '\'' +
				", ENDUTC='" + ENDUTC + '\'' +
				", CURRNETUTC='" + CURRNETUTC + '\'' +
				", STATE=" + STATE +
				'}';
	}
}
