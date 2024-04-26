package org.example.backendproject.repository.shop;

import org.example.backendproject.model.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.backendproject.repository.shop
 * fileName : ProductImageRepository
 * author : sjuk2
 * date : 2024-04-26
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-26         sjuk2          최초 생성
 */
@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
}
