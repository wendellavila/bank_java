package banco.dominio;

public class ContaPoupanca extends Conta {
	
	private double taxaJuros;
	
	public ContaPoupanca(double saldo, double taxa) {
		super(saldo);
	}

}
