package org.example.backendproject.repository.shop;

import org.example.backendproject.model.entity.CmCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.backendproject.repository.shop
 * fileName : CmCodeRepository
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
@Repository
public interface CmCodeRepository extends JpaRepository<CmCode, String> {
}
