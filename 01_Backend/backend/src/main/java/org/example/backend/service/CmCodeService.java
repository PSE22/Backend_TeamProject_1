package org.example.backend.service;

import org.example.backend.model.CmCode;
import org.example.backend.repository.CmCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName : org.example.backend.service
 * fileName : CmCodeService
 * author : kimtaewan
 * date : 2024-05-10
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10         kimtaewan          최초 생성
 */
@Service
public class CmCodeService {
    @Autowired
    CmCodeRepository cmCodeRepository;

    public List<CmCode> findByUpCmCd() {
        return cmCodeRepository.findByUpCmCd();
    }

    //    주문 상세 오더코드
    public String findByCmCdName(Long orderId) {
        String findByCmCdName = cmCodeRepository.findByCmCdName(orderId);
        return findByCmCdName;
    }
}
