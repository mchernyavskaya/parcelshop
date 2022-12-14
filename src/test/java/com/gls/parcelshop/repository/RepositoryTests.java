package com.gls.parcelshop.repository;

import com.gls.parcelshop.model.Address;
import com.gls.parcelshop.model.DeliveryState;
import com.gls.parcelshop.model.Parcel;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RepositoryTests {

    @Autowired
    private ParcelRepository parcelRepository;

    private final String parcelNumber = "1000";
    private final String deliveryDate = "20200421";

    @BeforeEach
    void cleanup() {
        parcelRepository.deleteAll();
    }

    @Test
    void testCreateParcel() {
        parcelRepository.save(createStubParcel(parcelNumber, deliveryDate));

        List<Parcel> parcels = parcelRepository.findAll();
        assertEquals(1, parcels.size());
    }

    @Test
    void testGetParcelByDeliveryDateAndState() {
        Parcel parcel = parcelRepository.save(createStubParcel(parcelNumber, deliveryDate));
        List<Parcel> result = parcelRepository.findAllByDeliveryDateAndDeliveryState(
                deliveryDate, DeliveryState.OUT_FOR_DELIVERY);
        assertEquals(1, result.size());
        assertEquals(parcel, result.get(0));
    }

    private Parcel createStubParcel(String number, String deliveryDate) {
        return Parcel.builder()
                .address(createDefaultAddress())
                .parcelNumber(number)
                .deliveryDate(deliveryDate)
                .deliveryState(DeliveryState.OUT_FOR_DELIVERY)
                .build();
    }

    private Address createDefaultAddress() {
        return Address.builder()
                .consignee("GLS")
                .street("Kemperplatz")
                .number("1")
                .zip("10785")
                .build();
    }
}
