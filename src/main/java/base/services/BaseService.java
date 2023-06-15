package base.services;

import base.repositories.implementations.BaseRepositoryImp;

public class BaseService<Repo extends BaseRepositoryImp> {
    private Repo repository;

    public BaseService(Repo repo){
        repository = repo;
    }
}
