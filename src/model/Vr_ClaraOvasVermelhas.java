package model;

//classe da peça da vitória régia que possue ovas vermelhas
public class Vr_ClaraOvasVermelhas extends Vr_ClaraOvas {

	public Vr_ClaraOvasVermelhas() {
		super("vermelho","imagens/Vr_ClaraOvasVermelhas.png");
		adicionarSapoVermelho();
	}
	
	@Override
	public void removerFlor() {
		setImagem("imagens/Vr_ClaraOvasVermelhas.png");
		this.hasFlor = false;
	}
	
	@Override
	public String removerSapo() {
		if (hasSapo) {
			this.hasSapo = false;
			setImagem("imagens/Vr_ClaraOvasVermelhas.png");
			return this.sapo;
		}
		return null;
	}
	
	@Override
	public void virar() {
		setImagem("imagens/Vr_EscuraOvasVermelhas.png");
		this.isVirada = true;
	}
	
	@Override
	public void desvirar() {
		setImagem("imagens/Vr_ClaraOvasVermelhas.png");
		this.isVirada = false;
	}

	@Override
	public void adicionarFlorAmarela() {
		setImagem("imagens/Vr_ClaraOvasVermelhasFlorAmarela.png");
		this.hasFlor = true;
	}

	@Override
	public void adicionarFlorVermelha() {
		setImagem("imagens/Vr_ClaraOvasVermelhasFlorVermelha.png");
		this.hasFlor = true;
	}

	@Override
	public void adicionarSapoAmarelo() {
		setImagem("imagens/Vr_ClaraOvasVermelhasSapoAmarelo.png");
		this.hasSapo = true;
		this.sapo = "amarelo";
	}

	@Override
	public void adicionarSapoVermelho() {
		setImagem("imagens/Vr_ClaraOvasVermelhasSapoVermelho.png");
		this.hasSapo = true;
		this.sapo = "vermelho";
	}

	@Override
	public void reset() {
		this.hasFlor = false;
		this.hasSapo = true;
		setImagem("imagens/Vr_ClaraOvasVermelhasSapoVermelho.png");
		
	}

	
}
