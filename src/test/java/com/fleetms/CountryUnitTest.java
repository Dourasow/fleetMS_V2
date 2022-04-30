package com.fleetms;

import com.fleetms.settings.model.Country;
import com.fleetms.settings.repositories.CountryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CountryUnitTest {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void findById()
    {
        Country country = countryRepository.findById(1).orElse(null);
        assertNull(country);
    }
    @Test
    public void findByIdee()
    {
        Country country = countryRepository.findById(1).orElse(null);
        assertNotNull(country);
    }

    @Test
    public void findByIdEmp()
    {
        Country country = countryRepository.findById(100).orElse(null);
        assertNull(country);
    }
}
