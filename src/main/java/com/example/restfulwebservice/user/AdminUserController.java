package com.example.restfulwebservice.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
    private UserDaoService service;

    public AdminUserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers() {
        List<User> users = service.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name",  "joinDate", "password"); // 필터 가져오고싶은 필드 선언

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter); // User 도메인에 선언되어있는 필터이름 적용

        MappingJacksonValue mapping = new MappingJacksonValue(users); // 필터적용
        mapping.setFilters(filters);

        return mapping;
    }

    // GET /admin/users/1 -> /admin/v1/users/1
    //@GetMapping("/v1/users/{id}")
    //@GetMapping(value = "/users/{id}/", params = "version=1")
    //@GetMapping(value = "/users/{id}", headers = "X-API-VERSION=1")
    @GetMapping(value = "/v1/users/{id}", produces =  "application/vnd.company.appv1+json")
    public MappingJacksonValue retrieveUserV1(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name",  "password", "ssn"); // 필터 가져오고싶은 필드 선언

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter); // User 도메인에 선언되어있는 필터이름 적용

        MappingJacksonValue mapping = new MappingJacksonValue(user); // 필터적용
        mapping.setFilters(filters);

        return mapping;
    }

    //@GetMapping("/v2/users/{id}")
    //@GetMapping(value = "/users/{id}/", params = "version=2")
    //@GetMapping(value = "/users/{id}", headers = "X-API-VERSION=2")
    @GetMapping(value = "/v2/users/{id}", produces =  "application/vnd.company.appv2+json")
    public MappingJacksonValue retrieveUserV2(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        // User -> User2
        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user, userV2); // id, name, joinDate, password, ssn
        userV2.setGrade("VIP");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name",  "joinDate", "grade"); // 필터 가져오고싶은 필드 선언

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter); // User 도메인에 선언되어있는 필터이름 적용

        MappingJacksonValue mapping = new MappingJacksonValue(userV2); // 필터적용
        mapping.setFilters(filters);

        return mapping;
    }
}
