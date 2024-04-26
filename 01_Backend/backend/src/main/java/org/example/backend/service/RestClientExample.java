//package org.example.backend.service;
//
///**
// * packageName : org.example.backend.service
// * fileName : RestClientExample
// * author : kimtaewan
// * date : 2024-04-26
// * description : 권한 인증 정상작동 테스트
// * 요약 :
// * <p>
// * ===========================================================
// * DATE            AUTHOR             NOTE
// * -----------------------------------------------------------
// * 2024-04-26         kimtaewan          최초 생성
// */
//
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//
//public class RestClientExample {
//
//    public static void main(String[] args) {
//        // JWT 토큰
//        String jwtToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0dXNlcjEiLCJpYXQiOjE3MTQxMTA3OTcsImV4cCI6MTcxNDExMTM5N30.0y9HGrGQhbYLAKQnlTZ_a0JFE7PwMb3GnrcaikPiWFVbpxpTEmZ8Dh0w7gfyr5leJgkek_W_HJPxX_S55QKLIg";
//
//        // REST API 엔드포인트
//        String endpoint = "http://localhost:8000/api/mypage/testuser1/editProfile";
//
//        // HttpClient 생성
//        HttpClient httpClient = HttpClient.newHttpClient();
//
//        // GET 요청 생성
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(endpoint))
//                .header("Authorization", "Bearer " + jwtToken)  // JWT 토큰을 헤더에 포함
//                .build();
//
//        // 요청 보내기
//        try {
//            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//
//            // 응답 출력
//            System.out.println("Response Code: " + response.statusCode());
//            System.out.println("Response Body: " + response.body());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}