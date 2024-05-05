package org.example.backend.service.admin;

import org.example.backend.model.entity.admin.AdminOption;
import org.example.backend.repository.admin.AdminOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminOptionService {
    @Autowired
    AdminOptionRepository adminOptionRepository;

    //    TODO: 전체조회(read)r
    public List<AdminOption> findAll() {
        List<AdminOption> list = adminOptionRepository.findAll();
        return list;
    }

    //    페이징 처리
    public Page<AdminOption> findAllByAdminOptionNameContaining(String opName,
                                                                Pageable pageable) {
        //    DB like 검색 함수 실행 : 페이징 처리
        Page<AdminOption> page
                = adminOptionRepository
                .findAllByAdminOptionNameContaining(opName, pageable);
        return page;
    }

    //    상세조회
    public Optional<AdminOption> findById(Long opId) {
        //    JPA 상세조회 함수 실행
        Optional<AdminOption> optionalAdminOption
                = adminOptionRepository.findById(opId);
        return optionalAdminOption;
    }

    //    TODO: 등록(insert)c,수정(update)u
    public AdminOption save(AdminOption adminOption) {
        //    JPA 저장 함수 실행 : return 값 : 저장된 객체
        AdminOption adminOption2 = adminOptionRepository.save(adminOption);

        return adminOption2;
    }

    //    TODO: 삭제(delete)d
    public boolean removeById(Long opId) {
        if (adminOptionRepository.existsById(opId) == true) {
            adminOptionRepository.deleteById(opId);
            return true;
        } else {
            return false;
        }
    }
}
