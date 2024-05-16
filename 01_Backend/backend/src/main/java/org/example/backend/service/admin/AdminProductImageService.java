package org.example.backend.service.admin;

import org.example.backend.model.entity.ProductImage;
import org.example.backend.repository.admin.AdminProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminProductImageService {

    @Autowired
    AdminProductImageRepository adminProductImageRepository;

    //    TODO: 전체조회(read)r
    public List<ProductImage> findAll() {
        List<ProductImage> list = adminProductImageRepository.findAll();
        return list;
    }

    //    페이징 처리
    public Page<ProductImage> findAllByAdminProductImagePdidContaining(Long pdId,
                                                                  Pageable pageable) {
        //    DB like 검색 함수 실행 : 페이징 처리
        Page<ProductImage> page
                = adminProductImageRepository
                .findAllByAdminProductImagePdImgIdContaining(pdId, pageable);
        return page;
    }

    //    상세조회
    public Optional<ProductImage> findById(Long pdImgId) {
        //    JPA 상세조회 함수 실행
        Optional<ProductImage> optionalProductImage
                = adminProductImageRepository.findById(pdImgId);
        return optionalProductImage;
    }

    //    TODO: 등록(insert)c,수정(update)u
    public ProductImage save(ProductImage productImage) {
        //    JPA 저장 함수 실행 : return 값 : 저장된 객체
        ProductImage productImage2 = adminProductImageRepository.save(productImage);

        return productImage2;
    }

    //    TODO: 삭제(delete)d
    public boolean removeById(Long pdImgId) {
        if (adminProductImageRepository.existsById(pdImgId) == true) {
            adminProductImageRepository.deleteById(pdImgId);
            return true;
        } else {
            return false;
        }
    }
}
