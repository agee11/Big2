package com.mygdx.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class EntityManager {

	private final Array<Entity> entities = new Array<Entity>();
	
	public EntityManager(){
		
	}
	
	public void update(){
		for (Entity entity : entities) {
			entity.update();
		}
	}
	
	public void render(SpriteBatch sb){
		for (Entity entity : entities) {
			entity.render(sb);
		}
	}
	
	public void addEntity(Entity entity){
		entities.add(entity);
	}
	
}
