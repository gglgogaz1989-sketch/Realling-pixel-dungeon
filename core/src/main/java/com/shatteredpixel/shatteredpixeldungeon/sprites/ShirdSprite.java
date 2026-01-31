package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;

public class ShirdSprite extends MobSprite {

    public ShirdSprite() {
        super();
        // Указываем полный путь к переменной внутри класса Assets
        texture(Assets.Sprites.SHIRD_IDLE); 
    }

    @Override
    public void update() {
        super.update();

        if (ch instanceof Mob) {
            Mob m = (Mob) ch;
            // Исправляем обращение к SLEEPING через экземпляр 'm'
            if (m.state == m.SLEEPING) {
                texture(Assets.Sprites.SHIRD_SLEEP);
            } else {
                texture(Assets.Sprites.SHIRD_IDLE);
            }
        }
    }
    
    @Override
    public void attack(int pos) {
        texture(Assets.Sprites.SHIRD_ATTACK);
        super.attack(pos);
    }
}
