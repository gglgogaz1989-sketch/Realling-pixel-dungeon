package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ShirdSprite;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Shovel;
import com.shatteredpixel.shatteredpixeldungeon.items.Amulet;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Greatshield;
import com.shatteredpixel.shatteredpixeldungeon.ui.BossHealthBar;

public class Shird extends Mob {

	{
		spriteClass = ShirdSprite.class;
		
		// Настройки здоровья как у Rat, но для босса
		HP = HT = 1500;
		defenseSkill = 30;

		state = HUNTING;
	}

	@Override
	public String name() {
		return "Ширд";
	}

	@Override
	public int damageRoll() {
		return 35;
	}

	@Override
	public int attackSkill(Char target) {
		return 30;
	}

	@Override
	public void damage(int dmg, Object src) {
		super.damage(dmg, src);
		// Показываем полоску босса при получении урона
		BossHealthBar.assignBoss(this);
	}

	@Override
	public void die(Object cause) {
		// Выпадение предметов
		Dungeon.level.drop(new Shovel(), pos).sprite.drop();
		Dungeon.level.drop(new Amulet(), pos).sprite.drop();
		
		// Большой щит (Greatshield)
		try {
			Dungeon.level.drop(new Greatshield(), pos).sprite.drop();
		} catch (Exception e) { /* на случай если щит называется иначе */ }
		
		super.die(cause);
	}
}
