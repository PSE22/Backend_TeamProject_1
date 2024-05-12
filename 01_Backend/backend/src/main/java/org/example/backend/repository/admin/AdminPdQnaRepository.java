package org.example.backend.repository.admin;

import org.example.backend.model.entity.admin.AdminPdQna;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminPdQnaRepository extends JpaRepository<AdminPdQna, Long> {
    @Query(value = "SELECT * FROM TB_PD_QNA\n" +
            "WHERE PD_QNA_TITLE LIKE '%' || :pdQnaTitle || '%'"
            ,countQuery = "SELECT count(*) FROM TB_PD_QNA\n" +
            "WHERE PD_QNA_TITLE LIKE '%' || :pdQnaTitle || '%'"
            ,nativeQuery = true
    )
    Page<AdminPdQna> findAllByAdminPdQnaTitleContaining(@Param("pdQnaTitle") String pdQnaTitle,
                                                           Pageable pageable
    );
}
