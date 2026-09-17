package com.example.spatial.service;


import com.example.spatial.dto.CreateAssetRequest;
import com.example.spatial.dto.CreateWorkOrderRequest;

import com.example.spatial.model.*;
import com.example.spatial.repository.AssetRepository;
import com.example.spatial.repository.WorkOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class NetworkService {

    private final AssetRepository assetRepository;
    private final WorkOrderRepository workOrderRepository;

    public NetworkService(AssetRepository assetRepository, WorkOrderRepository workOrderRepository) {
        this.assetRepository = assetRepository;
        this.workOrderRepository = workOrderRepository;
    }

    public List<NetworkAsset> getAllAssets() {
        return assetRepository.findAll();
    }

    public Map<String, Object> getAssetsGeoJson() {
        List<NetworkAsset> assets = assetRepository.findAll();

        List<Map<String, Object>> features = new ArrayList<>();
        for (NetworkAsset asset : assets) {
            Map<String, Object> feature = new HashMap<>();
            feature.put("type", "Feature");

            Map<String, Object> geometry = new HashMap<>();
            geometry.put("type", "Point");
            geometry.put("coordinates", Arrays.asList(asset.getLongitude(), asset.getLatitude()));
            feature.put("geometry", geometry);

            Map<String, Object> properties = new HashMap<>();
            properties.put("id", asset.getId());
            properties.put("tag", asset.getAssetTag());
            properties.put("type", asset.getType().name());
            properties.put("status", asset.getStatus().name());
            properties.put("region", asset.getRegion());
            feature.put("properties", properties);

            features.add(feature);
        }

        Map<String, Object> geoJson = new HashMap<>();
        geoJson.put("type", "FeatureCollection");
        geoJson.put("features", features);
        return geoJson;
    }

    @Transactional
    public WorkOrder resolveWorkOrder(Long workOrderId) {
        WorkOrder order = workOrderRepository.findById(workOrderId)
                .orElseThrow(() -> new RuntimeException("Work order not found: " + workOrderId));

        order.setStatus(WorkOrderStatus.COMPLETED);
        order.setResolvedAt(LocalDateTime.now());

        // Updating the linked asset status back to ACTIVE
        NetworkAsset asset = order.getAsset();
        asset.setStatus(AssetStatus.ACTIVE);
        asset.setLastSurveyedAt(LocalDateTime.now());

        assetRepository.save(asset);
        return workOrderRepository.save(order);


    }
    // Add to NetworkService.java:

    public NetworkAsset createAsset(CreateAssetRequest request) {
        NetworkAsset asset = new NetworkAsset();
        asset.setAssetTag(request.getAssetTag());
        asset.setType(request.getType());
        asset.setStatus(AssetStatus.ACTIVE);
        asset.setLatitude(request.getLatitude());
        asset.setLongitude(request.getLongitude());
        asset.setRegion(request.getRegion() != null ? request.getRegion() : "Field Survey");
        asset.setInstalledAt(LocalDateTime.now());
        asset.setLastSurveyedAt(LocalDateTime.now());
        return assetRepository.save(asset);
    }

    public WorkOrder createWorkOrder(CreateWorkOrderRequest request) {
        NetworkAsset asset = assetRepository.findById(request.getAssetId())
                .orElseThrow(() -> new RuntimeException("Asset not found: " + request.getAssetId()));

        WorkOrder order = new WorkOrder();
        order.setTicketNumber("WO-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        order.setTitle(request.getTitle());
        order.setDescription(request.getDescription());
        order.setStatus(WorkOrderStatus.OPEN);
        order.setAssignedTechnician(request.getAssignedTechnician());
        order.setAsset(asset);
        order.setCreatedAt(LocalDateTime.now());

        // Automatically mark the asset as DEGRADED when a ticket is logged against it
        asset.setStatus(AssetStatus.DEGRADED);
        assetRepository.save(asset);

        return workOrderRepository.save(order);
    }

    public List<WorkOrder> getAllWorkOrders() {
        return workOrderRepository.findAll();
    }

    public Map<String, Object> getNetworkStats() {
        long totalAssets = assetRepository.count();
        List<NetworkAsset> assets = assetRepository.findAll();

        long activeCount = assets.stream().filter(a -> a.getStatus() == AssetStatus.ACTIVE).count();
        long degradedCount = assets.stream().filter(a -> a.getStatus() == AssetStatus.DEGRADED).count();
        long offlineCount = assets.stream().filter(a -> a.getStatus() == AssetStatus.OFFLINE).count();
        long openTickets = workOrderRepository.findAll().stream()
                .filter(wo -> wo.getStatus() != WorkOrderStatus.COMPLETED).count();

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalAssets", totalAssets);
        stats.put("active", activeCount);
        stats.put("degraded", degradedCount);
        stats.put("offline", offlineCount);
        stats.put("openWorkOrders", openTickets);
        return stats;
    }
}