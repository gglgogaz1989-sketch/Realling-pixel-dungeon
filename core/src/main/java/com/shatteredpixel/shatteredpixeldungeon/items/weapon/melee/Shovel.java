package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
// Импортируем всё из папки выше, так как MeleeWeapon лежит там
import com.shatteredpixel.shatteredpixeldungeon.items.*;

public class Shovel extends MeleeWeapon {

    public Shovel() {
        name = "Лопата";
        // Стандартные характеристики лопаты
        initValues(5, 12, 1.0f, 1.0f); 
    }

    @Override
    public void proc(Char attacker, Char defender, int damage) {
        super.proc(attacker, defender, damage);
        // 5% шанс паралича
        if (Math.random() < 0.05) {
            Paralysis.affect(defender, 5f);
        }
    }
}
