package entity;

import component.Collider;
import util.Vector2;

public class Decor extends Entity {

    public int spriteIndex;

    public Decor(String _name, Vector2 _position, int _spriteIndex) {
        super(_name, _position);

        collider = new Collider(this, 0, 0, 0, 0, false, false);
        animationManager.CreateAnimation("/sprite/decor/decor", _spriteIndex * 4 + (int)(Math.random() * 4), 1);
    }


}
