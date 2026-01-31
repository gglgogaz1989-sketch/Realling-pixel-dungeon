package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

// Обязательный импорт, чтобы игра поняла, что это оружие
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.MeleeWeapon;

public class Shovel extends MeleeWeapon {

    public Shovel() {
        // Оставляем скобки пустыми (fix для новых версий игры)
        super();

        // Задаем параметры вручную
        tier = 4;
        image = 4; // Пока ставим картинку меча, чтобы не крашнулось
    }

    @Override
    public int min() {
        return 10;
    }

    @Override
    public int max() {
        return 20;
    }

    @Override
    public String name() {
        return "Лопата";
    }

    @Override
    public String desc() {
        return "Раньше это оружие использовалось для закапывания могил.";
    }
}
