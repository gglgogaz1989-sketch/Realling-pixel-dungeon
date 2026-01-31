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

    // В новых версиях инициализацию лучше делать в конструкторе или через методы
    public Shird() {
        super();
        
        // Используем методы доступа, если переменные напрямую недоступны
        this.name = "Ширд"; 
        this.hp = this.maxHP = 1500;
        
        spriteClass = ShirdSprite.class;
        state = HUNTING;
    }

    @Override
    public int damageRoll() {
        return 35; 
    }

    @Override
    public int attackSkill(Char target) {
        return 30;
    }

    // ИСПРАВЛЕНИЕ: В твоем движке damage возвращает void!
    @Override
    public void damage(int dmg, Object src) {
        super.damage(dmg, src); // Просто вызываем, ничего не сохраняя в int
        BossHealthBar.assignBoss(this); 
    }

    @Override
    public void notice() {
        super.notice();
        yell("Твой путь заканчивается здесь!");
        BossHealthBar.assignBoss(this);
    }

    @Override
    public void die(Object cause) {
        // Выпадение предметов
        Dungeon.level.drop(new Shovel(), pos).sprite.drop();
        Dungeon.level.drop(new Amulet(), pos).sprite.drop();
        Dungeon.level.drop(new Greatshield(), pos).sprite.drop();
        
        GLog.w("Невероятно... Ширд пал. Вы забрали Амулет!");
        super.die(cause);
    }
}
