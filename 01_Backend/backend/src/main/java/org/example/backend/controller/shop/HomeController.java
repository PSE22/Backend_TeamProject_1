package org.example.backend.controller.shop;

import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.entity.Event;
import org.example.backend.model.entity.Product;
import org.example.backend.service.shop.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * packageName : org.example.backend.controller.shop
 * fileName : HomeController
 * author : SAMSUNG
 * date : 2024-05-08
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-08         SAMSUNG          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/shop")
@Controller
public class HomeController {
    @Autowired
    HomeService homeService;

    // 이벤트 전체 조회
    @GetMapping("/event")
    public ResponseEntity<Object> findAllEvent() {
        try {
            List<Event> list = homeService.findAllEvent();
            if (list.isEmpty() == true) {
                // 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // 조회 성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 상품 전체 조회
    @GetMapping("/home/product")
    public ResponseEntity<Object> findAllProduct() {
        try {
            List<Product> list = homeService.findAllProduct();
            if (list.isEmpty() == true) {
                // 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // 조회 성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
