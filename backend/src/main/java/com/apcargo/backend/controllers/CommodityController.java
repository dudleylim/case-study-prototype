package com.apcargo.backend.controllers;

import com.apcargo.backend.exceptions.FieldException;
import com.apcargo.backend.models.Commodity;
import com.apcargo.backend.repositories.CommodityRepository;
import com.apcargo.backend.requests.CommodityAddRequest;
import com.apcargo.backend.requests.CommodityUpdateRequest;
import com.apcargo.backend.services.CommodityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/comm")
public class CommodityController {
    private final CommodityService commodityService;

    @GetMapping("")
    public ResponseEntity<?> getCommodities() {
        return new ResponseEntity<>(commodityService.getCommodities(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommodity(@PathVariable("id") Long id) {
        try {
            Commodity commodity = commodityService.getCommodity(id);
            return new ResponseEntity<>(commodity, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("status", e.getStatusCode().value());
            response.put("message", e.getReason());

            return new ResponseEntity<>(response, e.getStatusCode());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createCommodity(@RequestBody CommodityAddRequest commodityAddRequest) {
        try {
            commodityService.createCommodity(commodityAddRequest);

            HashMap<String, Object> response = new HashMap<>();
            response.put("status", 201);
            response.put("message", "New commodity added successfully.");

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (FieldException e) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("status", 400);
            response.put("message", e.getMessages());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (ResponseStatusException e) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("status", e.getStatusCode().value());
            response.put("message", e.getReason());

            return new ResponseEntity<>(response, e.getStatusCode());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCommodity(@PathVariable("id") Long id, @RequestBody CommodityUpdateRequest commodityUpdateRequest) {
        try {
            commodityService.updateCommodity(id, commodityUpdateRequest);

            HashMap<String, Object> response = new HashMap<>();
            response.put("status", 200);
            response.put("message", "Commodity information updated successfully.");

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (FieldException e) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("status", 400);
            response.put("message", e.getMessages());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (ResponseStatusException e) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("status", e.getStatusCode().value());
            response.put("message", e.getReason());

            return new ResponseEntity<>(response, e.getStatusCode());
        }
    }
}
