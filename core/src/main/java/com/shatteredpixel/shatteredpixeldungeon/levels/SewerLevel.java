package com.shatteredpixel.shatteredpixeldungeon.levels;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Ghost;
import com.shatteredpixel.shatteredpixeldungeon.items.Amulet;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Shovel;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.SewerPainter;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.WornDartTrap;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.watabou.utils.Random;

public class SewerLevel extends RegularLevel {

	// Музыкальные треки для канализации
	public static final String[] SEWER_TRACK_LIST = new String[]{
		Assets.Music.SEWERS_1, Assets.Music.SEWERS_2, Assets.Music.SEWERS_3
	};
	public static final float[] SEWER_TRACK_CHANCES = new float[]{1f, 1f, 1f};

	{
		color1 = 0x48763c;
		color2 = 0x59994a;
	}

	@Override
	public String tilesTex() {
		return Assets.Environment.TILES_SEWERS;
	}

	@Override
	public String waterTex() {
		return Assets.Environment.WATER_SEWERS;
	}

	@Override
	protected Painter painter() {
		return new SewerPainter();
	}

	@Override
	protected void createMobs() {
		// 1. Спавним квестового призрака
		Ghost.Quest.spawn( this, roomExit );
		
		// 2. Генерируем стандартных врагов (крыс, гноллов)
		super.createMobs();

		// 3. Твоя лопата (спавним прямо под героем на 1 этаже)
		if (Dungeon.depth == 1 && Dungeon.hero != null) {
			Dungeon.level.drop(new Shovel(), Dungeon.hero.pos).sprite.drop();
		}
	}

	@Override
	protected void createItems() {
		// Этот вызов ГЕНЕРИРУЕТ СУНДУКИ, золото и расходники на полу
		super.createItems();
	}

	@Override
	protected int standardRooms(boolean forceMax) {
		return 5 + (forceMax ? 1 : Random.Int(2));
	}

	@Override
	protected Class<?>[] trapClasses() {
		return new Class<?>[]{ WornDartTrap.class };
	}

	@Override
	protected float[] trapChances() {
		return new float[]{ 1f };
	}

	@Override
	public String tileName( int tile ) {
		if (tile == Terrain.WATER) return Messages.get(SewerLevel.class, "water_name");
		return super.tileName( tile );
	}
}
