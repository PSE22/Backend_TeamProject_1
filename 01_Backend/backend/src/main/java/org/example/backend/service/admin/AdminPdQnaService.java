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

    //    TODO: 전체조회(read)
    public List<AdminPdQna> findAll() {
        List<AdminPdQna> list = adminPdQnaRepository.findAll();
        return list;
    }

    //    페이징 처리
    public Page<AdminPdQna> findAllByAdminPdQnaTitleContaining(String pdQnaTitle,
                                                                  Pageable pageable) {
        Page<AdminPdQna> page
                = adminPdQnaRepository
                .findAllByAdminPdQnaTitleContaining(pdQnaTitle, pageable);
        return page;
    }

    //    상세조회
    public Optional<AdminPdQna> findById(Long pdQnaId) {
        Optional<AdminPdQna> optionalAdminPdQna
                = adminPdQnaRepository.findById(pdQnaId);
        return optionalAdminPdQna;
    }

    //    TODO: 등록(insert),수정(update)
    public AdminPdQna save(AdminPdQna adminPdQna) {
        AdminPdQna adminPdQna2 = adminPdQnaRepository.save(adminPdQna);

        return adminPdQna2;
    }

    //    TODO: 삭제(delete)
    public boolean removeById(Long pdQnaId) {
        if (adminPdQnaRepository.existsById(pdQnaId) == true) {
            adminPdQnaRepository.deleteById(pdQnaId);
            return true;
        } else {
            return false;
        }
    }
}
