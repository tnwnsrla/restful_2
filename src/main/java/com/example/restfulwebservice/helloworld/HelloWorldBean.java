package com.example.restfulwebservice.helloworld;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getter, Setter, 생성자, toString
@AllArgsConstructor // 파라미터 생성자
@NoArgsConstructor // 디폴트 생성자
public class HelloWorldBean {
    private String message;
}
