package com.faketri.market.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface Repository<T, E> {

    Optional<E> findById(T id);

    E findByFields(E entity);

    List<E> findAll();

    Page<E> findAll(Pageable pageable);

    E save(E entity);

    Boolean update(E entity);

    Boolean delete(E entity);

    int countAll();

}
