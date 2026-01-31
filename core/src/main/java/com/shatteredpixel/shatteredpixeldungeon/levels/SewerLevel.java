package com.shatteredpixel.shatteredpixeldungeon.levels;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Shird;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Shovel;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.SewerPainter;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Ghost;

public class SewerLevel extends RegularLevel {

    // Музыка для SewerBossLevel и обычных уровней
    public static final String[] SEWER_TRACK_LIST
            = new String[]{Assets.Music.SEWERS_1, Assets.Music.SEWERS_2, Assets.Music.SEWERS_2,
            Assets.Music.SEWERS_1, Assets.Music.SEWERS_3, Assets.Music.SEWERS_3};
    public static final float[] SEWER_TRACK_CHANCES = new float[]{1f, 1f, 0.5f, 0.25f, 1f, 0.5f};

    {
        color1 = 0x48763c;
        color2 = 0x59994a;
    }

    // ТУТ ГЕНЕРИРУЮТСЯ ВРАГИ
    @Override
    protected void createMobs() {
        // Спавним призрака (квест)
        Ghost.Quest.spawn( this, roomExit );
        // super.createMobs() запускает генерацию обычных крыс и гноллов
        super.createMobs();

        // Твоя лопата прямо на входе
        if (Dungeon.depth == 1) {
            Dungeon.level.drop(new Shovel(), Dungeon.hero.pos).sprite.drop();
        }
    }

    // ТУТ ГЕНЕРИРУЮТСЯ ПРЕДМЕТЫ И СУНДУКИ
    @Override
    protected void createItems() {
        // Это вызывает стандартную генерацию предметов и сундуков из RegularLevel
        super.createItems();
    }

    @Override
    protected Painter painter() {
        // SewerPainter отвечает за расстановку дверей, травы и воды
        return new SewerPainter();
    }

    @Override
    public String tilesTex() {
        return Assets.Environment.TILES_SEWERS;
    }

    @Override
    public String waterTex() {
        return Assets.Environment.WATER_SEWERS;
    }
}
