package com.fleetms.settings.services;

import com.fleetms.settings.model.State;
import com.fleetms.settings.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    //Get All the Countries Method
    public List<State> findAll()
    {
        return stateRepository.findAll();
    }

    public void save(State state)
    {
        stateRepository.save(state);
    }

    public void delete(Integer id)
    {
        stateRepository.delete(stateRepository.getById(id));
    }

    public State findById(Integer id) {
        return stateRepository.findById(id).orElse(null);
    }

    public List<State> getByKeyword(String keyword)
    {
        return stateRepository.findByKeyword(keyword);
    }

    public Page<State> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(field).ascending() : Sort.by(field).descending();

        Pageable pageable = PageRequest.of(pageNumber - 1, 2, sort);

        return stateRepository.findAll(pageable);
    }

    public Page<State> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber -1, 2);
        return stateRepository.findAll(pageable);
    }

}
