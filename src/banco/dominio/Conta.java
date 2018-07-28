package banco.dominio;

import java.text.NumberFormat;
import java.util.Locale;

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
	public String sacar(double valor){
            
            NumberFormat formatoMonetario = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            String mensagem = "";
            
            if(valor <= saldo) {
                saldo -= valor;
                return mensagem;
            }
            else {
                mensagem = "Saldo insuficiente para realizar o saque. Déficit: " + formatoMonetario.format(valor - saldo);
                return mensagem;
            }
	}
	public double getChequeEspecial() {
            return 0;
	}
	
}
