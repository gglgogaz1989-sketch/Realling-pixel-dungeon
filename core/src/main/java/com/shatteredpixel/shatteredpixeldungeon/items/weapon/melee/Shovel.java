package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;

public class Shovel extends MeleeWeapon {

    public Shovel() {
        // Tier 4
        super( 4, 1f, 1f );
        // Временная иконка, пока не обновишь items.png
        image = 4; 
    }

    @Override
    public int min() { return 10; }

    @Override
    public int max() { return 20; }

    @Override
    public int typicalStrength() { return 16; }

    @Override
    public String name() { return "Лопата"; }

    @Override
    public String desc() {
        return "Раньше это оружие использовалось для закапывания могил, но сейчас его можно просто найти на этажах.";
    }
}
