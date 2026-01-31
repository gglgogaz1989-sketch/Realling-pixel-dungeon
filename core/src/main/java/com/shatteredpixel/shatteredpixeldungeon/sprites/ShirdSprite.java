package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class Shovel extends MeleeWeapon {

	{
		image = ItemSpriteSheet.MACE; 
		hitSound = Assets.Sounds.HIT; 
		tier = 3;
	}

	@Override
	public int max(int lvl) {
		return 20 + (lvl * 5);
	}

	@Override
	public int proc(Char attacker, Char defender, int damage) {
		if (Math.random() < 0.10) {
			// ИСПРАВЛЕНИЕ: в новых версиях используется .set() или конструктор
			Buff.affect(defender, Paralysis.class).set(2f); 
		}
		return super.proc(attacker, defender, damage);
	}
}
