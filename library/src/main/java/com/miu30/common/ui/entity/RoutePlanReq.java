package com.miu30.common.ui.entity;

import java.io.Serializable;

/**
 * 具体请求参数,返回值说明见百度api
 * http://lbsyun.baidu.com/index.php?title=webapi/direction-api
 * #.E4.BA.A7.E5.93.81.E4.BB.8B.E7.BB.8D
 *
 * @author Administrator
 *
 */
public class RoutePlanReq implements Serializable {
	private String origin;
	private String destination;
	private String mode;
	private String region;
	private String origin_region;
	private String destination_region;
	private String coord_type;
	private String tactics;

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getOrigin_region() {
		return origin_region;
	}

	public void setOrigin_region(String origin_region) {
		this.origin_region = origin_region;
	}

	public String getDestination_region() {
		return destination_region;
	}

	public void setDestination_region(String destination_region) {
		this.destination_region = destination_region;
	}

	public String getCoord_type() {
		return coord_type;
	}

	public void setCoord_type(String coord_type) {
		this.coord_type = coord_type;
	}

	public String getTactics() {
		return tactics;
	}

	public void setTactics(String tactics) {
		this.tactics = tactics;
	}

	@Override
	public String toString() {
		return "RoutePlanReq [origin=" + origin + ", destination=" + destination + ", mode=" + mode + ", region="
				+ region + ", origin_region=" + origin_region + ", destination_region=" + destination_region
				+ ", coord_type=" + coord_type + ", tactics=" + tactics + "]";
	}

}