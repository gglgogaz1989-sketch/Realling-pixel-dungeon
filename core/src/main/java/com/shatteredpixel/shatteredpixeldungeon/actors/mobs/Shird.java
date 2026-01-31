package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ShirdSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Shovel;

public class Shird extends Mob {

    {
        name = "Ширд";
        spriteClass = ShirdSprite.class;

        hp = maxHP = 70;
        defenseSkill = 12;
        baseSpeed = 1.2f;

        state = HUNTING; 
    }

    @Override
    public int damageRoll() {
        return 10 + (int)(Math.random() * 9); 
    }

    @Override
    public int attackSkill(Char target) {
        return 15;
    }

    @Override
    public void notice() {
        super.notice();
        yell("Я заберу твою душу этой лопатой!");
    }

    @Override
    public boolean attack(Char enemy) {
        if (sprite != null) sprite.attack(enemy.pos);
        return super.attack(enemy);
    }

    @Override
    public void die(Object cause) {
        super.die(cause);
        Dungeon.level.drop(new Shovel(), pos).sprite.drop();
        GLog.w("Ширд повержен! Вы нашли его старую лопату.");
    }
}
