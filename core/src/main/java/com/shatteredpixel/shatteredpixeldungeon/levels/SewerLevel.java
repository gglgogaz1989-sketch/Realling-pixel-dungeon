package com.shatteredpixel.shatteredpixeldungeon.levels;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Ghost;
import com.shatteredpixel.shatteredpixeldungeon.effects.Ripple;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.shatteredpixel.shatteredpixeldungeon.items.Amulet;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.LevelTransition;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.SewerPainter;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.*;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.SurfaceScene;
import com.shatteredpixel.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndMessage;
// ИЗМЕНЕНО: Импорт теперь ведет в общую папку rooms
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.ObsidianRoom;
import com.watabou.noosa.Game;
import com.watabou.noosa.Group;
import com.watabou.noosa.audio.Music;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.Callback;
import com.watabou.utils.ColorMath;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

public class SewerLevel extends RegularLevel {

	{ color1 = 0x48763c; color2 = 0x59994a; }

	@Override
	public void create() {
		super.create();
		if (Dungeon.depth == 1 && rooms != null) {
			rooms.add(new ObsidianRoom());
		}
	}

	@Override
	protected int specialRooms(boolean forceMax) {
		int n = super.specialRooms(forceMax);
		return Dungeon.depth == 1 ? n + 1 : n;
	}

	@Override
	protected Painter painter() {
		return new SewerPainter()
				.setWater(feeling == Feeling.WATER ? 0.85f : 0.30f, 5)
				.setGrass(feeling == Feeling.GRASS ? 0.80f : 0.20f, 4)
				.setTraps(nTraps(), trapClasses(), trapChances());
	}

	@Override
	public void playLevelMusic(){
		if (Statistics.amuletObtained && Dungeon.depth == 1){
			Music.INSTANCE.play(Assets.Music.THEME_FINALE, true);
		} else {
			Music.INSTANCE.playTracks(new String[]{Assets.Music.SEWERS_1, Assets.Music.SEWERS_2, Assets.Music.SEWERS_3}, new float[]{1f, 1f, 1f}, false);
		}
	}

	@Override public String tilesTex() { return Assets.Environment.TILES_SEWERS; }
	@Override public String waterTex() { return Assets.Environment.WATER_SEWERS; }

	@Override
	protected Class<?>[] trapClasses() {
		return new Class<?>[]{ WornDartTrap.class, ToxicTrap.class, ShockingTrap.class, ChillingTrap.class };
	}

	@Override
	protected float[] trapChances() {
		return new float[]{1, 1, 1, 1};
	}

	@Override
	public Group addVisuals() {
		super.addVisuals();
		for (int i=0; i < length(); i++) {
			if (map[i] == Terrain.WALL_DECO) visuals.add( new Sink( i ) );
		}
		return visuals;
	}

	private static class Sink extends Emitter {
		private int pos;
		private float rippleDelay = 0;
		private static final Emitter.Factory factory = new Emitter.Factory() {
			@Override public void emit(Emitter emitter, int index, float x, float y) {
				WaterParticle p = (WaterParticle)emitter.recycle( WaterParticle.class );
				p.reset( x, y );
			}
		};
		public Sink( int pos ) {
			this.pos = pos;
			PointF p = DungeonTilemap.tileCenterToWorld( pos );
			pos( p.x - 2, p.y + 3, 4, 0 );
			pour( factory, 0.1f );
		}
		@Override
		public void update() {
			if (visible = (pos < Dungeon.level.heroFOV.length && Dungeon.level.heroFOV[pos])) {
				super.update();
				if (!isFrozen() && (rippleDelay -= Game.elapsed) <= 0) {
					Ripple ripple = GameScene.ripple( pos + Dungeon.level.width() );
					if (ripple != null) {
						ripple.y -= DungeonTilemap.SIZE / 2;
						rippleDelay = Random.Float(0.4f, 0.6f);
					}
				}
			}
		}
	}

	public static final class WaterParticle extends PixelParticle {
		public WaterParticle() {
			acc.y = 50; am = 0.5f;
			color( ColorMath.random( 0xb6ccc2, 0x3b6653 ) );
			size( 2 );
		}
		public void reset( float x, float y ) {
			revive(); this.x = x; this.y = y;
			speed.set( Random.Float( -2, +2 ), 0 );
			left = lifespan = 0.4f;
		}
	}
}
