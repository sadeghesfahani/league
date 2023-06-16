package base.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("COACH")
@Setter
@Getter
public class Coach extends Human{

        @OneToOne
        @JoinColumn(name = "team_id")
        private Team team;
}
