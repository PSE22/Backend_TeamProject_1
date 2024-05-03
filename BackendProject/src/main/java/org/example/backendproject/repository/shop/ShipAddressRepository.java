package org.example.backendproject.repository.shop;

import org.example.backendproject.model.entity.ShipAddress;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface ShipAddressRepository extends JpaRepository<ShipAddress, Integer> {
    Optional<ShipAddress> findByUserId(String userId);
}