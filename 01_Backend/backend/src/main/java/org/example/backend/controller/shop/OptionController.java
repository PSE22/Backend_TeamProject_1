package org.example.backend.controller.shop;

import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.dto.shop.IOptionDto;
import org.example.backend.service.shop.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * packageName : org.example.backendproject.controller.shop
 * fileName : OptionController
 * author : sjuk2
 * date : 2024-05-01
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-01         sjuk2          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/shop")
public class OptionController {
    @Autowired
    OptionService optionService;

//    상품 옵션 전체 조회
    @GetMapping("/productOption/{pdId}")
    public ResponseEntity<Object> findById(@PathVariable int pdId) {
        try {
            List<IOptionDto> optionDtoList = optionService.findById(pdId);
            if (optionDtoList.isEmpty() == true) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
//                조회 성공
                return new ResponseEntity<>(optionDtoList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
