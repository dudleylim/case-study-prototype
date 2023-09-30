package com.apcargo.backend.controllers;

import com.apcargo.backend.exceptions.FieldException;
import com.apcargo.backend.models.CommodityClass;
import com.apcargo.backend.requests.CommodityClassAddRequest;
import com.apcargo.backend.requests.CommodityClassUpdateRequest;
import com.apcargo.backend.services.CommodityClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/comm_class")
public class CommodityClassController {
    private final CommodityClassService commodityClassService;

    @GetMapping("")
    public ResponseEntity<?> getCommodityClasses() {
        return new ResponseEntity<>(commodityClassService.getCommodityClasses(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommodityClass(@PathVariable("id") Long id) {
        try {
            CommodityClass foundCommodityClass = commodityClassService.getCommodityClass(id);
            return new ResponseEntity<>(foundCommodityClass, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("status", e.getStatusCode().value());
            response.put("message", e.getReason());

            return new ResponseEntity<>(response, e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createCommodityClass(@RequestBody CommodityClassAddRequest commodityClassAddRequest) {
        try {
            commodityClassService.addCommodityClass(commodityClassAddRequest);

            HashMap<String, Object> response = new HashMap<>();
            response.put("status", 201);
            response.put("message", "New commodity class added successfully.");

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
    public ResponseEntity<?> updateCommodityClass(@PathVariable("id") Long id, @RequestBody CommodityClassUpdateRequest commodityClassUpdateRequest) {
        try {
            commodityClassService.updateCommodityClass(id, commodityClassUpdateRequest);

            HashMap<String, Object> response = new HashMap<>();
            response.put("status", 200);
            response.put("message", "Commodity class information updated successfully.");

            return new ResponseEntity<>(response, HttpStatus.OK);
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
