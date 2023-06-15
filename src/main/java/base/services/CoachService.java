package base.services;

import base.repositories.implementations.CoachRepositoryImp;

public class CoachService extends BaseService<CoachRepositoryImp> {

    private static final CoachRepositoryImp CoachRepositoryImp = new CoachRepositoryImp();

    public CoachService() {
        super(CoachRepositoryImp);
    }


}
