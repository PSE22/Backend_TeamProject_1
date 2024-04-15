/**
 * packageName : PACKAGE_NAME
 * fileName : ReFunction
 * author : GGG
 * date : 2024-03-08
 * description :
 * 요약 :
 *     함수 : 기능을 미리 정의해놓고 호출해서 사용하는 것(코딩 생산성 향상)
 *     재귀함수  : 자기자신을 계속 호출하는 함수
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-08         GGG          최초 생성
 */
public class ReFunction {

//     전역함수(정적함수) : static 함수
    public  static  int reSum(int n){

        if(n == 1) return 1; // 결과 내보내기 (return = 함수 종료(break)) : 종료조건

//      n = 3 -> reSum(3-1) + 3 => reSum(2) + 3   -> n == 1 이 아니기때문에 return(종료) 안됨 
//      n = 2 -> reSum(2-1) + 3 => reSum(1) + 3   -> n == 1 이 아니기때문에 return(종료) 안됨
//      n = 1 -> reSum(1)       => (1 + 2) + 3    -> n == 1 이기때문에 종료되며 앞의 n = 3 , n = 2 일때의 n을 더함
        return reSum(n-1) + n;
    }


    public static void main(String[] args) {
//        예제 : 1 ~ n 까지의 합을 계산해보세요(누적합)
//        1) 반복문 : 누적합
//        2) 재귀함수 이용
        int n=3;
        System.out.println("1 ~ 3 : " + reSum(n));
    }
}
