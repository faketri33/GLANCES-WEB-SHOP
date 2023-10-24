package com.faketri.market.repository.Contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RepositoryDao<T> {

    Optional<T> findById(Long id);
    List<T> findAll();
    Page<T> findAllByPage(Pageable pageable);
    void save(T entity);
    void update(T entity);
    void delete(T entity);
}
