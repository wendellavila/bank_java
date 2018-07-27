package banco.dominio;

public class ExcecaoChequeEspecial extends Exception {
	private double deficit;

	public double getDeficit() {
		return deficit;
	}
	
	public ExcecaoChequeEspecial(String mensagem, double deficit) {
		super(mensagem);
		this.deficit = deficit;
	}
}
