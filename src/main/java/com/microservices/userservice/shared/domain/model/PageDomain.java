package com.microservices.userservice.shared.domain.model;

import java.util.List;

public record PageDomain<T>(
    List<T> content,
    int currentPage,
    int totalPages,
    long totalElements
){}
