package com.shatteredpixel.shatteredpixeldungeon.items.weapons;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.items.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.Random;

public class Shovel extends MeleeWeapon {
    {
        name = "Лопата";
    }

    public Shovel() {
        // Урон 5-12, скорость 1.0
        initValues(5, 12, 1.0f, 1.0f);
    }

    @Override
    public void proc(Char attacker, Char defender, int damage) {
        super.proc(attacker, defender, damage);

        // Шанс 5% (0.05) наложить паралич на 5 ходов
        if (Random.Float() < 0.05f) {
            // Накладываем эффект паралича
            Paralysis.affect(defender, 5f); 
        }
    }

    @Override
    public String desc() {
        return "Старая садовая лопата. Удар по голове может на время парализовать противника.";
    }
}
