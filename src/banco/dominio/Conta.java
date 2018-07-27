package banco.dominio;

public class Conta {

	protected double saldo;
	
	public double getSaldo() {
		return saldo;
	}
	
	public Conta(double saldoInicial) {
		saldo = saldoInicial;
	}
	
	public boolean depositar(double valor){
		if(valor > 0) {
			saldo += valor;
			return true;
		}
		else {
			return false;
		}
		
	}
	public void sacar(double valor) throws ExcecaoChequeEspecial {
		if(valor <= saldo) {
			saldo -= valor;
		}
		else {
			throw new ExcecaoChequeEspecial("Saldo insuficiente", valor);
		}
	}
	public double getChequeEspecial() {
		return 0;
	}
	
}
