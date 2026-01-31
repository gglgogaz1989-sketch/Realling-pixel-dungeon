package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;

public class Shovel extends MeleeWeapon {

    public Shovel() {
        // Оставляем скобки пустыми! Это важно для твоей версии Weapon.java
        super();

        // Задаем параметры через переменные
        tier = 4;   // Тир 4 (определяет силу)
        image = 4;  // Временная картинка меча
    }

    @Override
    public int min() { return 10; }

    @Override
    public int max() { return 20; }

    @Override
    public String name() { return "Лопата"; }

    @Override
    public String desc() {
        return "Раньше это оружие использовалось для закапывания могил.";
    }
}
