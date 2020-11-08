package com.example.covidapps.model.webService.pojo.response;

import com.google.gson.annotations.SerializedName;

public class UniqueIdField{

	@SerializedName("isSystemMaintained")
	private boolean isSystemMaintained;

	@SerializedName("name")
	private String name;

	public void setIsSystemMaintained(boolean isSystemMaintained){
		this.isSystemMaintained = isSystemMaintained;
	}

	public boolean isIsSystemMaintained(){
		return isSystemMaintained;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}