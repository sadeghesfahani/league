package base.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Setter
@Getter
public class Game extends BaseEntity<Long> {

    @OneToMany(mappedBy = "team")
    private List<Team> teams = new ArrayList<>();

    @OneToMany(mappedBy = "goal")
    private List<Goal> goals = new ArrayList<>();

    @OneToOne(mappedBy = "stadium")
    private Stadium stadium;

    private Date date;

    public Team getTheWinnerTeam() {
        HashMap<Team, Integer> results = new HashMap<>();
        for (Goal goal : goals) {
            if (goal.getFrom().equals(teams.get(0))) {
                if (results.containsKey(teams.get(0))) {
                    results.put(teams.get(0), results.get(teams.get(0)) + 1);
                } else {
                    results.put(teams.get(0), 1);
                }
            }
            if (goal.getFrom().equals(teams.get(1))) {
                if (results.containsKey(teams.get(1))) {
                    results.put(teams.get(1), results.get(teams.get(1)) + 1);
                } else {
                    results.put(teams.get(1), 1);
                }
            }
        }
        if (results.get(teams.get(0)) > results.get(teams.get(1))) {
            return teams.get(0);
        } else if(results.get(teams.get(0)) < results.get(teams.get(1))){
            return teams.get(1);
        }else{
            return null;
        }
    }

    public boolean isDerby(){
        return teams.get(0).getCity().equals(teams.get(1).getCity());
    }
}
