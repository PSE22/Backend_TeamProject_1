package org.example.backend.repository.shop;

import org.example.backend.model.entity.ShipAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * packageName : org.example.backendproject.repository.shop
 * fileName : ShipAddressRepository
 * author : SAMSUNG
 * date : 2024-05-01
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-01         SAMSUNG          최초 생성
 */
@Repository
public interface ShipAddressRepository extends JpaRepository<ShipAddress, Long> {
    @Query(value = "SELECT * FROM TB_SHIP_ADDRESS\n" +
            "WHERE USER_ID LIKE '%' || :userId || '%'"
            ,countQuery = "SELECT count(*) FROM TB_SHIP_ADDRESS\n" +
            "WHERE USER_ID LIKE '%' || :userId || '%'"
            ,nativeQuery = true
    )
    Optional<ShipAddress> findByUserId(String userId);
}
