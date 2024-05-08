package org.example.backend.repository.admin;

import org.example.backend.model.entity.admin.AdminPdQnaReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminPdQnaReplyRepository extends JpaRepository<AdminPdQnaReply, Long> {
    @Query(value = "SELECT * FROM TB_PD_QNA_REPLY\n" +
            "WHERE PD_QNA_REPLY_CONTENT LIKE '%' || :pdQnaReplyContent || '%'"
            ,countQuery = "SELECT count(*) FROM TB_PD_QNA_REPLY\n" +
            "WHERE PD_QNA_REPLY_CONTENT LIKE '%' || :pdQnaReplyContent || '%'"
            ,nativeQuery = true
    )
    Page<AdminPdQnaReply> findAllByAdminPdQnaReplyContentContaining(@Param("pdQnaReplyContent") String pdQnaReplyContent,
                                                        Pageable pageable
    );
}
