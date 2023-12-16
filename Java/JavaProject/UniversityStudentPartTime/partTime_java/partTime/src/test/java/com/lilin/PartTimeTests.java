package com.lilin;

import com.lilin.utils.SecurityUtils;

/**
 * Created by LiLin on 2022/9/21/19:54:22
 */
public class PartTimeTests {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI3OTYwMjc0NGY5Yjg0ZjRlYWI1M2I5OWY2NGZhMTA2OCIsInN1YiI6IjUiLCJpc3MiOiJzZyIsImlhdCI6MTY2NDc2NTQwOCwiZXhwIjoxNjY0ODUxODA4fQ.4Pc9q7FRiz2K1-6DToD23_3s5Ibo2X9utdVeUHlhQLU";
        String token2 = "{\"token\":\"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJiMDU3NDczNjQzNDU0MGE3YTk1MTgzOThlMTExYjJhMyIsInN1YiI6IjUiLCJpc3MiOiJzZyIsImlhdCI6MTY2NDc2NjMzMCwiZXhwIjoxNjY0ODUyNzMwfQ.8HG_ne9ETLWYIoO7G9F5c5iCgeq-BcrriYLzEHhC8nE\"}";

        System.out.println(token.contains("token"));
        System.out.println(token2.contains("token"));
    }
}
