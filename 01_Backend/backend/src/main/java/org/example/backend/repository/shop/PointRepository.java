package org.example.backend.repository.shop;

import org.example.backend.model.dto.shop.IPointDto;
import org.example.backend.model.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * packageName : org.example.backendproject.repository.shop
 * fileName : MyPointRepository
 * author : 5
 * date : 24. 5. 2.
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.         5          최초 생성
 */
@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    @Query(value = "SELECT \n" +
            "    case \n" +
            "        when ((select count(USE_POINT_ID) from TB_DETAIL_POINT WHERE USER_ID = :userId) != 0)\n" +
            "then\n" +
            "(SELECT SUM(POINT_ADD)\n" +
            "FROM TB_POINT\n" +
            "WHERE USER_ID = :userId\n" +
            "AND STATUS = 'Y'\n" +
            "GROUP BY USER_ID)\n" +
            "- \n" +
            "(\n" +
            "(SELECT POINT_ADD\n" +
            "FROM TB_POINT\n" +
            "WHERE POINT_ID = (\n" +
            "    SELECT POINT_ID\n" +
            "    FROM (\n" +
            "        SELECT *\n" +
            "        FROM TB_DETAIL_POINT\n" +
            "        WHERE POINT_ID IN (\n" +
            "            SELECT POINT_ID\n" +
            "            FROM TB_POINT\n" +
            "            WHERE USER_ID = :userId\n" +
            "            AND STATUS = 'Y'\n" +
            "        )\n" +
            "    )\n" +
            "    WHERE ROWNUM = 1\n" +
            "))\n" +
            "-\n" +
            "(SELECT POINT_BALANCE\n" +
            "FROM (\n" +
            "    SELECT POINT_BALANCE\n" +
            "    FROM TB_DETAIL_POINT\n" +
            "    WHERE POINT_ID IN (\n" +
            "        SELECT POINT_ID\n" +
            "        FROM TB_POINT\n" +
            "        WHERE USER_ID = :userId\n" +
            "        AND STATUS = 'Y'\n" +
            "    )\n" +
            "    ORDER BY USE_POINT_ID DESC\n" +
            ")\n" +
            "WHERE ROWNUM = 1))\n" +
            "else \n" +
            "(SELECT SUM(POINT_ADD)\n" +
            "FROM TB_POINT\n" +
            "WHERE USER_ID = :userId\n" +
            "AND STATUS = 'Y'\n" +
            "GROUP BY USER_ID)\n" +
            "END AS resultPoint\n" +
            "FROM DUAL", nativeQuery = true)
    Optional<IPointDto> findByResultPoint(@Param("userId") String userId);
}
