package com.miu30.common.ui.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 审批车辆信息
 */
public class ShenPiCarInfo implements Parcelable {

    /**
     * CERTIFICATE_NO : 出网110100001723                                  网约车车辆运输证号
     * PLATE_NO : BZ4304                                                 车牌号
     * BODY_COLOR : 黑色
     * BRAND : 09a9decfb22c445484032ace521208bf
     * BELONG_TYPE : 企业                                                 车辆所属类型
     * OWNER_IDEN_NUM : 10116620-2                                       所有者证件号码
     * OWNER_NAME : 北京首汽（集团）股份有限公司                              所有者
     * CAR_STATUS : 运营                                                  车辆状态
     * VECHILE_MODEL : BH7201RAV                                         车辆型号
     * FIRST_TIME : 2017-05-15T07:10:03.000+0000                         初次上岗日期
     * REGISTRATION_TIME : 2017-05-05T01:17:47.000+0000                  注册时间
     * FUEL_TYPE : 36b8ff28078b467b9038532ddb58cc7f                        燃料类型
     * DISPLACEMENT : 1999
     * VIN : LBELFAKC3GY070027
     * ENGINE_MODEL : null
     * WHEEL_BASE : 2805                                                   轴距
     * ENGINE_NO : GW053959
     * PSH : 05350
     * REMARK : null
     * CERT_BB_REASON : null                                               出租车营运证件补办原因
     * RN : 1
     */
    private String CERTIFICATE_NO;
    private String PLATE_NO;
    private String BODY_COLOR;
    private String BRAND;
    private String BELONG_TYPE;
    private String OWNER_IDEN_NUM;
    private String OWNER_NAME;
    private String CAR_STATUS;
    private String VECHILE_MODEL;
    private String FIRST_TIME;
    private String REGISTRATION_TIME;
    private String FUEL_TYPE;
    private String DISPLACEMENT;
    private String VIN;
    private String ENGINE_MODEL;
    private String WHEEL_BASE;
    private String ENGINE_NO;
    private String PSH;
    private String REMARK;
    private String PLAT_NAME;
    private String CERT_BB_REASON;
    private String FUEL_TYPE_VALUE;
    private int RN;

    public String getCERTIFICATE_NO() {
        return CERTIFICATE_NO;
    }

    public void setCERTIFICATE_NO(String CERTIFICATE_NO) {
        this.CERTIFICATE_NO = CERTIFICATE_NO;
    }

    public String getPLATE_NO() {
        return PLATE_NO;
    }

    public void setPLATE_NO(String PLATE_NO) {
        this.PLATE_NO = PLATE_NO;
    }

    public String getBODY_COLOR() {
        return BODY_COLOR;
    }

    public void setBODY_COLOR(String BODY_COLOR) {
        this.BODY_COLOR = BODY_COLOR;
    }

    public String getBRAND() {
        return BRAND;
    }

    public void setBRAND(String BRAND) {
        this.BRAND = BRAND;
    }

    public String getBELONG_TYPE() {
        return BELONG_TYPE;
    }

    public void setBELONG_TYPE(String BELONG_TYPE) {
        this.BELONG_TYPE = BELONG_TYPE;
    }

    public String getOWNER_IDEN_NUM() {
        return OWNER_IDEN_NUM;
    }

    public void setOWNER_IDEN_NUM(String OWNER_IDEN_NUM) {
        this.OWNER_IDEN_NUM = OWNER_IDEN_NUM;
    }

    public String getOWNER_NAME() {
        return OWNER_NAME;
    }

    public void setOWNER_NAME(String OWNER_NAME) {
        this.OWNER_NAME = OWNER_NAME;
    }

    public String getCAR_STATUS() {
        return CAR_STATUS;
    }

    public void setCAR_STATUS(String CAR_STATUS) {
        this.CAR_STATUS = CAR_STATUS;
    }

    public String getVECHILE_MODEL() {
        return VECHILE_MODEL;
    }

    public void setVECHILE_MODEL(String VECHILE_MODEL) {
        this.VECHILE_MODEL = VECHILE_MODEL;
    }

    public String getFIRST_TIME() {
        return FIRST_TIME;
    }

    public void setFIRST_TIME(String FIRST_TIME) {
        this.FIRST_TIME = FIRST_TIME;
    }

    public String getREGISTRATION_TIME() {
        return REGISTRATION_TIME;
    }

    public void setREGISTRATION_TIME(String REGISTRATION_TIME) {
        this.REGISTRATION_TIME = REGISTRATION_TIME;
    }

    public String getFUEL_TYPE() {
        return FUEL_TYPE;
    }

    public void setFUEL_TYPE(String FUEL_TYPE) {
        this.FUEL_TYPE = FUEL_TYPE;
    }

    public String getDISPLACEMENT() {
        return DISPLACEMENT;
    }

    public void setDISPLACEMENT(String DISPLACEMENT) {
        this.DISPLACEMENT = DISPLACEMENT;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getENGINE_MODEL() {
        return ENGINE_MODEL;
    }

    public void setENGINE_MODEL(String ENGINE_MODEL) {
        this.ENGINE_MODEL = ENGINE_MODEL;
    }

    public String getWHEEL_BASE() {
        return WHEEL_BASE;
    }

    public void setWHEEL_BASE(String WHEEL_BASE) {
        this.WHEEL_BASE = WHEEL_BASE;
    }

    public String getENGINE_NO() {
        return ENGINE_NO;
    }

    public void setENGINE_NO(String ENGINE_NO) {
        this.ENGINE_NO = ENGINE_NO;
    }

    public String getPSH() {
        return PSH;
    }

    public void setPSH(String PSH) {
        this.PSH = PSH;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getCERT_BB_REASON() {
        return CERT_BB_REASON;
    }

    public void setCERT_BB_REASON(String CERT_BB_REASON) {
        this.CERT_BB_REASON = CERT_BB_REASON;
    }

    public int getRN() {
        return RN;
    }

    public void setRN(int RN) {
        this.RN = RN;
    }

    public String getFUEL_TYPE_VALUE() {
        return FUEL_TYPE_VALUE;
    }

    public void setFUEL_TYPE_VALUE(String FUEL_TYPE_VALUE) {
        this.FUEL_TYPE_VALUE = FUEL_TYPE_VALUE;
    }

    public String getPLAT_NAME() {
        return PLAT_NAME;
    }

    public void setPLAT_NAME(String PLAT_NAME) {
        this.PLAT_NAME = PLAT_NAME;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.CERTIFICATE_NO);
        dest.writeString(this.PLATE_NO);
        dest.writeString(this.BODY_COLOR);
        dest.writeString(this.BRAND);
        dest.writeString(this.BELONG_TYPE);
        dest.writeString(this.OWNER_IDEN_NUM);
        dest.writeString(this.OWNER_NAME);
        dest.writeString(this.CAR_STATUS);
        dest.writeString(this.VECHILE_MODEL);
        dest.writeString(this.FIRST_TIME);
        dest.writeString(this.REGISTRATION_TIME);
        dest.writeString(this.FUEL_TYPE);
        dest.writeString(this.DISPLACEMENT);
        dest.writeString(this.VIN);
        dest.writeString(this.ENGINE_MODEL);
        dest.writeString(this.WHEEL_BASE);
        dest.writeString(this.ENGINE_NO);
        dest.writeString(this.PSH);
        dest.writeString(this.REMARK);
        dest.writeString(this.PLAT_NAME);
        dest.writeString(this.CERT_BB_REASON);
        dest.writeString(this.FUEL_TYPE_VALUE);
        dest.writeInt(this.RN);
    }

    public ShenPiCarInfo() {
    }

    protected ShenPiCarInfo(Parcel in) {
        this.CERTIFICATE_NO = in.readString();
        this.PLATE_NO = in.readString();
        this.BODY_COLOR = in.readString();
        this.BRAND = in.readString();
        this.BELONG_TYPE = in.readString();
        this.OWNER_IDEN_NUM = in.readString();
        this.OWNER_NAME = in.readString();
        this.CAR_STATUS = in.readString();
        this.VECHILE_MODEL = in.readString();
        this.FIRST_TIME = in.readString();
        this.REGISTRATION_TIME = in.readString();
        this.FUEL_TYPE = in.readString();
        this.DISPLACEMENT = in.readString();
        this.VIN = in.readString();
        this.ENGINE_MODEL = in.readString();
        this.WHEEL_BASE = in.readString();
        this.ENGINE_NO = in.readString();
        this.PSH = in.readString();
        this.REMARK = in.readString();
        this.PLAT_NAME = in.readString();
        this.CERT_BB_REASON = in.readString();
        this.FUEL_TYPE_VALUE = in.readString();
        this.RN = in.readInt();
    }

    public static final Creator<ShenPiCarInfo> CREATOR = new Creator<ShenPiCarInfo>() {
        @Override
        public ShenPiCarInfo createFromParcel(Parcel source) {
            return new ShenPiCarInfo(source);
        }

        @Override
        public ShenPiCarInfo[] newArray(int size) {
            return new ShenPiCarInfo[size];
        }
    };
}
