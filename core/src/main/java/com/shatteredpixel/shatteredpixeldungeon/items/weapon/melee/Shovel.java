package com.shatteredpixel.shatteredpixeldungeon.items.weapons;

// Этот импорт ОБЯЗАТЕЛЕН, чтобы работал урон (initValues)
import com.shatteredpixel.shatteredpixeldungeon.items.MeleeWeapon;

public class Shovel extends MeleeWeapon {
    {
        name = "Лопата";
    }

    public Shovel() {
        // Урон: от 5 до 12. Скорость: 1.0. Дистанция: 1.0.
        initValues(5, 12, 1.0f, 1.0f);
    }

    @Override
    public String desc() {
        return "Старая садовая лопата. Неплохое оружие для начала пути.";
    }
}
