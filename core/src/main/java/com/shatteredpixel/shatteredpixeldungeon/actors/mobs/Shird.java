package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;

public class ShirdSprite extends MobSprite {

    public ShirdSprite() {
        super();
        // Используем переменную, которую ты добавил в Assets.java
        texture(Assets.SHIRD_IDLE); 
    }

    @Override
    public void update() {
        super.update();

        // Безопасная проверка состояния для смены текстур
        if (ch instanceof Mob) {
            Mob m = (Mob) ch;
            if (m.state == Mob.SLEEPING) {
                texture(Assets.SHIRD_SLEEP);
            } else {
                texture(Assets.SHIRD_IDLE);
            }
        }
    }
}
