package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Shield;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.AmuletOfIndor;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ShirdSprite;
import com.watabou.utils.Random;

public class Shird extends Mob {

    private int summonCooldown = 0;
    private boolean shieldPhaseTriggered = false;

    {
        name = "Ширд";
        spriteClass = ShirdSprite.class;
        HP = HT = 1500;
        flying = true;
    }

    @Override
    protected boolean act() {
        if (HP <= 0) return super.act();

        // 1 СПОСОБКА: Просыпается на 1000 ХП
        if (HP <= 1000 && state == SLEEPING) {
            state = WANDERING;
            sprite.parent.add(new Shield()); // Визуальный эффект пробуждения
        }

        // 2 СПОСОБКА: Призыв врагов каждые 5 ходов (если ХП <= 750)
        if (HP <= 750 && HP > 100) {
            if (summonCooldown-- <= 0) {
                spawnMinion();
                summonCooldown = 5;
                ((ShirdSprite)sprite).playCast(); // Анимация призыва
            }
        }

        return super.act();
    }

    @Override
    public int damageRoll() {
        // 3 СПОСОБКА: Лазер (10-30 урона) когда ХП < 500
        if (HP <= 500 && HP > 100) {
            return Random.Int(10, 30);
        }
        // 5 ФАЗА: Почти смерть (урон 5-35)
        return Random.Int(5, 35);
    }

    @Override
    public int defenseProc(Char attacker, int damage) {
        // 4 СПОСОБКА: ЩИТ ЯРОСТИ (на 100 ХП)
        if (HP <= 100 && !shieldPhaseTriggered) {
            shieldPhaseTriggered = true;
            say("Ярость поглотит тебя!");
            // Здесь можно добавить призыв редких врагов
            return 0; 
        }

        // Пока активен щит, босса нельзя ударить (если ты так решишь добавить проверку миньонов)
        return super.defenseProc(attacker, damage);
    }

    private void spawnMinion() {
        // Код для спавна случайного моба рядом с боссом
        Mob minion = Mob.make(Dungeon.depth); 
        minion.pos = Dungeon.level.randomRespawnCell();
        if (minion.pos != -1) {
            GameScene.add(minion);
        }
    }

    @Override
    public void die(Object cause) {
        say("Нет, нет, нет зачем ты меня убил? Я, я хотел чтоб никто не забрал мой амулет...");
        drop(new AmuletOfIndor(), pos);
        super.die(cause);
    }
          }
