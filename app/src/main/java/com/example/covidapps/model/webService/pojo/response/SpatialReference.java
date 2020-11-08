package com.example.covidapps.model.webService.pojo.response;

import com.google.gson.annotations.SerializedName;

public class SpatialReference{

	@SerializedName("latestWkid")
	private int latestWkid;

	@SerializedName("wkid")
	private int wkid;

	public void setLatestWkid(int latestWkid){
		this.latestWkid = latestWkid;
	}

	public int getLatestWkid(){
		return latestWkid;
	}

	public void setWkid(int wkid){
		this.wkid = wkid;
	}

	public int getWkid(){
		return wkid;
	}
}