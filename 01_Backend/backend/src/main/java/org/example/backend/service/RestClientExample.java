package org.example.backend.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * packageName : org.example.backend.service
 * fileName : RestClientExample
 * author : kimtaewan
 * date : 2024-04-26
 * description : 권한 인증 정상작동 테스트
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-26         kimtaewan          최초 생성
 */
public class RestClientExample {

    public static void main(String[] args) {
        // JWT 토큰
        String jwtToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0dXNlcjEiLCJpYXQiOjE3MTQ0NDUyNTIsImV4cCI6MTcxNDQ0NTg1Mn0.axxVKo20aVETVxTiy8ePalQ0qQN2TwHmSklT4YA-GEQVL0wOP1r4rmSWQs5As6N6n7489xJYdbX7yyjGvv4t1A";
        // REST API 엔드포인트
        String endpoint = "http://localhost:8000/api/shop/cart";

        // HttpClient 생성
        HttpClient httpClient = HttpClient.newHttpClient();

        // GET 요청 생성
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Authorization", "Bearer " + jwtToken)  // JWT 토큰을 헤더에 포함
                .build();

        // 요청 보내기
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // 응답 출력
            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}