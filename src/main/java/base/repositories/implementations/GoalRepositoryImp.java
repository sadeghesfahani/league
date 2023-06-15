package base.repositories.implementations;

import base.entities.Goal;

public class GoalRepositoryImp extends BaseRepositoryImp<Goal, Long> {
    public GoalRepositoryImp() {
        super(Goal.class);
    }
}
