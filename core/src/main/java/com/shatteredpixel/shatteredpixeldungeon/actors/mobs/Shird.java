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
        // Скорость 1.2 делает его чуть быстрее обычных крыс
        baseSpeed = 1.2f;

        // Это заставляет его сразу нападать, как только он увидит игрока
        state = HUNTING; 
    }

    @Override
    public int damageRoll() {
        // Урон от 10 до 18
        return 10 + (int)(Math.random() * 9); 
    }

    @Override
    public int attackSkill(Char target) {
        // Точность атаки
        return 15;
    }

    @Override
    public void notice() {
        super.notice();
        // Босс кричит при обнаружении игрока
        yell("Я заберу твою душу этой лопатой!");
    }

    @Override
    public boolean attack(Char enemy) {
        // Запускаем анимацию удара в спрайте
        sprite.attack(enemy.pos);
        return super.attack(enemy);
    }

    @Override
    public void die(Object cause) {
        super.die(cause);
        // При смерти гарантированно выпадает твоя новая лопата
        Dungeon.level.drop(new Shovel(), pos).sprite.drop();
        GLog.w("Ширд повержен! Вы нашли его старую лопату.");
    }

    @Override
    public String description() {
        return "Древний смотритель, сошедший с ума. Он не выпустит тебя живым.";
    }
}
