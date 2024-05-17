package org.example.backend.service.admin;

import org.example.backend.model.entity.admin.AdminPdQnaReply;
import org.example.backend.repository.admin.AdminPdQnaReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminPdQnaReplyService {

    @Autowired
    AdminPdQnaReplyRepository adminPdQnaReplyRepository;

    //    TODO: 전체조회(read)
    public List<AdminPdQnaReply> findAll() {
        List<AdminPdQnaReply> list = adminPdQnaReplyRepository.findAll();
        return list;
    }

    //    페이징 처리
    public Page<AdminPdQnaReply> findAllByAdminPdQnaReplyContentContaining(String pdQnaReplyContent,
                                                               Pageable pageable) {
        Page<AdminPdQnaReply> page
                = adminPdQnaReplyRepository
                .findAllByAdminPdQnaReplyContentContaining(pdQnaReplyContent, pageable);
        return page;
    }

    //    상세조회
    public Optional<AdminPdQnaReply> findById(Long pdQnaReplyId) {
        Optional<AdminPdQnaReply> optionalAdminPdQnaReply
                = adminPdQnaReplyRepository.findById(pdQnaReplyId);
        return optionalAdminPdQnaReply;
    }

    //    TODO: 등록(insert),수정(update)
    public AdminPdQnaReply save(AdminPdQnaReply adminPdQnaReply) {
        AdminPdQnaReply adminPdQnaReply2 = adminPdQnaReplyRepository.save(adminPdQnaReply);

        return adminPdQnaReply2;
    }

    //    TODO: 삭제(delete)
    public boolean removeById(Long pdQnaReplyId) {
        if (adminPdQnaReplyRepository.existsById(pdQnaReplyId) == true) {
            adminPdQnaReplyRepository.deleteById(pdQnaReplyId);
            return true;
        } else {
            return false;
        }
    }
}
