package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ShirdSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Shovel;
import com.shatteredpixel.shatteredpixeldungeon.items.Amulet;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Greatshield;
import com.shatteredpixel.shatteredpixeldungeon.ui.BossHealthBar;

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
    public int damage(int dmg, Object src) {
        int realDamage = super.damage(dmg, src);
        BossHealthBar.assignBoss(this); 
        return realDamage;
    }

    @Override
    public void notice() {
        super.notice();
        yell("Твой путь заканчивается здесь!");
        BossHealthBar.assignBoss(this);
    }

    @Override
    public void die(Object cause) {
        // Выпадение Лопаты, Амулета и Большого щита
        Dungeon.level.drop(new Shovel(), pos).sprite.drop();
        Dungeon.level.drop(new Amulet(), pos).sprite.drop();
        Dungeon.level.drop(new Greatshield(), pos).sprite.drop();
        
        GLog.w("Невероятно... Ширд пал. Вы забрали Амулет!");
        super.die(cause);
    }
}
