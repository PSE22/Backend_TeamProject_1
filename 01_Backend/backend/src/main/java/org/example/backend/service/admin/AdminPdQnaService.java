package org.example.backend.service.admin;

import org.example.backend.model.entity.admin.AdminPdQna;
import org.example.backend.repository.admin.AdminPdQnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminPdQnaService {

    @Autowired
    AdminPdQnaRepository adminPdQnaRepository;

    //    TODO: 전체조회(read)r
    public List<AdminPdQna> findAll() {
        List<AdminPdQna> list = adminPdQnaRepository.findAll();
        return list;
    }

    //    페이징 처리
    public Page<AdminPdQna> findAllByAdminPdQnaTitleContaining(String pdQnaTitle,
                                                                  Pageable pageable) {
        //    DB like 검색 함수 실행 : 페이징 처리
        Page<AdminPdQna> page
                = adminPdQnaRepository
                .findAllByAdminPdQnaTitleContaining(pdQnaTitle, pageable);
        return page;
    }

    //    상세조회
    public Optional<AdminPdQna> findById(Long pdQnaId) {
        //    JPA 상세조회 함수 실행
        Optional<AdminPdQna> optionalAdminPdQna
                = adminPdQnaRepository.findById(pdQnaId);
        return optionalAdminPdQna;
    }

    //    TODO: 등록(insert)c,수정(update)u
    public AdminPdQna save(AdminPdQna adminPdQna) {
        //    JPA 저장 함수 실행 : return 값 : 저장된 객체
        AdminPdQna adminPdQna2 = adminPdQnaRepository.save(adminPdQna);

        return adminPdQna2;
    }

    //    TODO: 삭제(delete)d
    public boolean removeById(Long pdQnaId) {
        if (adminPdQnaRepository.existsById(pdQnaId) == true) {
            adminPdQnaRepository.deleteById(pdQnaId);
            return true;
        } else {
            return false;
        }
    }
}
