package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Shird;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;

public class ShirdSprite extends MobSprite {

    @Override
    public void link(Mob mob) {
        super.link(mob);
        // По умолчанию ставим спящую текстуру
        texture = Assets.SHIRD_SLEEP; 
    }

    @Override
    public void update() {
        super.update();
        if (ch == null) return;

        // Логика смены картинок по стадиям ХП
        if (ch.HP <= 0) {
             // Текстура смерти (можно использовать idle)
        } else if (((Shird)ch).isCasting) {
            texture = Assets.SHIRD_CAST; // Стадия призыва
        } else if (ch.state == ch.WANDERING || ch.state == ch.HUNTING) {
            texture = Assets.SHIRD_IDLE; // Проснулся и стоит/ходит
        } else {
            texture = Assets.SHIRD_SLEEP; // Спит
        }
    }
    }
