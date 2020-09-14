package clientes_MVC;

import java.awt.event.*;
import java.util.ArrayList;

public class ClientesControlador implements ActionListener {	
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
			//recuperar la informaci�n de todos los campos despu�s de escribir el RFC en su campo
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
				vista.error("El RFC capturado no es v�lido (10 caracteres).");
			return;
//----------------------------------------------------------------------------------
		}else if(evt.getSource() == vista.btnBorrar) {
			//borrar toda la tupla del RFC que se recuper�
			if(vista.getRfc().length() == 10)
			{
				int resp = modelo.borrarCliente(vista.getRfc());
				if(resp==1) {
					vista.exito("El Cliente fue eliminado con �xito.");
					vista.limpiarCampos();
				}
				else
					vista.error("El Cliente no existe.");
			}
			else
				vista.error("El RFC capturado no es v�lido (10 caracteres).");
			return;
//------------------------------------------------------------------------------------
		}else if(evt.getSource() == vista.btnConsultar) {
			//desplegar un modal donde aparezcan todas las tuplas de la tabla Clientes
			arrClientes = modelo.consultarClientes();
			vista.tablaRegistros(arrClientes);
			return;
//--------------------------------------------------------------------------------------
		}else if(evt.getSource() == vista.btnGrabar) {
			//guardar un registro despu�s de haber llenado todos los campos de la interfaz
			if(vista.getRfc().length() == 10 && !vista.getNombre().equals("") && vista.getEdad()!=0 && vista.getIdCiudad()!=0)
			{
				int resp = modelo.grabarCliente(vista.getRfc(),vista.getNombre(),vista.getEdad(),vista.getIdCiudad());
				if(resp==1) {
					vista.exito("Cliente grabado con �xito.");
					vista.limpiarCampos();
				}
				else
					vista.error("El RFC del cliente ya existe.");
			}
			else
				vista.error("Campos con datos inv�lidos.");
			return;
//-----------------------------------------------------------------------------------------
		}else if(evt.getSource() == vista.btnModificar) {
			 //modificar una tupla despu�s de haberla recuperado
			if(vista.getRfc().length() == 10 && !vista.getNombre().equals("") && vista.getEdad()!=0 && vista.getIdCiudad()!=0)
			{
				int resp = modelo.actualizarCliente(vista.getRfc(),vista.getNombre(),vista.getEdad(),vista.getIdCiudad());
				if(resp==1)
					vista.exito("Cliente modificado con �xito.");
				else
					vista.error("El RFC capturado no existe.");
			}
			else
				vista.error("Campos con datos inv�lidos.");
			return;
		}
	}
}