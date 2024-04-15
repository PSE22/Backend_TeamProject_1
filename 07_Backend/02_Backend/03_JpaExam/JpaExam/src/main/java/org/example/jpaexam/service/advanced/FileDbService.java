package org.example.jpaexam.service.advanced;

import lombok.extern.slf4j.Slf4j;
import org.example.jpaexam.model.entity.advanced.FileDb;
import org.example.jpaexam.repository.advanced.FileDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;
import java.util.UUID;


/**
 * packageName : org.example.jpaexam.service.advanced
 * fileName : FileDbService
 * author : GGG
 * date : 2024-03-22
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-22         GGG          최초 생성
 */
@Slf4j
@Service
public class FileDbService {
    @Autowired
    FileDbRepository fileDbRepository; // JPA DB 객체

    //    like 검색 + 전체조회 + 페이징처리
    public Page<FileDb> findAllByFileTitleContaining(
            String fileTitle,
            Pageable pageable
    ) {
        // JPA like 검색 함수 실행
        Page<FileDb> page
                = fileDbRepository
                .findAllByFileTitleContaining(fileTitle, pageable);
        return page;
    }

//    TODO: 저장/수정 : save
//          => uuid/파일 다운로드 url 생성한후 객체에 넣어 생성하는 알고리즘
//        1) insert : 기본키(uuid) 없으면
//          1-1) uuid 생성 : 자바생성함수 이용
//          1-2) 파일 다운로드 url 생성
//           a) 현재 기본주소 : http://localhost:8000
//           b) 추가 주소 붙임: /advanced/fileDb      -> 기본주소에 추가 주소 추가
//           c) 파일명(uuid) : 다운로드 파일명은 세계에서 유일 해야함 : uuid (~~~~...)
//           => http://localhost:8000//advanced/fileDb/~~~
//          1-3) uuid/파일다운로드 생성후 FileDb 객체에 넣어서(생성자,setter) 저장(save)
    public FileDb save(String uuid
                        , String fileTitle
                        , String fileContent
                        , MultipartFile file // TODO : file 업로드 클래스
        ){
        FileDb fileDb2 = null;

        try {
            if(uuid == null) {
//            TODO: insert : 기본키(uuid) 없으면
//              1-1) uuid 생성 : 자바생성함수 이용
//               복습 : uuid : 세계에서 유일하게 랜덤한 고유값
//                생성 예) xxxx-xxxx-xxxx-xxxx => - 제거 : xxxxxxxxxxxxxxxx
//                 replace : 문자열 찾아서 다른 문자열로 바꾸기 -> - 를 공백으로 변경
//                 replace 안해도됨  UUID 제작하는법만 알면됨
//                 사용법 : UUID.randomUUID().toString()
                String tmpUuid = UUID.randomUUID()
                                    .toString()
                                    .replace("-" , ""); // 업로드 핵심 로직

//            TODO : 1-2) 파일 다운로드 url 생성 : url 만들어주는 자바함수 : ServletUriComponentsBuilder
                String fileDownload = ServletUriComponentsBuilder
                                        .fromCurrentContextPath()   // spring 기본주소 : http://localhost:8000
                                        .path("/advanced/fileDb/")  // 추가 경로넣기    : /advanced/fileDb/
                                        .path(tmpUuid)              // uuid 넣기      :  xxxxx
                                        .toUriString(); // 합치기  : http://localhost:8000/advanced/fileDb/xxxxx

//          TODO: 1-3) uuid/파일다운로드 생성후 FileDb 객체에 넣어서(생성자,setter) 저장(save)
                FileDb fileDb = new FileDb(tmpUuid
                        ,fileTitle
                        ,fileContent
                        ,file.getOriginalFilename() // 파일업로드 당시 파일명
                        ,file.getBytes()            // 파일 사이즈
                        ,fileDownload);             // 새로만든 파일다운로드 url
                fileDb2 = fileDbRepository.save(fileDb);

            } else {

//            TODO: update : 기본키(uuid) 있으면
//                1-1) uuid 생성(생략) : 있으니까 생략함
//                1-2) 파일 다운로드 url 생성 실행
                String fileDownload = ServletUriComponentsBuilder     // 여기에 다양한 함수가 있고 .~~ 해서 함수를 꺼내쓰는거
                        .fromCurrentContextPath()                     // spring 기본주소 : http://localhost:8000
                        .path("/advanced/fileDb/")                    // 추가 경로넣기    : /advanced/fileDb/
                        .path(uuid)                                   // uuid 넣기      :  xxxxx
                        .toUriString();                               // url 합치기 -> uuid 에는 / 없어서 / 잘보고 넣기

//              TODO: 1-3) FileDB 객체 생성(생성자,setter)
                FileDb fileDb = new FileDb(           // entity 에 만든 순서대로 들어감
                        uuid,                         // 기존 uuid
                        fileTitle,                    // 제목
                        fileContent,                  // 내용
                        file.getOriginalFilename(),   // 업로드 할때 파일명 가져오는 함수
                        file.getBytes(),              // 업로드 이미지
                        fileDownload                  // 파일 다운로드 url
                );
                fileDb2 = fileDbRepository.save(fileDb);  // DB 수정
            }

        } catch (Exception e){
            log.debug(e.getMessage());
        }
        return fileDb2;
    }

//    상세조회
    public Optional<FileDb> findById(String uuid){
        return fileDbRepository.findById(uuid);
    }

//    삭제 : 기본키(uuid)
public boolean removeById(String uuid) {
    if(fileDbRepository.existsById(uuid) == true) {
        fileDbRepository.deleteById(uuid);
        return true;
    } else {
        return false;
    }
}
}
