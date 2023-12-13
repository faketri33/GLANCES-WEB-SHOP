package com.faketri.market.repository.impl;

import com.faketri.market.entity.Characteristics;
import com.faketri.market.repository.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class CharacteristicsImpl implements Repository<Long, Characteristics> {
    @Override
    public Optional<Characteristics> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Characteristics> findAll() {
        return null;
    }

    @Override
    public Page<Characteristics> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Long save(Characteristics entity) {
        return null;
    }

    @Override
    public Boolean update(Characteristics entity) {
        return null;
    }

    @Override
    public Boolean delete(Characteristics entity) {
        return null;
    }

    @Override
    public int countAll() {
        return 0;
    }
}
