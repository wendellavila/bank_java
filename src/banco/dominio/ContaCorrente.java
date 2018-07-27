package banco.dominio;

public class ContaCorrente extends Conta {
	
	private double chequeEspecial;
	
	public ContaCorrente(double saldo) {
		super(saldo);
	}
	public ContaCorrente(double saldo, double protecao) {
		super(saldo);
		chequeEspecial = protecao;
	}
	public void sacar(double total) throws ExcecaoChequeEspecial {
		if(total <= super.saldo) {
			super.saldo -= total;
		}
		else if(total <= super.saldo + chequeEspecial){
			double temp = chequeEspecial;
			chequeEspecial += (super.saldo - total);
			super.saldo -= total - temp;
		}
		else if(total > super.saldo + chequeEspecial && chequeEspecial != 0) {
			super.saldo = 0;
			throw new ExcecaoChequeEspecial("O cheque especial não foi suficiente para cobrir o saque.", total);
		}
		else {
			throw new ExcecaoChequeEspecial("Não há saldo suficiente e conta sem cheque especial", total - (super.saldo + chequeEspecial));
		}
	}
	public double getChequeEspecial() {
		return chequeEspecial;
	}

}
