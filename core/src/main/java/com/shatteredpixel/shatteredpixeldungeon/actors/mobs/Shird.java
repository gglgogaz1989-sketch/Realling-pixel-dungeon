package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ShirdSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Shovel;

public class Shird extends Mob {

    {
        name = "Ширд";
        spriteClass = ShirdSprite.class;

        hp = maxHP = 60;
        defenseSkill = 10;
        baseSpeed = 1.2f;

        // Вместо MeleeAI используем стандартное состояние охоты
        state = HUNTING; 
    }

    @Override
    public int damageRoll() {
        return 8 + (int)(Math.random() * 7); 
    }

    @Override
    public int attackSkill(Char target) {
        return 14;
    }

    @Override
    public void notice() {
        super.notice();
        yell("Кто посмел потревожить мои владения?!");
    }

    @Override
    public void die(Object cause) {
        super.die(cause);
        // Выпадение лопаты при смерти
        Dungeon.level.drop(new Shovel(), pos).sprite.drop();
        GLog.w("Ширд повержен! Лопата выпала на землю.");
    }

    @Override
    public String description() {
        return "Древний страж канализации. Он выглядит очень злым.";
    }
}
