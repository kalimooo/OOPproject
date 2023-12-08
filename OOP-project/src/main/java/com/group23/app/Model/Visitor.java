package com.group23.app.Model;

public interface Visitor {
    public void resolveLaserCollision(Laser laser);
    public void resolvePowerUpCollision(PowerUp powerUp);
    public void resolveCollectibleItemCollision(CollectibleItem collectibleItem);
    public void resolvePlayerCollision(Player player);

}
