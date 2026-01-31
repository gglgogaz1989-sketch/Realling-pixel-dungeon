package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.utils.Random;

// Пробуем импортировать всё из папок, где может быть MeleeWeapon
import com.shatteredpixel.shatteredpixeldungeon.items.*;
import com.shatteredpixel.shatteredpixeldungeon.items.weapons.*;

public class Shovel extends MeleeWeapon {

    public Shovel() {
        name = "Лопата";
        // Если на эту строку будет ругаться, значит в твоей версии 
        // метод называется по-другому, но пока оставим так:
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
