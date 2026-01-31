package com.shatteredpixel.shatteredpixeldungeon.items.weapons;

import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class Shovel extends MeleeWeapon {
    {
        name = "Лопата";
        // Указываем индекс иконки (выбери свободный в твоем спрайт-листе)
        image = 15; 
    }

    public Shovel() {
        // Урон: минимум 5, максимум 12
        initValues(5, 12, 1.0f, 1.0f);
    }

    @Override
    public String desc() {
        return "Старая садовая лопата. Не слишком острое, но тяжелое орудие.";
    }
}
