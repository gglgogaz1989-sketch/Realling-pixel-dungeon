package com.shatteredpixel.shatteredpixeldungeon.levels.rooms;

import com.shatteredpixel.shatteredpixeldungeon.items.keys.ObsidianKey;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.standard.StandardRoom;

public class ObsidianRoom extends StandardRoom {

    // Фиксированный размер 5x5 для обсидиановой комнаты
    @Override
    public int minWidth() { return 5; }
    @Override
    public int maxWidth() { return 5; }
    @Override
    public int minHeight() { return 5; }
    @Override
    public int maxHeight() { return 5; }

    @Override
    public void paint(Level level) {
        // 1. Рисуем стены и пол
        Painter.fill(level, this, Terrain.WALL, Terrain.EMPTY_SP);

        // 2. Ставим обсидиановый сундук в центр
        int center = level.pointToCell(center());
        level.set(center, Terrain.OBSIDIAN_CHEST);

        // 3. Выбираем случайную клетку для ключа (но не ту, где сундук)
        int keyPos;
        do {
            keyPos = level.pointToCell(random());
        } while (keyPos == center);

        // 4. Создаем и бросаем ключ на пол
        level.drop(new ObsidianKey(), keyPos).sprite.drop();

        // 5. Запираем все входы в комнату на замок
        for (Door door : connected.values()) {
            door.set(Door.Type.LOCKED);
        }
    }
}
