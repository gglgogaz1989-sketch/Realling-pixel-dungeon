package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;

public class ShirdSprite extends MobSprite {

	public ShirdSprite() {
		super();
		texture(Assets.Sprites.SHIRD_IDLE); 
	}

	@Override
	public void update() {
		super.update();
		if (ch instanceof Mob) {
			Mob m = (Mob) ch;
			// Обращаемся к SLEEPING через объект m, а не через класс Mob
			if (m.state == m.SLEEPING) {
				texture(Assets.Sprites.SHIRD_SLEEP);
			} else {
				texture(Assets.Sprites.SHIRD_IDLE);
			}
		}
	}
}
