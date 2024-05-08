package org.example.backend.service.mypage;

import org.example.backend.model.dto.shop.IQnaDto;
import org.example.backend.model.dto.shop.IReviewDto;
import org.example.backend.repository.mypage.MyQnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.backend.service.mypage
 * fileName : MyQnaController
 * author : gumiji
 * date : 5/7/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 5/7/24         gumiji          최초 생성
 */
@Service
public class MyQnaService {
    @Autowired
    MyQnaRepository myQnaRepository;

    public Page<IQnaDto> findByUserId(String userId,
                                      Pageable pageable) {
        Page<IQnaDto> page = myQnaRepository.findByUserId(userId, pageable);
        return page;
    }
}
