package banco.dominio;

import java.text.NumberFormat;
import java.util.Locale;

public class ContaCorrente extends Conta {
	
	private double chequeEspecial;
	
	public ContaCorrente(double saldo) {
		super(saldo);
	}
	public ContaCorrente(double saldo, double protecao) {
		super(saldo);
		chequeEspecial = protecao;
	}
	public String sacar(double total) {
            
                NumberFormat formatoMonetario = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
                String mensagem = "";
                
		if(total <= super.saldo) {
                    super.saldo -= total;
                    return mensagem;
		}
		else if(total <= super.saldo + chequeEspecial){
                    double temp = chequeEspecial;
                    chequeEspecial += (super.saldo - total);
                    super.saldo -= total - temp;
                    return mensagem;
		}
		else if(total > super.saldo + chequeEspecial && chequeEspecial == 0) {
                    mensagem = "Saldo insuficiente para realizar o saque. Conta sem cheque especial. Déficit: "
                                + formatoMonetario.format(total - super.saldo);
                    super.saldo = 0;
                    return mensagem;
		}
		else {
                    mensagem = "O cheque especial não foi suficiente para cobrir o saque. Déficit: "
                                + formatoMonetario.format( (total - (super.saldo + chequeEspecial)) );
                    return mensagem;
		}
	}
	public double getChequeEspecial() {
		return chequeEspecial;
	}

}
