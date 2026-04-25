package com.microservices.userservice.user.infrastructure.adapter.in.rest;

import com.microservices.userservice.shared.domain.model.PageDomain;
import com.microservices.userservice.user.domain.model.Users;
import com.microservices.userservice.user.domain.port.in.*;
import com.microservices.userservice.user.infrastructure.adapter.in.rest.dto.UserCreateRequest;
import com.microservices.userservice.user.infrastructure.adapter.in.rest.dto.UserResponse;
import com.microservices.userservice.user.infrastructure.adapter.in.rest.dto.UserUpdateRequest;
import com.microservices.userservice.user.infrastructure.adapter.in.rest.mapper.rest.UserMapperRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController{

    private final SaveUserPort saveUserPort;
    private final UpdateUserPort updateUserPort;
    private final UpdateUserStatusPort updateUserStatusPort;
    private final ListUsersPort listUsersPort;
    private final FindUserByIdPort findUserByIdPort;
    private final FindUsersByTextPort findUsersByTextPort;
    private final DeleteUserPort deleteUserPort;
    private final UserMapperRest mapper;


    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        Users users = findUserByIdPort.findById(id);
        return ResponseEntity.ok(mapper.toResponse(users));
    }

    @GetMapping("/buscar")
    public ResponseEntity<PageDomain<UserResponse>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        PageDomain<Users> pages = listUsersPort.list(page,size);
        PageDomain<UserResponse> responsePageDomain = new PageDomain<>(
                pages.content().stream().map(mapper::toResponse).toList(),
                pages.currentPage(),
                pages.totalPages(),
                pages.totalElements()
        );
        return ResponseEntity.ok(responsePageDomain);
    }

    @GetMapping
    public ResponseEntity<PageDomain<UserResponse>> findByText(
            @RequestParam String text,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        PageDomain<Users> pages = findUsersByTextPort.findByText(text, page, size);
        PageDomain<UserResponse> pageDomain = new PageDomain<>(
                pages.content().stream().map(mapper::toResponse).toList(),
                pages.currentPage(),
                pages.totalPages(),
                pages.totalElements()

        );

        return ResponseEntity.ok(pageDomain);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest request){
        Users users = updateUserPort.update(id, mapper.toDomain(request));
        return ResponseEntity.ok(mapper.toResponse(users));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        deleteUserPort.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Users> status(@PathVariable Long id, @RequestParam Boolean status){
        return ResponseEntity.ok(updateUserStatusPort.updateStatus(id, status));
    }

}




