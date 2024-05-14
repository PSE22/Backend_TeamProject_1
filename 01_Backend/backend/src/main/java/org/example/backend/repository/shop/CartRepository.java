package org.example.backend.repository.shop;

import org.example.backend.model.dto.shop.ICartDto;
import org.example.backend.model.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : org.example.backendproject.repository.shop
 * fileName : CartRepository
 * author : gumiji
 * date : 4/23/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 4/23/24         gumiji          최초 생성
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    //    장바구니 전체조회
    @Query(value = "SELECT\n" +
            "C.CART_ID AS cartId, C.CART_COUNT AS cartCount, " +
            "C.OP_ID AS opId,\n" +
            "C.USER_ID AS userId,\n" +
            "O.OP_NAME AS opName,\n" +
            "O.OP_PRICE AS opPrice,\n" +
            "PD.PD_NAME AS pdName,\n" +
            "PD.PD_PRICE AS pdPrice,\n" +
            "PD.PD_THUMBNAIL AS pdThumbnail,\n" +
            "PD.PD_ID AS pdId\n" +
            "FROM TB_CART C, TB_OPTION O, TB_PRODUCT PD WHERE C.OP_ID = O.OP_ID\n" +
            "AND O.PD_ID = PD.PD_ID\n" +
            "AND PD.STATUS = 'Y' AND C.USER_ID = :userId",
            nativeQuery = true)
    List<ICartDto> findByUserId(@Param("userId") String userId);

}
