package com.example.covidapps.model.webService.pojo.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CovidResponse{

	@SerializedName("features")
	private List<FeaturesItem> features;

	@SerializedName("globalIdFieldName")
	private String globalIdFieldName;

	@SerializedName("objectIdFieldName")
	private String objectIdFieldName;

	@SerializedName("spatialReference")
	private SpatialReference spatialReference;

	@SerializedName("fields")
	private List<FieldsItem> fields;

	@SerializedName("uniqueIdField")
	private UniqueIdField uniqueIdField;

	@SerializedName("geometryType")
	private String geometryType;

	public void setFeatures(List<FeaturesItem> features){
		this.features = features;
	}

	public List<FeaturesItem> getFeatures(){
		return features;
	}

	public void setGlobalIdFieldName(String globalIdFieldName){
		this.globalIdFieldName = globalIdFieldName;
	}

	public String getGlobalIdFieldName(){
		return globalIdFieldName;
	}

	public void setObjectIdFieldName(String objectIdFieldName){
		this.objectIdFieldName = objectIdFieldName;
	}

	public String getObjectIdFieldName(){
		return objectIdFieldName;
	}

	public void setSpatialReference(SpatialReference spatialReference){
		this.spatialReference = spatialReference;
	}

	public SpatialReference getSpatialReference(){
		return spatialReference;
	}

	public void setFields(List<FieldsItem> fields){
		this.fields = fields;
	}

	public List<FieldsItem> getFields(){
		return fields;
	}

	public void setUniqueIdField(UniqueIdField uniqueIdField){
		this.uniqueIdField = uniqueIdField;
	}

	public UniqueIdField getUniqueIdField(){
		return uniqueIdField;
	}

	public void setGeometryType(String geometryType){
		this.geometryType = geometryType;
	}

	public String getGeometryType(){
		return geometryType;
	}
}