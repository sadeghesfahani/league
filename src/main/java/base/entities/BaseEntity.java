package base.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class BaseEntity<IDType extends Serializable> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private IDType id;


}
