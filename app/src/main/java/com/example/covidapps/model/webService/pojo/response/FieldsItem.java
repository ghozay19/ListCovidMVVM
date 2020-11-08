package com.example.covidapps.model.webService.pojo.response;

import com.google.gson.annotations.SerializedName;

public class FieldsItem{

	@SerializedName("sqlType")
	private String sqlType;

	@SerializedName("defaultValue")
	private Object defaultValue;

	@SerializedName("domain")
	private Object domain;

	@SerializedName("name")
	private String name;

	@SerializedName("alias")
	private String alias;

	@SerializedName("type")
	private String type;

	@SerializedName("length")
	private int length;

	public void setSqlType(String sqlType){
		this.sqlType = sqlType;
	}

	public String getSqlType(){
		return sqlType;
	}

	public void setDefaultValue(Object defaultValue){
		this.defaultValue = defaultValue;
	}

	public Object getDefaultValue(){
		return defaultValue;
	}

	public void setDomain(Object domain){
		this.domain = domain;
	}

	public Object getDomain(){
		return domain;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setAlias(String alias){
		this.alias = alias;
	}

	public String getAlias(){
		return alias;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setLength(int length){
		this.length = length;
	}

	public int getLength(){
		return length;
	}
}