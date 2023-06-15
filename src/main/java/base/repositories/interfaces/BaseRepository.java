package base.repositories.interfaces;


import java.io.Serializable;
import java.util.List;

public interface BaseRepository<Entity, IDType extends Serializable> {
    Entity findById(IDType id);

    List<Entity> findAll();

    Entity save(Entity entity);

    void delete(Entity entity);
}
