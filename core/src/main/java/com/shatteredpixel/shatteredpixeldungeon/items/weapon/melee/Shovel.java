package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

// Мы импортируем основной класс оружия, чтобы игра поняла, от кого мы наследуемся
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.MeleeWeapon;

public class Shovel extends MeleeWeapon {

    public Shovel() {
        // В новых версиях скобки должны быть пустые!
        super();

        // Мы задаем параметры здесь, а не в скобках
        tier = 4;
        image = 4; // Пока временная картинка
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
        return "Раньше это оружие использовалось для закапывания могил, но сейчас его можно просто найти на этажах.";
    }
}
