package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class Shovel extends MeleeWeapon {

	{
		// Используем MACE, так как SHOVEL нет в твоем SpriteSheet
		image = ItemSpriteSheet.MACE; 
		hitSound = Assets.Sounds.HIT; 
		hitSoundPitch = 0.8f;
		tier = 3;
	}

	@Override
	public int max(int lvl) {
		return 4*(tier+1) + lvl*(tier+1);
	}

	@Override
	public int proc(Char attacker, Char defender, int damage) {
		if (Math.random() < 0.10) {
			// ИСПРАВЛЕНИЕ: правильный вызов баффа для новой версии
			Buff.affect(defender, Paralysis.class).duration(2f);
		}
		return super.proc(attacker, defender, damage);
	}

	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		// Обычный выпад, чтобы не искать методы в классе Mace
		hero.attack(target);
	}
}
