package org.example.backend.service.admin;

import org.example.backend.model.entity.admin.AdminPdReview;
import org.example.backend.repository.admin.AdminPdReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminPdReviewService {

    @Autowired
    AdminPdReviewRepository adminPdReviewRepository;

    //    TODO: 전체조회(read)
    public List<AdminPdReview> findAll() {
        List<AdminPdReview> list = adminPdReviewRepository.findAll();
        return list;
    }

    //    페이징 처리
    public Page<AdminPdReview> findAllByAdminReviewTitleContaining(String reviewTitle,
                                                               Pageable pageable) {
        Page<AdminPdReview> page
                = adminPdReviewRepository
                .findAllByAdminPdReviewTitleContaining(reviewTitle, pageable);
        return page;
    }

    //    상세조회
    public Optional<AdminPdReview> findById(Long reviewId) {
        Optional<AdminPdReview> optionalAdminPdReview
                = adminPdReviewRepository.findById(reviewId);
        return optionalAdminPdReview;
    }

    //    TODO: 등록(insert),수정(update)
    public AdminPdReview save(AdminPdReview adminPdReview) {
        AdminPdReview adminPdReview2 = adminPdReviewRepository.save(adminPdReview);

        return adminPdReview2;
    }

    //    TODO: 삭제(delete)
    public boolean removeById(Long reviewId) {
        if (adminPdReviewRepository.existsById(reviewId) == true) {
            adminPdReviewRepository.deleteById(reviewId);
            return true;
        } else {
            return false;
        }
    }
}
