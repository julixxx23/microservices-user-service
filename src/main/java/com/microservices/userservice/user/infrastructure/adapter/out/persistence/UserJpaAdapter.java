package com.microservices.userservice.user.infrastructure.adapter.out.persistence;

import com.microservices.userservice.shared.domain.model.PageDomain;
import com.microservices.userservice.user.domain.model.Users;
import com.microservices.userservice.user.domain.port.out.UserRepositoryPort;
import com.microservices.userservice.user.infrastructure.adapter.out.persistence.entity.UsersEntity;
import com.microservices.userservice.user.infrastructure.adapter.out.persistence.mapper.UserPersistenceMapper;
import com.microservices.userservice.user.infrastructure.adapter.out.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;
    private final UserPersistenceMapper mapper;

    @Override
    public Users save(Users users){
        return mapper.toDomain(userJpaRepository.save(mapper.toEntity(users)));
    }

    @Override
    public Users update(Long id, Users users){
        return mapper.toDomain(userJpaRepository.save(mapper.toEntity(users)));
    }

    @Override
    public Users updateStatus(Long id , Boolean status){
        UsersEntity entity = userJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with"));

        entity.setIsActive(status);
        return mapper.toDomain(userJpaRepository.save(entity));
    }

    @Override
    public Optional<Users> findById(Long id){
        return userJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public PageDomain<Users> list(int page, int size){
        Page<UsersEntity> entityPage = userJpaRepository.findAll(PageRequest.of(page, size));
        Page<Users> pages = entityPage.map(mapper::toDomain);
        return  new PageDomain<>(
                pages.getContent(),
                pages.getNumber(),
                pages.getTotalPages(),
                pages.getTotalElements()
        );
    }

    @Override
    public PageDomain<Users> findByText(String text, int page, int size){
        Page<UsersEntity> entityPage = userJpaRepository
                .findByUserNameContainingIgnoreCase(text, PageRequest.of(page, size));
        Page<Users> pages = entityPage.map(mapper::toDomain);
        return new PageDomain<>(
                pages.getContent(),
                pages.getNumber(),
                pages.getTotalPages(),
                pages.getTotalElements()
        );
    }

    @Override
    public void delete(Long id){
        userJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByUsername(String username){
        return userJpaRepository.existsByUserName(username);
    }

    @Override
    public boolean existsByUsernameExcludingId(String username, Long id){
        return  userJpaRepository.existsByUsernameExcludingId(username, id);
    }
}
