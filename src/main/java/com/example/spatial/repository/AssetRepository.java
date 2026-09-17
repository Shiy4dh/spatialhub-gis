package com.example.spatial.repository;


import com.example.spatial.model.NetworkAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<NetworkAsset, Long> {
    List<NetworkAsset> findByRegion(String region);
}