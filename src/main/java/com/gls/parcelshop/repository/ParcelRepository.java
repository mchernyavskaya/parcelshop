package com.gls.parcelshop.repository;

import com.gls.parcelshop.model.DeliveryState;
import com.gls.parcelshop.model.Parcel;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelRepository extends CrudRepository<Parcel, Long> {
    List<Parcel> findAll();
    List<Parcel> findAllByParcelNumber(String parcelNumber);
    List<Parcel> findAllByDeliveryDateAndDeliveryState(String deliveryDate, DeliveryState deliveryState);
    List<Parcel> findAllByDeliveryDate(String deliveryDate);
}
