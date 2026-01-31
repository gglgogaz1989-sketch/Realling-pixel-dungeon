package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
// Исправляем импорт: MeleeWeapon лежит в папке items!
import com.shatteredpixel.shatteredpixeldungeon.items.MeleeWeapon;

public class Shovel extends MeleeWeapon {

    public Shovel() {
        name = "Лопата";
        initValues(5, 12, 1.0f, 1.0f); 
    }

    @Override
    public void proc(Char attacker, Char defender, int damage) {
        super.proc(attacker, defender, damage);
        // Используем Math.random(), чтобы не зависеть от внешних классов Random
        if (Math.random() < 0.05) {
            Paralysis.affect(defender, 5f);
        }
    }
}
