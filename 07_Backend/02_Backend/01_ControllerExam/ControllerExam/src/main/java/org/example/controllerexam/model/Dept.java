package org.example.controllerexam.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : org.example.controllerexam.model
 * fileName : Dept
 * author : GGG
 * date : 2024-03-12
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-12         GGG          최초 생성
 */
@Setter
@Getter
@AllArgsConstructor
@ToString
public class Dept {
    int dno;
    String dname;
    String loc;
}
