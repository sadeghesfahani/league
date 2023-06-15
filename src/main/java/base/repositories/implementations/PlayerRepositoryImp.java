package base.repositories.implementations;

import base.entities.Player;

public class PlayerRepositoryImp extends BaseRepositoryImp<Player, Long> {

    public PlayerRepositoryImp() {
        super(Player.class);
    }
}
