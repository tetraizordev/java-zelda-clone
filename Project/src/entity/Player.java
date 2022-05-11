package entity;

import component.AnimationManager;
import component.Collider;
import component.TileManager;
import main.GamePanel;
import resource.Collision;
import util.Vector2;

import java.awt.*;

public class Player extends Entity
{
    public Boolean isMoving = false;

    public Player(String _name, Vector2 _position, int _speed)
    {
        SetEntityProperties(_name, new Vector2(_position.x, _position.y), _speed);
    }

    public void start()
    {
        super.start();

        // Walk
        animationManager.CreateAnimation("/sprite/player/edited/player", 1, 1, 10); // Idle Down
        animationManager.CreateAnimation("/sprite/player/edited/player", 5, 5, 10); // Idle Right
        animationManager.CreateAnimation("/sprite/player/edited/player", 9, 9, 10); // Idle Up
        animationManager.CreateAnimation("/sprite/player/edited/player", 13, 13, 10); // Idle Left

        // Idle
        animationManager.CreateAnimation("/sprite/player/edited/player", 0, 3, 10); // Walk Down
        animationManager.CreateAnimation("/sprite/player/edited/player", 4, 7, 10); // Walk Right
        animationManager.CreateAnimation("/sprite/player/edited/player", 8, 11, 10); // Walk Up
        animationManager.CreateAnimation("/sprite/player/edited/player", 12, 15, 10); // Walk Left

        collider = new Collider(this, 4, 7, 9, 9);

    }

    public void update()
    {

        // Update Entity class as well.
        super.update();

        // Handle collisions


        // Handle inputs on separate function.
        InputHandler();

        if(isMoving)
        {
            animationManager.SwitchAnimation((int)(entityDirection.getValue() + 4));
        }
        else
        {
            animationManager.SwitchAnimation((int)(entityDirection.getValue()));
        }

        SwingWeapon();
    }

    public void render(Graphics2D g2D)
    {
        // Render Entity class as well.
        super.render(g2D);
    }

    void InputHandler()
    {
        isMoving = false;

        Vector2 moveVector = new Vector2(0, 0);

        if(playerKeyHandler.keyList.get(0).isButtonDown)
        {
            entityDirection = Direction.UP;
            moveVector.y = -speed;
            isMoving = true;
        }

        else if(playerKeyHandler.keyList.get(1).isButtonDown)
        {
            entityDirection = Direction.DOWN;
            moveVector.y = speed;
            isMoving = true;
        }

        else if(playerKeyHandler.keyList.get(2).isButtonDown)
        {
            entityDirection = Direction.RIGHT;
            moveVector.x = speed;
            isMoving = true;
        }

        else if(playerKeyHandler.keyList.get(3).isButtonDown)
        {
            entityDirection = Direction.LEFT;
            moveVector.x = -speed;
            isMoving = true;
        }

        collider.CheckForCollisions();

        moveVector.Normalize();

        for (Collision collision : collider.collisions) {
            if(collision.direction == entityDirection)
                isMoving = false;
        }
        if(isMoving)
            position.Add(moveVector);
    }

    public void PrintPlayerValues()
    {
        System.out.println("Player position: " + this.position.x + ", " + this.position.y);
    }

    public void SwingWeapon()
    {
        for(Collision collision : collider.collisions)
            if(collision.collisionType == Collision.CollisionType.ENTITY){
                System.out.println(collision.entity.name);
            }
    }
}