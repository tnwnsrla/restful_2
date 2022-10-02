package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "user") // user 입장에서는 post 라는 객체를 1:다 로 가져오면 된다.
    private List<Post> posts;

    public User(Integer id, String name, Date joinDate, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;
    }
}
