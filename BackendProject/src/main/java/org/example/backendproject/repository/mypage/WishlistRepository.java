package org.example.backendproject.repository.mypage;

import org.example.backendproject.model.common.PdIdUserIdPk;
import org.example.backendproject.model.dto.mypage.WishlistDto;
import org.example.backendproject.model.entity.Wishlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.backendproject.repository.mypage
 * fileName : WishListRepository
 * author : GGG
 * date : 2024-04-22
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-22         GGG          최초 생성
 */
@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    @Query(value = "select * from (\n" +
            "    SELECT PD.PD_ID AS pdId , PD.PD_NAME AS pdName, PD.PD_PRICE AS pdPrice, PI.PD_IMG_URL AS pdImgUrl \n" +
            "    FROM TB_PRODUCT PD, TB_PRODUCT_IMAGE PI, TB_WISHLIST WS\n" +
            "    WHERE PD.PD_ID = PI.PD_ID\n" +
            "    AND PD.PD_ID = WS.PD_ID\n)"

            , nativeQuery = true)

    Page<WishlistDto> selectWishlistContaining(
            @Param("pdId") Integer pdId,
            Pageable pageable
    );

}
