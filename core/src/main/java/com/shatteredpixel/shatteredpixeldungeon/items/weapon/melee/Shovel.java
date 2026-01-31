package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.items.MeleeWeapon;

public class Shovel extends MeleeWeapon {

    public Shovel() {
        name = "Лопата";
        // Если initValues выдает ошибку, проверь параметры в MeleeWeapon.java
        initValues(5, 12, 1.0f, 1.0f); 
    }

    @Override
    public void proc(Char attacker, Char defender, int damage) {
        super.proc(attacker, defender, damage);
        if (Math.random() < 0.05) {
            Paralysis.affect(defender, 5f);
        }
    }
}
