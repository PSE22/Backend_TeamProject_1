package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * packageName : Baekjoon
 * fileName : Simple_1929
 * author : GGG
 * date : 2024-03-08
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-08         GGG          최초 생성
 */
public class Simple_1929 {
    public static void main(String[] args) throws Exception{
//    시간 초과 : 1) BufferedReader
//               2) StringBuilder
        BufferedReader bufferedReader
                = new BufferedReader(new InputStreamReader(System.in));
        String[] str = bufferedReader.readLine().split(" ");  // 3 16 BufferedReader 는 함수 readLine 하나뿐
        int start = Integer.parseInt(str[0]);                       // 3   " " 기준으로 3 16 으로 잘라서 배열의 0번에 3, 1번에 16이
                                                                    //          문자열로 잘라져서 들어갔기 때문에 int로 바꿈
        int end = Integer.parseInt(str[1]);                         // 16

        StringBuilder stringBuilder = new StringBuilder();
        int[] arr = new int[1000001];                               // 숫자배열
//        숫자배열 초기화 : 2 ~ 1000000 까지 배열에 넣기
        for (int i = 2; i < 1000000; i++) {
//            arr[0] == arr[1] == 0 => 소수 아님
            arr[i] = i;     // 소수이면 그값 그대로 넣고, 아니면 0으로 넣기
        }
//        에라토스테네스 체 반복문 실행 (2중반복문)
//        배수들은 소수아님 => 배수는 0 넣기
        for (int i = 2; i <1000000; i++) {
//            소수판단 반복문 : 배수들만 판단 2*i
            for (int j = 2*i; j <1000000; j += i) {
                if(arr[i] == 0) continue;   // 이미 배열값이 0이면 스킵
//                소수판단 조건문 : 나누었을때 0이 되면 소수가 아님
                if(j % i == 0) arr[j] = 0;  // 배수는 소수 아님 : 표시(0저장)
            }
        }
//        결과 출력 : 소수 인것만 출력 : 숫자배열에서 0보다 큰것만 출력
        for (int i = start; i <= end; i++) {
            if(arr[i] > 0) {
                stringBuilder.append(i + "\n");
            }
        }
        System.out.println(stringBuilder);
    }
}
