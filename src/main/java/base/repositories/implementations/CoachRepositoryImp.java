package base.repositories.implementations;

import base.entities.Coach;

public class CoachRepositoryImp extends BaseRepositoryImp<Coach, Long> {
    public CoachRepositoryImp() {
        super(Coach.class);
    }
}
