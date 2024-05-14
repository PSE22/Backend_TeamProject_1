package org.example.backend.controller.mypage;

import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.entity.ShipAddress;
import org.example.backend.service.mypage.ShipAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/mypage")
public class ShipAddressController {

    @Autowired
    ShipAddressService shipAddressService;

    //    TODO: 저장 함수
    @PostMapping("/editProfile")
    public ResponseEntity<Object> create(
            @RequestBody ShipAddress shipAddress
    ) {
        log.debug("1");
        try {
//            DB 서비스 저장 함수 실행
            ShipAddress shipAddress2 = shipAddressService.save(shipAddress);

//            성공(OK) 메세지 + 저장된객체(dept2)
            return new ResponseEntity<>(shipAddress2, HttpStatus.OK);

        } catch (Exception e) {
            log.debug("에러 : " + e.getMessage());
//            500 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 수정함수
    @PutMapping("/editProfile/{shipAddrId}")
    public ResponseEntity<Object> update(
            @PathVariable long shipAddrId,
            @RequestBody ShipAddress shipAddress
    ) {
        try {
            log.debug("확인22"+shipAddress);
            ShipAddress shipAddress2 = shipAddressService.save(shipAddress);  // 수정

            return new ResponseEntity<>(shipAddress2, HttpStatus.OK);
        } catch (Exception e) {
            log.debug("확인"+e.getMessage());
//            DB 에러 (서버 에러) -> 500 신호(INTERNAL_SERVER_ERROR)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
