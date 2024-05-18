package org.example.backend.service;

import org.example.backend.model.entity.OrderCancel;
import org.example.backend.model.entity.Refund;
import org.example.backend.repository.order.RefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * packageName : org.example.backend.service
 * fileName : RefundService
 * author : kimtaewan
 * date : 2024-05-10
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10         kimtaewan          최초 생성
 */
@Service
public class RefundService {
    @Autowired
    RefundRepository refundRepository;

    public void updateRefund(Refund refund) {
        refundRepository.save(refund);
    }

    public Optional<Refund> findByOpId(Long opId) {
        return refundRepository.findByOpId(opId);
    }
}
