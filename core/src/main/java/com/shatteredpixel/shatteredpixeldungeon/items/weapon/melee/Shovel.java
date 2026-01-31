package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class Shovel extends MeleeWeapon {

	{
		// Используем иконку лопаты (она обычно есть в спрайтшите)
		image = ItemSpriteSheet.SHOVEL; 
		hitSound = Assets.Sounds.HIT_BLUNT; // Тупой звук удара (как лопатой)
		hitSoundPitch = 0.9f;

		tier = 3; // Сделаем её 3-го тира
	}

	@Override
	public int max(int lvl) {
		// Формула урона как у оружия 3 тира: 16 базовый + скалирование от уровня
		return 4*(tier+1) + lvl*(tier+1);
	}

	@Override
	public int proc(Char attacker, Char defender, int damage) {
		// 10% шанс оглушить (парализовать) врага на 2 хода при ударе
		if (Math.random() < 0.10) {
			Paralysis.affect(defender, 2f);
		}
		return super.proc(attacker, defender, damage);
	}

	// Способность дуэлянта (как у булавы/тяжелого оружия)
	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		// Лопата может "оглушать" врага (используем логику Mace)
		Mace.crushingBlow(hero, target, this);
	}

}
