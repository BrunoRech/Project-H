package model;


//classe da peça da vitória régia que possue ovas amarelas
public class Vr_ClaraOvasAmarelas extends Vr_ClaraOvas {

	public Vr_ClaraOvasAmarelas() {
		super("amarelo", "imagens/Vr_ClaraOvasAmarelas.png");
		adicionarSapoAmarelo();
	}

	@Override
	public void removerFlor() {
		setImagem("imagens/Vr_ClaraOvasAmarelas.png");
		this.hasFlor = false;
		corFlor = "";
	}

	@Override
	public String removerSapo() {
		if (hasSapo) {
			this.hasSapo = false;
			setImagem("imagens/Vr_ClaraOvasAmarelas.png");
			return this.sapo;
		}
		return null;
	}

	@Override
	public void virar() {
		setImagem("imagens/Vr_EscuraOvasAmarela.png");
		this.isVirada = true;
	}

	@Override
	public void desvirar() {
		setImagem("imagens/Vr_ClaraOvasAmarelas.png");
		this.isVirada = false;
	}

	@Override
	public void adicionarFlorAmarela() {
		setImagem("imagens/Vr_ClaraOvasAmarelasFlorAmarela.png");
		this.hasFlor = true;
		corFlor = "amarela";
	}

	@Override
	public void adicionarFlorVermelha() {
		setImagem("imagens/Vr_ClaraOvasAmarelasFlorVermelha.png");
		this.hasFlor = true;
		corFlor = "vermelha";
	}

	@Override
	public void adicionarSapoAmarelo() {
		setImagem("imagens/Vr_ClaraOvasAmarelasSapoAmarelo.png");
		this.hasSapo = true;
		this.sapo = "amarelo";
	}

	@Override
	public void adicionarSapoVermelho() {
		setImagem("imagens/Vr_ClaraOvasAmarelasSapoVermelho.png");
		this.hasSapo = true;
		this.sapo = "vermelho";
	}

	@Override
	public void reset() {
		this.hasSapo = true;
		this.hasFlor = false;
		corFlor = "";
		setImagem("imagens/Vr_ClaraOvasAmarelasSapoAmarelo.png");

	}

}
