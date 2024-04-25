package org.example.backendproject.repository.mypage;

import org.example.backendproject.model.common.PdIdUserIdPk;
import org.example.backendproject.model.dto.mypage.WishlistDto;
import org.example.backendproject.model.entity.Wishlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
public interface WishlistRepository extends JpaRepository<Wishlist, PdIdUserIdPk> {
    List<Wishlist> findByPdId(Integer pdId);
    @Query(value = "SELECT PD.PD_ID AS pdId, PD.PD_NAME AS pdName, PD.PD_PRICE AS pdPrice, PI.PD_IMG_URL AS pdImgUrl " +
            "FROM TB_PRODUCT PD " +
            "JOIN TB_PRODUCT_IMAGE PI ON PD.PD_ID = PI.PD_ID " +
            "JOIN TB_WISHLIST WS ON PD.PD_ID = WS.PD_ID " +
            "WHERE WS.STATUS = 'Y'", nativeQuery = true)


    Page<WishlistDto> selectWishlistContaining(
            Integer pdId,
            Pageable pageable
    );

}
