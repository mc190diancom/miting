package com.miu30.common.ui.entity;

import java.util.ArrayList;

public class GPSPoint {

		private double LAT84;
		private double LON84;
		private String GPS_TIME;	
		private int VEHICLE_STATUS;
		private float AZIMUTHS;
		private double SPEED;

	public double getLAT84() {
		return LAT84;
	}

	public void setLAT84(double LAT84) {
		this.LAT84 = LAT84;
	}

	public double getLON84() {
		return LON84;
	}

	public void setLON84(double LON84) {
		this.LON84 = LON84;
	}

	public String getGPS_TIME() {
		return GPS_TIME;
	}

	public void setGPS_TIME(String GPS_TIME) {
		this.GPS_TIME = GPS_TIME;
	}

	public int getVEHICLE_STATUS() {
		return VEHICLE_STATUS;
	}

	public void setVEHICLE_STATUS(int VEHICLE_STATUS) {
		this.VEHICLE_STATUS = VEHICLE_STATUS;
	}

	public float getAZIMUTHS() {
		return AZIMUTHS;
	}

	public void setAZIMUTHS(float AZIMUTHS) {
		this.AZIMUTHS = AZIMUTHS;
	}

	public double getSPEED() {
		return SPEED;
	}

	public void setSPEED(double SPEED) {
		this.SPEED = SPEED;
	}

	@Override
		public String toString() {
			return "GPSPoint [lat=" + LAT84 + ", lon=" + LON84 + ", gpstime=" + GPS_TIME + "]";
		}

}
