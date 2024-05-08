package org.example.backend.service.shop;

import org.example.backend.model.dto.shop.IOptionDto;
import org.example.backend.repository.shop.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName : org.example.backendproject.service.shop
 * fileName : OptionService
 * author : sjuk2
 * date : 2024-05-01
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-01         sjuk2          최초 생성
 */
@Service
public class OptionService {
    @Autowired
    OptionRepository optionRepository;

//    상품 옵션 전체 조회
    public List<IOptionDto> findById(int pdId) {
        List<IOptionDto> optionDtoList = optionRepository.findAllByPdidContaining(pdId);
        return optionDtoList;
    }
}
