package org.example.backendproject.service.shop;

import org.example.backendproject.model.entity.CmCode;
import org.example.backendproject.repository.shop.CmCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.backendproject.service.shop
 * fileName : CmCodeService
 * author : SAMSUNG
 * date : 2024-04-25
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-25         SAMSUNG          최초 생성
 */
@Service
public class CmCodeService {
    @Autowired
    CmCodeRepository cmCodeRepository;

    /**
     * 코드 전체 조회
     * @return
     */
    public List<CmCode> findAll() {
        List<CmCode> list = cmCodeRepository.findAll(); // db 전체조회 함수 호출
        return list;
    }
}
