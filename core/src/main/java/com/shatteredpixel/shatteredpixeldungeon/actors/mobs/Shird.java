package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.ai.MeleeAI;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ShirdSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.weapons.Shovel;

public class Shird extends Mob {

    {
        name = "Ширд";
        spriteClass = ShirdSprite.class;

        hp = maxHP = 60;
        defenseSkill = 10;
        baseSpeed = 1.2f;

        // Настройка ИИ
        AI = new MeleeAI();
    }

    @Override
    public int damageRoll() {
        return 8 + (int)(Math.random() * 7); // Урон 8-15
    }

    @Override
    public int attackSkill(Char target) {
        return 14;
    }

    @Override
    public void notice() {
        super.notice();
        // Босс кричит при встрече
        yell("Кто посмел потревожить мои владения?!");
    }

    @Override
    public void die(Object cause) {
        super.die(cause);
        // При смерти босса выпадает твоя лопата
        Dungeon.level.drop(new Shovel(), pos).sprite.drop();
        GLog.w("Ширд повержен! Лопата выпала на землю.");
    }

    @Override
    public String description() {
        return "Древний страж канализации. Он выглядит очень злым и сжимает в руках старую лопату.";
    }
}
