package banco.relatorios;

import banco.dominio.*;

import java.text.NumberFormat;
import java.util.*;

public class RelatorioClientes {
    
    public String relatorio = "";

    public String geraRelatorio() {
        NumberFormat formatoMonetario = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));


        Banco banco = Banco.getBanco();
        Cliente cliente;
        Conta conta;
        
        System.out.println("\t\t\tRelatório de Clientes");
        System.out.println("\t\t\t================");

        int indiceCliente = banco.getNumeroDeClientes();
        int indiceConta;

        for(int i = 0; i < indiceCliente; i++) {
            cliente = banco.getCliente(i);
            indiceConta = cliente.getNumeroDeContas();

            System.out.println();
            System.out.println("Cliente: " + cliente.getUltimoNome() + ", " + cliente.getPrimeiroNome());

            relatorio += "\nCliente: " + cliente.getUltimoNome() + ", "+ cliente.getPrimeiroNome() + "\n";

            for(int j = 0; j < indiceConta; j++) {
                conta = cliente.getConta(j);
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

                relatorio += "    " + tipoConta + ": Saldo atual de " + formatoMonetario.format(conta.getSaldo()) + "\n";
            }
            relatorio+= "\n";
        }
        return relatorio;
    }
}


