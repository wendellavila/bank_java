package banco.dominio;
import java.util.ArrayList;
import java.util.List;

public class Banco {
	
	private List<Cliente> cliente;
	//private int numeroDeClientes = 0;
	private static Banco banco;
	
	public static Banco getBanco() {
		if(banco == null) {
			banco = new Banco();
		}
		return banco;
	}
	
	private Banco() {
		cliente = new ArrayList<Cliente>();
	}
	
	public void adicionarCliente(String p, String u){
		int indice = getNumeroDeClientes();
		cliente.add(indice, new Cliente(p, u));
	}
	public int getNumeroDeClientes() {
		return cliente.size();
	}
	public Cliente getCliente(int indice) {
		return cliente.get(indice);
	}
        public int getClienteIndex(String p, String u){
            for(int i = 0; i < getNumeroDeClientes(); i++){
                Cliente temp = cliente.get(i);
                String pnome = temp.getPrimeiroNome();
                String unome = temp.getUltimoNome();
                if(pnome.equals(p) && unome.equals(u)){
                    return cliente.indexOf(temp);
                }
            }
            return -1;
        }
	
	
	
	
}
