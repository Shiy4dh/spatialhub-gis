package com.example.spatial.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;



    @Entity
    @Table(name = "network_assets")
    public class NetworkAsset {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String assetTag;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private AssetType type;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private AssetStatus status;

        @Column(nullable = false)
        private Double latitude;

        @Column(nullable = false)
        private Double longitude;

        private String region;
        private LocalDateTime installedAt;
        private LocalDateTime lastSurveyedAt;

        public NetworkAsset() {}

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getAssetTag() { return assetTag; }
        public void setAssetTag(String assetTag) { this.assetTag = assetTag; }

        public AssetType getType() { return type; }
        public void setType(AssetType type) { this.type = type; }

        public AssetStatus getStatus() { return status; }
        public void setStatus(AssetStatus status) { this.status = status; }

        public Double getLatitude() { return latitude; }
        public void setLatitude(Double latitude) { this.latitude = latitude; }

        public Double getLongitude() { return longitude; }
        public void setLongitude(Double longitude) { this.longitude = longitude; }

        public String getRegion() { return region; }
        public void setRegion(String region) { this.region = region; }

        public LocalDateTime getInstalledAt() { return installedAt; }
        public void setInstalledAt(LocalDateTime installedAt) { this.installedAt = installedAt; }

        public LocalDateTime getLastSurveyedAt() { return lastSurveyedAt; }
        public void setLastSurveyedAt(LocalDateTime lastSurveyedAt) { this.lastSurveyedAt = lastSurveyedAt; }
    }

