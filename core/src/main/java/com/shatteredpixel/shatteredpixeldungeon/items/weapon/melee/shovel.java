package com.shatteredpixel.shatteredpixeldungeon.items.weapons;

import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;

/**
 * Лопата: Урон 10-20, Сила 16.
 * Появляется на 1-29 этажах (кроме боссов).
 */
public class Shovel extends MeleeWeapon {

    public Shovel() {
        // Устанавливаем Tier 4 (это примерно соответствует силе 16 и урону 10-20)
        super( 4, 1f, 1f );
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
    public int typicalStrength() {
        return 16;
    }

    @Override
    public String name() {
        return "Лопата";
    }

    @Override
    public String desc() {
        return "Раньше это оружие использовалось для закапывания могил, но сейчас его можно просто найти на этажах.";
    }
    
    // Пока что привяжем к стандартной иконке (например, железного меча), 
    // чтобы игра не вылетала, пока ты не настроишь свой items.png
    {
        image = 4; 
    }
}
