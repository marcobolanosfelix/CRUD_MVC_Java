package clientes_MVC;

import java.awt.event.*;
import java.util.ArrayList;

public class ClientesControlador implements ActionListener, KeyListener {	
	private Vista vista;
	private ClientesModelo modelo;
	private Cliente datosCTE;
	private ArrayList<Cliente> arrClientes;
	
	public ClientesControlador(Vista vista, ClientesModelo modelo){
		this.vista = vista;
		this.modelo = modelo;
	}

	@Override
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == vista.btnRecuperar) {
			//recuperar la información de todos los campos después de escribir el RFC en su campo
			if(vista.getRfc().length() == 10)
			{
				datosCTE = modelo.recuperaCliente(vista.getRfc());
				if(datosCTE==null)
					vista.error("El Cliente no existe.");
				else
				{
					vista.setNombre(datosCTE.getNombre());
					vista.setEdad(datosCTE.getEdad());
					vista.setIdCiudad(datosCTE.getIdCiudad());
				}
			}
			else
				vista.error("El RFC capturado no es válido (10 caracteres).");
			return;
//--------------------------------------------------------------------------------------------
		}else if(evt.getSource() == vista.btnBorrar) {
			//borrar toda la tupla del RFC que se recuperó
			if(vista.getRfc().length() == 10)
			{
				int resp = modelo.borrarCliente(vista.getRfc());
				if(resp==1) {
					vista.exito("El Cliente fue eliminado con éxito.");
					vista.limpiarCampos();
				}
				else
					vista.error("El Cliente no existe.");
			}
			else
				vista.error("El RFC capturado no es válido (10 caracteres).");
			return;
//--------------------------------------------------------------------------------------------
		}else if(evt.getSource() == vista.btnConsultar) {
			//desplegar un modal donde aparezcan todas las tuplas de la tabla Clientes
			arrClientes = modelo.consultarClientes();
			vista.tablaRegistros(arrClientes);
			return;
//----------------------------------------------------------------------------------------------
		}else if(evt.getSource() == vista.btnGrabar) {
			//guardar un registro después de haber llenado todos los campos de la interfaz
			if(vista.getRfc().length() == 10 && !vista.getNombre().equals("") && vista.getEdad()!=0 && vista.getIdCiudad()!=0)
			{
				int resp = modelo.grabarCliente(vista.getRfc(),vista.getNombre(),vista.getEdad(),vista.getIdCiudad());
				if(resp==1) {
					vista.exito("Cliente grabado con éxito.");
					vista.limpiarCampos();
				}
				else
					vista.error("El RFC del cliente ya existe.");
			}
			else
				vista.error("Campos con datos inválidos.");
			return;
//--------------------------------------------------------------------------------------------
		}else if(evt.getSource() == vista.btnModificar) {
			 //modificar una tupla después de haberla recuperado
			if(vista.getRfc().length() == 10 && !vista.getNombre().equals("") && vista.getEdad()!=0 && vista.getIdCiudad()!=0)
			{
				int resp = modelo.actualizarCliente(vista.getRfc(),vista.getNombre(),vista.getEdad(),vista.getIdCiudad());
				if(resp==1)
					vista.exito("Cliente modificado con éxito.");
				else
					vista.error("El RFC capturado no existe.");
			}
			else
				vista.error("Campos con datos inválidos.");
			return;
		}
	}

	
	@Override
	public void keyTyped(KeyEvent evt) {
		String sRegex = "", sCaracter = "";
		sCaracter = String.valueOf(evt.getKeyChar());
		
		//Valida RFC; Letras mayusculas, primero 4 caracteres son letras, ultimos 6 son numeros 
		if(evt.getSource() == vista.txtRFC) {
			if(vista.getRfc().length() < 4) 
			{
				sRegex = "[A-Z|Ñ]";
			}else {
				sRegex = "[0-9]";
			}
			if (vista.getRfc().length()<10) 
			{
				if (evt.getKeyCode() != KeyEvent.VK_SHIFT && !sCaracter.matches(sRegex)) {
					evt.consume();
				}
			}else {
				evt.consume();
			}
			
		}
		
		//Valida nombre: Solo letras y espacios
		if(evt.getSource() == vista.txtNombre) {
			sRegex = "[A-z|ñ|Ñ]";
			if (vista.getNombre().length()<50) {
				if (evt.getKeyCode() != KeyEvent.VK_SHIFT && !sCaracter.equals(" ")  && !sCaracter.matches(sRegex)) {
					evt.consume();
				}
			}
			else {
				evt.consume();
			}
			return;
		}
		
		//Solo numeros
		if( evt.getSource() == vista.txtIdCiudad) {
			sRegex = "[0-9]";
			if (!sCaracter.matches(sRegex)) {
				evt.consume();
			}
			return;
		}
		
		//Solo numeros menor a 99
		if( evt.getSource() == vista.txtEdad) {
			sRegex = "[0-9]";
			if (vista.getEdad() <= 9) {
				if (!sCaracter.matches(sRegex)) {
					evt.consume();
				}
			}else {
				evt.consume();
			}
			return;
		}
		
		
	}

	@Override
	public void keyPressed(KeyEvent evt) {
		
	}

	@Override
	public void keyReleased(KeyEvent evt) {
	}
}