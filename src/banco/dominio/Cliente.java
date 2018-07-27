package banco.dominio;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

	private String primeiroNome;
	private String ultimoNome;
	private List<Conta> conta;
	
	public Cliente(String p, String u) {
		primeiroNome = p;
		ultimoNome = u;
		conta = new ArrayList<Conta>();
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public String getUltimoNome() {
		return ultimoNome;
	}
	
	public void setConta(Conta criarconta){
		int indice = getNumeroDeContas();
		//conta[numeroDeContas] = criarconta;
		conta.add(indice, criarconta);
	}

	public Conta getConta(int indice) {
		return conta.get(indice);
	}

	public int getNumeroDeContas() {
		return conta.size();
	}
	
}
