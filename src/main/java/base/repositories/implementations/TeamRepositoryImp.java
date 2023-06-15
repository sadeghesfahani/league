package base.repositories.implementations;
import base.entities.Team;



public class TeamRepositoryImp extends BaseRepositoryImp<Team, Long> {
    public TeamRepositoryImp() {
        super(Team.class);
    }

}
