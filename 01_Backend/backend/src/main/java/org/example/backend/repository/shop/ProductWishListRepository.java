package org.example.backend.repository.shop;

import org.example.backend.model.common.PdIdUserIdPk;
import org.example.backend.model.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.backendproject.repository.shop
 * fileName : ProductWishListRepository
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
@Repository
public interface ProductWishListRepository extends JpaRepository<Wishlist, PdIdUserIdPk> {
    @Query(value = "SELECT count(*)\n" +
            "FROM TB_WISHLIST\n" +
            "WHERE PD_ID = :pdId\n" +
            "AND USER_ID = :userId"
            , nativeQuery = true)
    Integer findBypPdIdAndUserId(@Param("pdId") Long pdId, @Param("userId") String userId);
}
