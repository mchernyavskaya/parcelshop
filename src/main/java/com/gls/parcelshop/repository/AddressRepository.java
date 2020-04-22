package com.gls.parcelshop.repository;

import com.gls.parcelshop.model.Address;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
    List<Address> findAll();
}
