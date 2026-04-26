package com.microservices.userservice.user.infrastructure.adapter.out.persistence.repository;

import com.microservices.userservice.user.infrastructure.adapter.out.persistence.entity.UsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UsersEntity, Long> {

    Page<UsersEntity> findByUserNameContainingIgnoreCase(String fullName, Pageable pageable);
    Optional<UsersEntity> findByUserName(String username);
    boolean existsByUserName(String userName);
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END " +
            "FROM UsersEntity u WHERE u.userName = :username AND u.idUser != :id")
    boolean existsByUsernameExcludingId(@Param("username") String userName,
                                        @Param("id") Long id);



}
