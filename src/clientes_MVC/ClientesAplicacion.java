package clientes_MVC;

public class ClientesAplicacion {

	public static void main(String[] args) {
		Vista vista = new Vista();
		ClientesModelo modelo = new ClientesModelo();
		ClientesControlador controlador = new ClientesControlador(vista,modelo);
		
		vista.setControlador(controlador);
		vista.muestrate();
	}
}