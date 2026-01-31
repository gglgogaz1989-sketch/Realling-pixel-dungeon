package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.items.MeleeWeapon; 
import com.shatteredpixel.shatteredpixeldungeon.utils.Random;

public class Shovel extends MeleeWeapon {
    {
        name = "Лопата";
    }

    public Shovel() {
        initValues(5, 12, 1.0f, 1.0f);
    }

    @Override
    public void proc(Char attacker, Char defender, int damage) {
        super.proc(attacker, defender, damage);
        if (Random.Float() < 0.05f) {
            Paralysis.affect(defender, 5f);
        }
    }
}
