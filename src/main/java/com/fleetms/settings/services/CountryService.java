package com.fleetms.settings.services;

import com.fleetms.settings.repositories.CountryRepository;
import com.fleetms.settings.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryrepository;

    //Get All the Countries Method
    public List<Country> getAll()
    {
        return countryrepository.findAll();
    }

    public void save(Country country)
    {
        countryrepository.save(country);
    }

    public void delete(Integer id)
    {
        countryrepository.delete(countryrepository.getById(id));
    }

    public Country getBYId(Integer id) {
        return countryrepository.findById(id).orElse(null);
    }

    public List<Country> getByKeyword(String keyword)
    {
        return countryrepository.findByKeyword(keyword);
    }

    public Page<Country> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(field).ascending() : Sort.by(field).descending();

        Pageable pageable = PageRequest.of(pageNumber - 1, 2, sort);

        return countryrepository.findAll(pageable);
    }

    public Page<Country> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber -1, 2);
        return countryrepository.findAll(pageable);
    }

}
