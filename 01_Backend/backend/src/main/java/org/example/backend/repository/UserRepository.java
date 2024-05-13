package org.example.backend.repository;

import org.example.backend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.backend.repository
 * fileName : UserRepository
 * author : kimtaewan
 * date : 2024-04-18
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-18         kimtaewan          최초 생성
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByStatusAndDelDate(String status, String delDate);

    //    값이 있을수도 있고 없을수도 있는 상황에 쓰는 클래스
    @Query(value = "SELECT USER_NAME FROM TB_USER " +
            "WHERE USER_ID = :userId",
            nativeQuery = true)
    String findByUserName(@Param("userId") String userId);

    String findByUserId(String userId);
}
