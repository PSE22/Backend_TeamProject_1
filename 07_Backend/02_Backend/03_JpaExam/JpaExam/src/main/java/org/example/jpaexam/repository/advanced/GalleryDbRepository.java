package org.example.jpaexam.repository.advanced;

import org.example.jpaexam.model.entity.advanced.GalleryDb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.jpaexam.repository.advanced
 * fileName : GalleryDbRepository
 * author : GGG
 * date : 2024-03-25
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-25         GGG          최초 생성
 */
@Repository
public interface GalleryDbRepository extends JpaRepository<GalleryDb, String> {
    //    제목(fileTitle) like 검색 : @Query()
//    TODO : soft delete : 삭제하지 않고 화면에만 안보이게 하는것
//          DELETE_YN = 'N' (조회시) => @Where() : JPA 사용(JPQL 만 적용)
//                                  => nativeQuery = ture(오라클 sql 은 직접 작성)
    @Query(value = "SELECT * FROM TB_GALLERY\n" +
            "WHERE GALLERY_TITLE LIKE '%' || :galleryTitle || '%'" +
            "AND DELETE_YN = 'N'"
            , countQuery = "SELECT * FROM TB_GALLERY\n" +
            "WHERE GALLERY_TITLE LIKE '%' || :galleryTitle || '%'" +
            "AND DELETE_YN = 'N'"
            , nativeQuery = true)
    Page<GalleryDb> findAllByGalleryTitleContaining(
            @Param("galleryTitle") String galleryTitle,
            Pageable pageable
    );
}
