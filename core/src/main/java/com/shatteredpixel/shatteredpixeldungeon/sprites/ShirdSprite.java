package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;

public class ShirdSprite extends MobSprite {

	public ShirdSprite() {
		super();
		// Если SHIRD_IDLE выдает ошибку, временно поставь Assets.Sprites.RAT
		texture(Assets.Sprites.SHIRD_IDLE); 
	}

	@Override
	public void update() {
		super.update();
		if (ch instanceof Mob) {
			Mob m = (Mob) ch;
			// Используем методы для проверки состояния, если state закрыт
			if (m.state == Mob.SLEEPING) {
				texture(Assets.Sprites.SHIRD_SLEEP);
			} else {
				texture(Assets.Sprites.SHIRD_IDLE);
			}
		}
	}
}
