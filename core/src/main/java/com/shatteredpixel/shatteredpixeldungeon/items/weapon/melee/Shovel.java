package com.shatteredpixel.shatteredpixeldungeon.items.weapons;

import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class Shovel extends MeleeWeapon {

    public Shovel() {
        // Tier 4, скорость 1, точность 1
        super( 4, 1f, 1f );
        // Пока используем временную иконку (например, 4 - это меч), 
        // чтобы игра не вылетала до обновления items.png
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
        return "Раньше это оружие использовалось для закапывания могил, " +
               "но сейчас его просто можно найти на этажах.";
    }
}
