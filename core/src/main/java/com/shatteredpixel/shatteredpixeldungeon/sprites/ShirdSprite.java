package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;

public class ShirdSprite extends MobSprite {

    public ShirdSprite() {
        super();
        // Используем IDLE как основную текстуру
        texture(Assets.SHIRD_IDLE);
    }

    @Override
    public void update() {
        super.update();

        // Проверяем, что ch (персонаж) это действительно Mob
        if (ch instanceof Mob) {
            Mob m = (Mob) ch;
            // Если спит — одна текстура, если нет — другая
            if (m.state == Mob.SLEEPING) {
                texture(Assets.SHIRD_SLEEP);
            } else {
                texture(Assets.SHIRD_IDLE);
            }
        }
    }
}
