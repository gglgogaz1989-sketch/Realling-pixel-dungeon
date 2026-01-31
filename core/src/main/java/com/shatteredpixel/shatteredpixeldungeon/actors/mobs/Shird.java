package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ShirdSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Shovel;

// Обязательно "extends Mob", чтобы появились hp и name
public class Shird extends Mob {

    {
        name = "Ширд";
        spriteClass = ShirdSprite.class;

        hp = maxHP = 1500; 
        defenseSkill = 25;
        baseSpeed = 1.0f;

        state = HUNTING; 
    }

    @Override
    public int damageRoll() {
        return 25 + (int)(Math.random() * 15); 
    }

    @Override
    public int attackSkill(Char target) {
        return 30;
    }

    // Убираем @Override attack, так как в Char он final. 
    // Вместо этого используем стандартную логику моба.
    
    @Override
    public void notice() {
        super.notice();
        yell("Твой путь заканчивается здесь!");
    }

    @Override
    public void die(Object cause) {
        super.die(cause);
        Dungeon.level.drop(new Shovel(), pos).sprite.drop();
        GLog.w("Невероятно... Ширд пал. Вы получили его легендарную лопату!");
    }
}
