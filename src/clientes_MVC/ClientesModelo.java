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
	public int actualizarCliente(String sRFC, String sNombre, int iEdad, int idCiudad) {
		return bd.actualizarCliente(sRFC, sNombre, iEdad, idCiudad);
	}
	
	public int grabarCliente(String sRFC, String sNombre, int iEdad, int idCiudad) {
		return bd.grabarCliente(sRFC, sNombre, iEdad, idCiudad);
	}
	
	public ArrayList<Cliente> consultarClientes() {
		return bd.consultarClientes();
	}
	
}
