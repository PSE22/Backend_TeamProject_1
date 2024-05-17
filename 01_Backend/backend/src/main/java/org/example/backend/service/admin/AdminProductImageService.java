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

    //    TODO: 전체조회(read)
    public List<ProductImage> findAll() {
        List<ProductImage> list = adminProductImageRepository.findAll();
        return list;
    }

    //    페이징 처리
    public Page<ProductImage> findAllByAdminProductImagePdidContaining(Long pdId,
                                                                  Pageable pageable) {
        Page<ProductImage> page
                = adminProductImageRepository
                .findAllByAdminProductImagePdImgIdContaining(pdId, pageable);
        return page;
    }

    //    상세조회
    public Optional<ProductImage> findById(Long pdImgId) {
        Optional<ProductImage> optionalProductImage
                = adminProductImageRepository.findById(pdImgId);
        return optionalProductImage;
    }

    //    TODO: 등록(insert),수정(update)
    public ProductImage save(ProductImage productImage) {
        ProductImage productImage2 = adminProductImageRepository.save(productImage);

        return productImage2;
    }

    //    TODO: 삭제(delete)
    public boolean removeById(Long pdImgId) {
        if (adminProductImageRepository.existsById(pdImgId) == true) {
            adminProductImageRepository.deleteById(pdImgId);
            return true;
        } else {
            return false;
        }
    }
}
