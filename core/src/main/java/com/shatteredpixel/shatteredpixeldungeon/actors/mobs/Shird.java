package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ShirdSprite;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Shovel;
import com.shatteredpixel.shatteredpixeldungeon.items.Amulet;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Greatshield;
import com.shatteredpixel.shatteredpixeldungeon.ui.BossHealthBar;

public class Shird extends Mob {

	public Shird() {
		super();
		// В этой версии мы присваиваем значения напрямую полям
		this.name = "Ширд";
		this.hp = this.maxHP = 1500;
		
		spriteClass = ShirdSprite.class;
		state = HUNTING;
	}

	@Override
	public int damageRoll() { return 35; }

	@Override
	public int attackSkill(Char target) { return 30; }

	@Override
	public void damage(int dmg, Object src) {
		super.damage(dmg, src);
		BossHealthBar.assignBoss(this);
	}

	@Override
	public void die(Object cause) {
		Dungeon.level.drop(new Shovel(), pos).sprite.drop();
		Dungeon.level.drop(new Amulet(), pos).sprite.drop();
		Dungeon.level.drop(new Greatshield(), pos).sprite.drop();
		super.die(cause);
	}
}
