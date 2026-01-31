package com.shatteredpixel.shatteredpixeldungeon.levels;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Shird;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Shovel;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.SewerPainter;

public class SewerLevel extends RegularLevel {

	{
		color1 = 0x48763c;
		color2 = 0x59994a;
	}

	@Override
	protected void createMobs() {
		super.createMobs();

		// Спавним лопату прямо под героем на 1 этаже
		if (Dungeon.depth == 1) {
			Dungeon.level.drop(new Shovel(), Dungeon.hero.pos).sprite.drop();
			
			// Если всё же нужен босс, раскомментируйте строки ниже:
			// Shird boss = new Shird();
			// boss.pos = randomRespawnCell(boss);
			// if (boss.pos != -1) Dungeon.level.spawnMob(boss);
		}
	}

	@Override
	protected Painter painter() {
		return new SewerPainter();
	}

	@Override
	public String tilesTex() {
		return com.shatteredpixel.shatteredpixeldungeon.Assets.Environment.TILES_SEWERS;
	}

	@Override
	public String waterTex() {
		return com.shatteredpixel.shatteredpixeldungeon.Assets.Environment.WATER_SEWERS;
	}
}
