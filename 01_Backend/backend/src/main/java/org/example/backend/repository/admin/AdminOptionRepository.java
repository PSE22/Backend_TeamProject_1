package org.example.backend.repository.admin;

import org.example.backend.model.entity.admin.AdminOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminOptionRepository extends JpaRepository<AdminOption, Long> {
    @Query(value = "SELECT * FROM TB_OPTION\n" +
            "WHERE OP_NAME LIKE '%' || :opName || '%'"
            ,countQuery = "SELECT count(*) FROM TB_OPTION\n" +
            "WHERE OP_NAME LIKE '%' || :opName || '%'"
            ,nativeQuery = true
    )
    Page<AdminOption> findAllByAdminOptionNameContaining(@Param("opName") String opName,
                                                           Pageable pageable
    );
}
