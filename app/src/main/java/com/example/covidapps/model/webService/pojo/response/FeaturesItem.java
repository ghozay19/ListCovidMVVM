package com.example.covidapps.model.webService.pojo.response;

import com.google.gson.annotations.SerializedName;

public class FeaturesItem{

	@SerializedName("attributes")
	private Attributes attributes;

	@SerializedName("geometry")
	private Geometry geometry;

	public void setAttributes(Attributes attributes){
		this.attributes = attributes;
	}

	public Attributes getAttributes(){
		return attributes;
	}

	public void setGeometry(Geometry geometry){
		this.geometry = geometry;
	}

	public Geometry getGeometry(){
		return geometry;
	}
}