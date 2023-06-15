package base.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Stadium extends BaseEntity<Long> {
    private String name;

    @OneToOne(mappedBy = "id")
    private City city;
}
