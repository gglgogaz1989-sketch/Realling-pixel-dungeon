package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;

public class ShirdSprite extends MobSprite {

    @Override
    public void update() {
        super.update();
        if (ch == null) return;

        // Переключение стадий
        if (ch.HP <= 500 && ch.HP > 100) {
            texture = Assets.SHIRD_ATTACK; // Стадия лазера
        } else if (ch.HP <= 750) {
            texture = Assets.SHIRD_CAST;   // Стадия призыва
        } else if (ch.state != ch.SLEEPING) {
            texture = Assets.SHIRD_IDLE;   // Проснулся
        } else {
            texture = Assets.SHIRD_SLEEP;  // Спит
        }
    }
                }
