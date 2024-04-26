package org.example.backendproject.repository.shop;

import org.example.backendproject.model.dto.shop.ICartDto;
import org.example.backendproject.model.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
public interface CartRepository extends JpaRepository<Cart, Integer> {
//    장바구니 전체조회
    @Query(value = "SELECT\n" +
            "C.CART_ID AS cartId," +
            "C.CART_COUNT AS cartCount,\n" +
            "O.OP_NAME AS opName,\n" +
            "O.OP_PRICE AS opPrice,\n" +
            "PD.PD_NAME AS pdName,\n" +
            "PD.PD_PRICE AS pdPrice,\n" +
            "PI.PD_IMG_URL AS pdImgUrl\n" +
            "FROM TB_CART C, TB_OPTION O, TB_PRODUCT PD, TB_PRODUCT_IMAGE PI, TB_USER U\n" +
            "WHERE C.OP_ID = O.OP_ID\n" +
            "AND O.PD_ID = PD.PD_ID\n" +
            "AND PD.PD_ID = PI.PD_ID\n"+
            "AND C.USER_ID = :userId",
            countQuery = "SELECT\n" +
                    "count(*)\n" +
                    "FROM TB_CART C, TB_OPTION O, TB_PRODUCT PD, TB_PRODUCT_IMAGE PI\n" +
                    "WHERE C.OP_ID = O.OP_ID\n" +
                    "AND O.PD_ID = PD.PD_ID\n" +
                    "AND PD.PD_ID = PI.PD_ID\n"+
                    "AND C.USER_ID = :userId",
            nativeQuery = true)
    Page<ICartDto> selectByCartContaining(@Param("userId") String userId, Pageable pageable);
}
