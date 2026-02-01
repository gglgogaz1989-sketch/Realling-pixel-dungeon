package com.shatteredpixel.shatteredpixeldungeon.levels;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.GamesInProgress;
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
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.standard.ObsidianRoom;
import com.watabou.noosa.Game;
import com.watabou.noosa.Group;
import com.watabou.noosa.audio.Music;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.Callback;
import com.watabou.utils.ColorMath;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class SewerLevel extends RegularLevel {

	{
		color1 = 0x48763c;
		color2 = 0x59994a;
	}

	public static final String[] SEWER_TRACK_LIST
			= new String[]{Assets.Music.SEWERS_1, Assets.Music.SEWERS_2, Assets.Music.SEWERS_3};
	public static final float[] SEWER_TRACK_CHANCES = new float[]{1f, 1f, 1f};

	@Override
	public void create() {
		super.create();
		// Добавляем твою комнату строго на 1-й этаж
		if (Dungeon.depth == 1 && rooms != null) {
			rooms.add(new ObsidianRoom());
		}
	}

	@Override
	protected int specialRooms(boolean forceMax) {
		int n = super.specialRooms(forceMax);
		// Резервируем место под ObsidianRoom на глубине 1
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
		if (Ghost.Quest.active() || Statistics.amuletObtained){
			if (Statistics.amuletObtained && Dungeon.depth == 1){
				Music.INSTANCE.play(Assets.Music.THEME_FINALE, true);
			} else {
				Music.INSTANCE.play(Assets.Music.SEWERS_TENSE, true);
			}
		} else {
			Music.INSTANCE.playTracks(SEWER_TRACK_LIST, SEWER_TRACK_CHANCES, false);
		}
	}

	@Override public String tilesTex() { return Assets.Environment.TILES_SEWERS; }
	@Override public String waterTex() { return Assets.Environment.WATER_SEWERS; }

	@Override
	protected Class<?>[] trapClasses() {
		return new Class<?>[]{ ChillingTrap.class, ShockingTrap.class, ToxicTrap.class, WornDartTrap.class };
	}

	@Override
	protected float[] trapChances() {
		return new float[]{1, 1, 1, 1};
	}

	@Override
	protected void createMobs() {
		Ghost.Quest.spawn( this, roomExit );
		super.createMobs();
	}

	@Override
	public boolean activateTransition(Hero hero, LevelTransition transition) {
		if (transition.type == LevelTransition.Type.SURFACE){
			if (hero.belongings.getItem( Amulet.class ) == null) {
				Game.runOnRenderThread(new Callback() {
					@Override public void call() { GameScene.show( new WndMessage( Messages.get(hero, "leave") ) ); }
				});
				return false;
			} else {
				Statistics.ascended = true;
				Game.switchScene(SurfaceScene.class, new Game.SceneChangeCallback() {
					@Override public void beforeCreate() {}
					@Override public void afterCreate() {
						Badges.validateHappyEnd();
						Dungeon.win( Amulet.class );
						Dungeon.deleteGame( GamesInProgress.curSlot, true );
						Badges.saveGlobal();
					}
				});
				return true;
			}
		} else return super.activateTransition(hero, transition);
	}

	@Override
	public Group addVisuals() {
		super.addVisuals();
		for (int i=0; i < length(); i++) {
			if (map[i] == Terrain.WALL_DECO) visuals.add( new Sink( i ) );
		}
		return visuals;
	}

	@Override
	public void buildFlagMaps() {
		super.buildFlagMaps();
		for (int i=0; i < length(); i++) {
			if (map[i] == Terrain.REGION_DECO || map[i] == Terrain.REGION_DECO_ALT) flamable[i] = true;
		}
	}

	@Override
	public void destroy(int pos) {
		int terr = map[pos];
		if (terr == Terrain.REGION_DECO || terr == Terrain.REGION_DECO_ALT){
			set(pos, terr == Terrain.REGION_DECO ? Terrain.WATER : Terrain.EMPTY_SP);
			Splash.at(pos, 0xFF507B5D, 10);
		}
		super.destroy(pos);
	}

	@Override
	public String tileName( int tile ) {
		switch (tile) {
			case Terrain.WATER: return Messages.get(SewerLevel.class, "water_name");
			case Terrain.REGION_DECO:
			case Terrain.REGION_DECO_ALT: return Messages.get(SewerLevel.class, "region_deco_name");
			default: return super.tileName( tile );
		}
	}

	@Override
	public String tileDesc(int tile) {
		switch (tile) {
			case Terrain.EMPTY_DECO: return Messages.get(SewerLevel.class, "empty_deco_desc");
			case Terrain.BOOKSHELF: return Messages.get(SewerLevel.class, "bookshelf_desc");
			case Terrain.REGION_DECO:
			case Terrain.REGION_DECO_ALT: return Messages.get(SewerLevel.class, "region_deco_desc");
			default: return super.tileDesc( tile );
		}
	}

	private static class Sink extends Emitter {
		private int pos;
		private float rippleDelay = 0;
		// Используем анонимный класс вместо лямбды (->) для совместимости
		private static final Emitter.Factory factory = new Emitter.Factory() {
			@Override
			public void emit(Emitter emitter, int index, float x, float y) {
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
