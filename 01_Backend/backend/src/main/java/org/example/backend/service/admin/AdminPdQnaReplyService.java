package org.example.backend.service.admin;

import org.example.backend.model.entity.admin.AdminPdQna;
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

    //    TODO: 전체조회(read)r
    public List<AdminPdQnaReply> findAll() {
        List<AdminPdQnaReply> list = adminPdQnaReplyRepository.findAll();
        return list;
    }

    //    페이징 처리
    public Page<AdminPdQnaReply> findAllByAdminPdQnaReplyContentContaining(String pdQnaReplyContent,
                                                               Pageable pageable) {
        //    DB like 검색 함수 실행 : 페이징 처리
        Page<AdminPdQnaReply> page
                = adminPdQnaReplyRepository
                .findAllByAdminPdQnaReplyContentContaining(pdQnaReplyContent, pageable);
        return page;
    }

    //    상세조회
    public Optional<AdminPdQnaReply> findById(Long pdQnaReplyId) {
        //    JPA 상세조회 함수 실행
        Optional<AdminPdQnaReply> optionalAdminPdQnaReply
                = adminPdQnaReplyRepository.findById(pdQnaReplyId);
        return optionalAdminPdQnaReply;
    }

    //    TODO: 등록(insert)c,수정(update)u
    public AdminPdQnaReply save(AdminPdQnaReply adminPdQnaReply) {
        //    JPA 저장 함수 실행 : return 값 : 저장된 객체
        AdminPdQnaReply adminPdQnaReply2 = adminPdQnaReplyRepository.save(adminPdQnaReply);

        return adminPdQnaReply2;
    }

    //    TODO: 삭제(delete)d
    public boolean removeById(Long pdQnaReplyId) {
        if (adminPdQnaReplyRepository.existsById(pdQnaReplyId) == true) {
            adminPdQnaReplyRepository.deleteById(pdQnaReplyId);
            return true;
        } else {
            return false;
        }
    }
}
