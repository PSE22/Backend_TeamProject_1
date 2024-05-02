package org.example.backend.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.entity.admin.AdminCoupon;
import org.example.backend.model.entity.admin.AdminProduct;
import org.example.backend.service.admin.AdminProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminProductController {

    @Autowired
    AdminProductService adminProductService;

    //    TODO: 전체 조회 함수 + 검색 + 페이징
    @GetMapping("/admin-product")
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue = "") String pdName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try{
//            매개변수(page, size) 페이징 변수에 저장
//             page : 현재페이지번호, size : 1페이지당개수
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 함수 실행
            Page<AdminProduct> pageList
                    = adminProductService.findAllByAdminProductNameContaining(pdName,pageable);

//            vue 로 json 데이터를 전송 : jsp (model == Map(키,값))
            Map<String, Object> response = new HashMap<>();
            response.put("adminProduct", pageList.getContent());             // 상품배열
            response.put("currentPage", pageList.getNumber());       // 현재페이지 번호(x)
            response.put("totalItems", pageList.getTotalElements()); // 전체데이터개수
            response.put("totalPages", pageList.getTotalPages());    // 전체페이지수(x)

//            TODO: 1) pageList 값이 없으면 : DB 테이블 없음 => NO_CONTENT(203)
//                  2) pageList 값이 있으면 : 성공 => OK(200)
            if(pageList.isEmpty() == true) {
//                1) pageList 값이 없으면 : DB 테이블 없음 => NO_CONTENT(203)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        } catch (Exception e) {
//            TODO: INTERNAL_SERVER_ERROR(500) : 벡엔드 서버 에러
//               아래 코드는 프론트로(웹브라우저) 신호를(500) 보냄
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 상세조회
    @GetMapping("/admin-product/{pdId}")
    public ResponseEntity<Object> findById(
            @PathVariable long pdId
    ) {
        try {
//            DB 상세조회 서비스 함수 실행
            Optional<AdminProduct> optionalAdminProduct = adminProductService.findById(pdId);

            if(optionalAdminProduct.isEmpty() == true) {
//                데이터 없음(203)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
//                데이터 있음(200)
                return new ResponseEntity<>(optionalAdminProduct.get()
                        , HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 저장 함수
    @PostMapping("/admin-product")
    public ResponseEntity<Object> create(
            @RequestBody AdminProduct adminProduct
    ) {
        log.debug("1");
        try {
//            DB 서비스 저장 함수 실행
            AdminProduct adminProduct2 = adminProductService.save(adminProduct);

//            성공(OK) 메세지 + 저장된객체(dept2)
            return new ResponseEntity<>(adminProduct2, HttpStatus.OK);

        } catch (Exception e) {
            log.debug("에러 : " + e.getMessage());
//            500 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 수정함수
    @PutMapping("/admin-product/{pdId}")
    public ResponseEntity<Object> update(
            @PathVariable long pdId,
            @RequestBody AdminProduct adminProduct
    ) {
        try {
            AdminProduct adminProduct2 = adminProductService.save(adminProduct);  // 수정

            return new ResponseEntity<>(adminProduct2, HttpStatus.OK);
        } catch (Exception e) {
//            DB 에러 (서버 에러) -> 500 신호(INTERNAL_SERVER_ERROR)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //     TODO: 삭제 함수
    @DeleteMapping("/admin-product/deletion/{pdId}")
    public ResponseEntity<Object> delete(
            @PathVariable long pdId
    ) {
        try {
//            DB 서비스 삭제 함수 실행
            boolean success = adminProductService.removeById(pdId);

            if (success == true) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                // 삭제 실행 : 0건 삭제(삭제할 데이터 없음)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
//            서버(DB) 에러
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
