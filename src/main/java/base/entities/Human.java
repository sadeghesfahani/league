package base.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Setter
@Getter
public class Human extends BaseEntity<Long> {
    private String name;
    private String lastName;
    private double salary;


}
