package com.faketri.market.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface Repository<T, E> {

    public Optional<E> findById(T id);
    public List<E> findAll();
    public Page<E> findAll(Pageable pageable);
    public Long save(E entity);
    public Boolean update(E entity);
    public Boolean delete(E entity);
    public int countAll();
}
