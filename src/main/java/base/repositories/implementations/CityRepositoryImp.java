package base.repositories.implementations;

import base.entities.City;

public class CityRepositoryImp extends BaseRepositoryImp<City, Long> {
    public CityRepositoryImp() {
        super(City.class);
    }
}
