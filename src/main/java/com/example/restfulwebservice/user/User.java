package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value={"password", "ssn"})
//@JsonFilter("UserInfo")
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
@Table(name = "User_JPA")
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min=2, message="Name 은 2글자 이상을 입력해주세요.")
    @ApiModelProperty(notes = "사용자 이름을 입력해주세요")
    private String name;
    @Past
    @ApiModelProperty(notes = "사용자 등록일을 입력해주세요")
    private Date joinDate;

    @ApiModelProperty(notes = "사용자 패스워드를 입력해주세요")
    private String password;
    @ApiModelProperty(notes = "사용자 주민번호를 입력해주세요")
    private String ssn;
}
