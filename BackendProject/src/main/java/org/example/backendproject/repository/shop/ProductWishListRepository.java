package org.example.backendproject.repository.shop;

import org.example.backendproject.model.common.PdIdUserIdPk;
import org.example.backendproject.model.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
