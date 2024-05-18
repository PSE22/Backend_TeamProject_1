package org.example.backend.service.admin;

import org.example.backend.model.entity.admin.AdminProduct;
import org.example.backend.repository.admin.AdminProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminProductService {

    @Autowired
    AdminProductRepository adminProductRepository;

    //    TODO: 전체조회(read)
    public List<AdminProduct> findAll() {
        List<AdminProduct> list = adminProductRepository.findAll();
        return list;
    }

    //    페이징 처리
    public Page<AdminProduct> findAllByAdminProductNameContaining(String pdName,
                                                                Pageable pageable) {
        Page<AdminProduct> page
                = adminProductRepository
                .findAllByAdminProductNameContaining(pdName, pageable);
        return page;
    }

    //    상세조회
    public Optional<AdminProduct> findById(Long pdId) {
        Optional<AdminProduct> optionalAdminProduct
                = adminProductRepository.findById(pdId);
        return optionalAdminProduct;
    }

    //    TODO: 등록(insert),수정(update)
    public AdminProduct save(AdminProduct adminProduct) {
        AdminProduct adminProduct2 = adminProductRepository.save(adminProduct);

        return adminProduct2;
    }

    //    TODO: 삭제(delete)
    public boolean removeById(Long pdId) {
        if (adminProductRepository.existsById(pdId) == true) {
            adminProductRepository.deleteById(pdId);
            return true;
        } else {
            return false;
        }
    }
}
