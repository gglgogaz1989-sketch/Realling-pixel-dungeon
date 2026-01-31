package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ShirdSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Shovel;
import com.shatteredpixel.shatteredpixeldungeon.items.Amulet;

public class Shird extends Mob {

    {
        name = "Ширд";
        spriteClass = ShirdSprite.class;

        hp = maxHP = 1500; 
        defenseSkill = 30;
        baseSpeed = 1f;

        state = HUNTING;
    }

    @Override
    public int damageRoll() {
        return 30 + (int)(Math.random() * 20);
    }

    @Override
    public int attackSkill(Char target) {
        return 35;
    }

    @Override
    public void notice() {
        super.notice();
        yell("Конец близок... Амулет останется здесь!");
    }

    @Override
    public void die(Object cause) {
        super.die(cause);
        
        // 1. Выпадает лопата
        Dungeon.level.drop(new Shovel(), pos).sprite.drop();
        
        // 2. Выпадает Амулет
        Amulet amulet = new Amulet();
        amulet.name = "Сердце Реалинга"; // Твоё название амулета
        Dungeon.level.drop(amulet, pos).sprite.drop();
        
        GLog.w("Ширд повержен! Вы забрали Сердце Реалинга.");
    }
}
