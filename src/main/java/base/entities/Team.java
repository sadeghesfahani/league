package base.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@Getter
public class Team extends BaseEntity<Long> {
    private final String CITY_ID = "city_id";

    private String name;
    @ManyToOne
    @JoinColumn(name = CITY_ID, nullable = false)
    private City city;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players = new ArrayList<>();

    @OneToOne(mappedBy = "coach", cascade = CascadeType.ALL, orphanRemoval = true)
    private Coach coach;


    public Coach getCoach() {
        return coach;
    }
}
