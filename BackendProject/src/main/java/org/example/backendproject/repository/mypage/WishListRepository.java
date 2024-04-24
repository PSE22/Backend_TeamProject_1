package org.example.backendproject.repository.mypage;

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
public interface WishListRepository extends JpaRepository<Wishlist, Integer> {
    @Query(value = "SELECT D.* FROM TB_DEPT D\n" +
            "WHERE D.DNAME LIKE '%'|| :dname ||'%'"
            , countQuery = "SELECT count(*) FROM TB_DEPT D\n" +
            "WHERE D.DNAME LIKE '%'|| :dname ||'%'"
            , nativeQuery = true)

    Page<Wishlist> findAllByPdNameContaining(
            @Param("pdName") String pdName,
            Pageable pageable
    );

}
