package org.example.backend.repository.mypage;

import org.example.backend.model.common.PtIdUseptIdPk;
import org.example.backend.model.entity.DetailPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.backendproject.repository.mypage
 * fileName : PointDetailRepository
 * author : GGG
 * date : 2024-04-26
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-26         GGG          최초 생성
 */
@Repository
public interface PointDetailRepository extends JpaRepository<DetailPoint, PtIdUseptIdPk> {
}
