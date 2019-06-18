package model.composite;

import java.util.ArrayList;
import java.util.List;
import model.Flor;

//Classe do composite das flores do jogo
public class Flor_Composite extends Flor{
	//lista
	private List<Flor> flores = new ArrayList<>();
	
	public Flor_Composite() {}
	
	//Pega o tamanho no formato de arvore
	@Override
	public int size() {
		int total = 0;
		for (Flor flor : flores) {
			total += flor.size();
		}
		return total;
	}
	
	public Flor remove(int i) {
		return flores.remove(i);
	}
	
	public Flor get(int i) {
		return flores.get(i);
	}
	
	public void add(Flor f) {
		flores.add(f);
	}
	
	public void clear() {
		flores.clear();
	}

}
