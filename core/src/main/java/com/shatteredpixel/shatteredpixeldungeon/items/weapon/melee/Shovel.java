package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet; // Проверь этот путь, если будет ошибка
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;

public class Shovel extends MeleeWeapon {

    {
        // В некоторых версиях SPD иконки лежат в Assets.ItemSprite
        image = 12; // Числовой индекс — самый надежный способ (12 обычно меч)
        tier = 3;
    }

    @Override
    public String name() {
        return "Лопата";
    }

    public static void giveToHero() {
        if (Dungeon.hero != null) {
            Shovel shovel = new Shovel();
            // В SPD вместо inventory() чаще всего используется belongings
            if (!shovel.collect(Dungeon.hero.belongings)) {
                Dungeon.level.drop(shovel, Dungeon.hero.pos).sprite.drop();
            }
        }
    }
}
