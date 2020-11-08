package com.example.covidapps.model.webService.pojo.response;

import com.google.gson.annotations.SerializedName;

public class Geometry{

	@SerializedName("x")
	private double X;

	@SerializedName("y")
	private double Y;

	public void setX(double X){
		this.X = X;
	}

	public double getX(){
		return X;
	}

	public void setY(double Y){
		this.Y = Y;
	}

	public double getY(){
		return Y;
	}
}