package base.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Setter
@Getter
public class Goal extends BaseEntity<Long> {

    @OneToOne()
    private Player player;

    @OneToOne()
    private Game game;

    @OneToOne()
    private Team to;

    @OneToOne()
    private Team from;
}
