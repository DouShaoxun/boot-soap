package cn.cruder.bootsoap.repository;

import com.baeldung.springsoap.gen.Country;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dousx
 * @date 2022-05-20 11:05
 */
@Component
public class CountryRepository {

    private static final Map<String, Country> countries = new HashMap<>();

    @PostConstruct
    public void initData() {
        // initialize countries map
        Country country = new Country();
        country.setName("Spain");
        country.setPopulation(100);
        country.setCapital("cap");
        country.setPopulation(22);
        countries.put(country.getName(), country);
    }

    public Country findCountry(String name) {
        return countries.get(name);
    }
}