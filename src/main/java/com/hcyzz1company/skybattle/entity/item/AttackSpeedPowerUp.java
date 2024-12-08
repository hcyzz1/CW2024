package com.hcyzz1company.skybattle.entity.item;

public class AttackSpeedPowerUp extends PowerUpItem {

    public AttackSpeedPowerUp(double initialXPos, double initialYPos) {
        super("speed.png", initialXPos, initialYPos, -5);  // 图片名称和速度设定
    }

    @Override
    public void activateEffect() {

    }

    @Override
    public void takeDamage() {
        this.destroy();
    }
}
