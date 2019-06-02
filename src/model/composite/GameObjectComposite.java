package model.composite;

import java.util.ArrayList;
import java.util.List;

import model.GameObject;

public class GameObjectComposite extends GameObject{

	private List<GameObject> pecas = new ArrayList<>();
	private static GameObjectComposite instance;
	
	public static GameObjectComposite getInstance() {
		if(instance == null) {
			instance = new GameObjectComposite();
		}
		return instance;
	}
	
	public GameObject addPeca(GameObject p) {
		pecas.add(p);
		return p;
	}
	
	
	@Override
	public void reset() {
		for(GameObject peca :pecas) {
			peca.reset();
		}
		
	}
	
}
