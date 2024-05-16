package org.example.backend.service.mypage;

import org.example.backend.model.entity.ShipAddress;
import org.example.backend.repository.shop.ShipAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipAddressService {

    @Autowired
    ShipAddressRepository shipAddressRepository;

    //    TODO: 등록(insert)c,수정(update)u
    public ShipAddress save(ShipAddress shipAddress) {
        //    JPA 저장 함수 실행 : return 값 : 저장된 객체
        ShipAddress shipAddress2 = shipAddressRepository.save(shipAddress);

        return shipAddress2;
    }
}
