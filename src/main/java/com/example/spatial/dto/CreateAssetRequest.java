package com.example.spatial.dto;

import com.example.spatial.model.AssetType;

public class CreateAssetRequest {
    private String assetTag;
    private AssetType type;
    private Double latitude;
    private Double longitude;
    private String region;

    // Getters and Setters
    public String getAssetTag() { return assetTag; }
    public void setAssetTag(String assetTag) { this.assetTag = assetTag; }
    public AssetType getType() { return type; }
    public void setType(AssetType type) { this.type = type; }
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
}