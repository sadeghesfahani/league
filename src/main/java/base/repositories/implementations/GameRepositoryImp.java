package base.repositories.implementations;

import base.entities.Game;


public class GameRepositoryImp extends BaseRepositoryImp<Game, Long> {
    public GameRepositoryImp() {
        super(Game.class);
    }
}
