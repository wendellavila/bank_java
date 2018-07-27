package banco.relatorios;

import banco.dominio.*;

import java.text.NumberFormat;
import java.util.*;

public class RelatorioClientes {

  public void geraRelatorio() {
    NumberFormat formatoMonetario = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    
    Banco banco = Banco.getBanco();
    Cliente cliente;

    System.out.println("\t\t\tRelatório de Clientes");
    System.out.println("\t\t\t================");
    
    int indiceCliente = banco.getNumeroDeClientes();
    int indiceConta;

    while(indiceCliente > 0){
      cliente = banco.getCliente(indiceCliente);
      indiceConta = cliente.getNumeroDeContas();

      System.out.println();
      System.out.println("Cliente: "
			 + cliente.getUltimoNome() + ", "
			 + cliente.getPrimeiroNome());

      while(indiceConta > 0) {
    	Conta conta = cliente.getConta(indiceConta);
    	String  tipoConta = "";

		// Determina o tipo de conta
		if ( conta instanceof ContaPoupanca ){
		  tipoConta = "Conta Poupanca";
		}
		else if ( conta instanceof ContaCorrente ){
		  tipoConta = "Conta Corrente";
		}
		else {
		  tipoConta = "Tipo desconhecido de conta";
		}

		// Exibe os saldos da conta
		System.out.println("    " + tipoConta + ": Saldo atual de "
			 + formatoMonetario.format(conta.getSaldo()));
		indiceConta--;
      }
      indiceCliente--;
    }
  }

}


