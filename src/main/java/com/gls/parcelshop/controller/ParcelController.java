package com.gls.parcelshop.controller;

import com.gls.parcelshop.model.DeliveryState;
import com.gls.parcelshop.model.Parcel;
import com.gls.parcelshop.repository.ParcelRepository;
import com.gls.parcelshop.service.NotificationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ParcelController {

    private final ParcelRepository parcelRepository;

    private final NotificationService notificationService;

    public ParcelController(ParcelRepository parcelRepository, NotificationService notificationService) {
        this.parcelRepository = parcelRepository;
        this.notificationService = notificationService;
    }

    @GetMapping("/parcels")
    public List<Parcel> getAllParcels(
            @RequestParam(required = false) String deliveryDate) {
        if (deliveryDate == null) {
            return parcelRepository.findAll();
        }
        return parcelRepository.findAllByDeliveryDateAndDeliveryState(deliveryDate, DeliveryState.OUT_FOR_DELIVERY);
    }

    @PostMapping(value = "/parcels", consumes = "application/json")
    public ResponseEntity<Parcel> insertNewParcels(@RequestBody Parcel parcel) {
        Parcel savedParcel = parcelRepository.save(parcel);
        notificationService.notifySomeoneAboutChange(savedParcel);
        return new ResponseEntity<>(savedParcel, HttpStatus.CREATED);
    }

}
