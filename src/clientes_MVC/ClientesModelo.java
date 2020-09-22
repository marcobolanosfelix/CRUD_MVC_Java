package clientes_MVC;

import java.util.ArrayList;

public class ClientesModelo {
	ClientesDAO bd = new ClientesDAO();
	
	
	public Cliente recuperaCliente(String sRFC) {
		return bd.recuperaCliente(sRFC);
	}
	
	public int borrarCliente(String sRFC) {
		return bd.borrarCliente(sRFC);
	}
	public int actualizarCliente(Cliente objCliente) {
		return bd.actualizarCliente(objCliente);
	}
	
	public int grabarCliente(Cliente objCliente) {
		return bd.grabarCliente(objCliente);
	}
	
	public ArrayList<Cliente> consultarClientes() {
		return bd.consultarClientes();
	}
}
