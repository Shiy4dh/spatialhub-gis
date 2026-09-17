package com.example.spatial.controller;

import com.example.spatial.dto.CreateAssetRequest;
import com.example.spatial.dto.CreateWorkOrderRequest;
import com.example.spatial.model.NetworkAsset;
import com.example.spatial.model.WorkOrder;
import com.example.spatial.service.NetworkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class NetworkController {

    private final NetworkService networkService;

    public NetworkController(NetworkService networkService) {
        this.networkService = networkService;
    }

    @GetMapping("/assets")
    public List<NetworkAsset> getAssets() {
        return networkService.getAllAssets();
    }

    @GetMapping("/assets/geojson")
    public Map<String, Object> getAssetsGeoJson() {
        return networkService.getAssetsGeoJson();
    }

    @PostMapping("/work-orders/{id}/resolve")
    public ResponseEntity<WorkOrder> resolveWorkOrder(@PathVariable Long id) {
        return ResponseEntity.ok(networkService.resolveWorkOrder(id));
    }
    // Add to NetworkController.java:

    @PostMapping("/assets")
    public ResponseEntity<NetworkAsset> createAsset(@RequestBody CreateAssetRequest request) {
        return ResponseEntity.ok(networkService.createAsset(request));
    }

    @GetMapping("/work-orders")
    public List<WorkOrder> getWorkOrders() {
        return networkService.getAllWorkOrders();
    }

    @PostMapping("/work-orders")
    public ResponseEntity<WorkOrder> createWorkOrder(@RequestBody CreateWorkOrderRequest request) {
        return ResponseEntity.ok(networkService.createWorkOrder(request));
    }

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        return networkService.getNetworkStats();
    }
}