package org.example.backend.service.mypage;

import org.example.backend.model.dto.shop.IReviewDto;
import org.example.backend.repository.mypage.MyReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.backend.service.mypage
 * fileName : MyReviewService
 * author : gumiji
 * date : 5/5/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 5/5/24         gumiji          최초 생성
 */
@Service
public class MyReviewService {
    @Autowired
    MyReviewRepository myReviewRepository;

    public Page<IReviewDto> findByUserId(String userId,
                                         Pageable pageable) {
        Page<IReviewDto> page = myReviewRepository.findByUserId(userId, pageable);
        return page;
    }

}
