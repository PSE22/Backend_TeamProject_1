package org.example.backend.service.mypage;

import org.example.backend.model.entity.ShipAddress;
import org.example.backend.repository.shop.ShipAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipAddressService {

    @Autowired
    ShipAddressRepository shipAddressRepository;
    public ShipAddress save(ShipAddress shipAddress) {
        ShipAddress shipAddress2 = shipAddressRepository.save(shipAddress);

        return shipAddress2;
    }
}
