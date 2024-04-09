package Baekjoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * packageName : PACKAGE_NAME
 * fileName : Baekjoon.Simple_2751
 * author : GGG
 * date : 2024-03-08
 * description : 2751 수 정렬하기 2(실버 5)
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-08         GGG          최초 생성
 */
public class Simple_2751 {
    public static void main(String[] args) {
//     백준 : 시간초과 =>
//     1) BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//            1줄씩 읽기 함수 : bufferedReader.readLine()
//     2) 문자열 : String => StringBuilder 클래스 사용
//          stringBuilder.append("문자열") 반복해서 문자열붙이기 한다음
//          마지막(반복문 밖에서)에 출력 : System.out.println(stringBuilder);
//        정렬문제 : 입력값 정렬되었으면 => Collection.sort() 사용
//                  아니면           => Arrays.sort() 사용
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();              // 반복문 횟수
//       시간초과 해결 : StringBuilder
        StringBuilder stringBuilder = new StringBuilder();
//        숫자배열 준비
        ArrayList<Integer> list = new ArrayList<>();
//        입력값 -> 숫자배열에 넣기
        for (int i = 0; i < num; i++) {
            int score = scanner.nextInt();      // 점수값
            list.add(score);                    // 배열에저장
        }

//        오름차순 정렬 : Collection.sort()
        Collections.sort(list);
//        결과출력
        for (int i = 0; i <list.size(); i++) {
            stringBuilder.append(list.get(i)); // 문자열 붙이기
            stringBuilder.append("\n");        // 줄바꿈
        }
//         시간초과시 출력함수는 1번만 호출할것
        System.out.println(stringBuilder);
    }
}
