package org.example.backend.service.admin;

import org.example.backend.model.entity.admin.AdminCoupon;
import org.example.backend.repository.admin.AdminCouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminCouponService {
    @Autowired
    AdminCouponRepository adminCouponRepository;

    //    TODO: 전체조회(read)
    public List<AdminCoupon> findAll() {
        List<AdminCoupon> list = adminCouponRepository.findAll();
        return list;
    }

    //    페이징 처리
    public Page<AdminCoupon> findAllByAdminCouponNameContaining(String cpName,
                                                              Pageable pageable) {
        Page<AdminCoupon> page
                = adminCouponRepository
                .findAllByAdminCouponNameContaining(cpName, pageable);
        return page;
    }

    //    상세조회
    public Optional<AdminCoupon> findById(Long cpId) {
        //    JPA 상세조회 함수 실행
        Optional<AdminCoupon> optionalAdminCoupon
                = adminCouponRepository.findById(cpId);
        return optionalAdminCoupon;
    }

    //    TODO: 등록(insert),수정(update)
    public AdminCoupon save(AdminCoupon adminCoupon) {
        //    JPA 저장 함수 실행 : return 값 : 저장된 객체
        AdminCoupon adminCoupon2 = adminCouponRepository.save(adminCoupon);

        return adminCoupon2;
    }

    //    TODO: 삭제(delete)
    public boolean removeById(Long cpId) {
        if (adminCouponRepository.existsById(cpId) == true) {
            adminCouponRepository.deleteById(cpId);
            return true;
        } else {
            return false;
        }
    }
}
