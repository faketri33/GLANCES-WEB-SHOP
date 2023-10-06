package com.faketri.market.service;

import com.faketri.market.entity.Characteristics;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import com.faketri.market.repository.CharacteristicsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacteristicsService {
    @Autowired
    private CharacteristicsDao characteristicsDao;

    public List<Characteristics> findAll(){
        return characteristicsDao.findAll();
    }

    public Characteristics findById(Long id){
        return characteristicsDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Characteristics with id - " + id + " not found")
        );
    }

    public void save(Characteristics characteristics){
        characteristicsDao.save(characteristics);
    }
}
