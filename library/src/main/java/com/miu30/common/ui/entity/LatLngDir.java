package com.miu30.common.ui.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class LatLngDir implements Parcelable {
	public double dir = -1;
	public double lat = 0;
	public String latS ;
	public double lng = 0;

	public LatLngDir() {

	}

	public LatLngDir(double dir, double lat, double lng) {
		super();
		this.dir = dir;
		this.lat = lat;
		this.lng = lng;
	}

	public double getDir() {
		return dir;
	}

	public void setDir(double dir) {
		this.dir = dir;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getLatS() {
		return latS;
	}

	public void setLatS(String latS) {
		this.latS = latS;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "LatLngDir [dir=" + dir + ", lat=" + lat + ", lng=" + lng + "]";
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeDouble(this.dir);
		dest.writeDouble(this.lat);
		dest.writeString(this.latS);
		dest.writeDouble(this.lng);
	}

	protected LatLngDir(Parcel in) {
		this.dir = in.readDouble();
		this.lat = in.readDouble();
		this.latS = in.readString();
		this.lng = in.readDouble();
	}

	public static final Parcelable.Creator<LatLngDir> CREATOR = new Parcelable.Creator<LatLngDir>() {
		@Override
		public LatLngDir createFromParcel(Parcel source) {
			return new LatLngDir(source);
		}

		@Override
		public LatLngDir[] newArray(int size) {
			return new LatLngDir[size];
		}
	};
}
