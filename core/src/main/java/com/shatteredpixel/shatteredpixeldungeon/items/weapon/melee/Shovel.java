package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;

public class Shovel extends MeleeWeapon {

    {
        // Используем спрайт меча, так как он гарантированно есть в атласе
        image = ItemSpriteSheet.SWORD;
        tier = 3;
    }

    @Override
    public String name() {
        return "Лопата";
    }

    @Override
    public String desc() {
        return "Старая, но всё ещё крепкая лопата. Кажется, ею можно не только копать.";
    }

    // Метод для выдачи лопаты прямо в руки герою (самый безопасный способ без вылетов)
    public static void giveToHero() {
        if (Dungeon.hero != null) {
            Shovel shovel = new Shovel();
            if (!shovel.collect(Dungeon.hero.inventory())) {
                // Если инвентарь полон, бросаем под ноги
                Dungeon.level.drop(shovel, Dungeon.hero.pos).sprite.drop();
            }
        }
    }
}
