package clientes_MVC;

import java.sql.*;
import java.util.ArrayList;

public class ClientesDAO {
	private Connection ConexionBD;
	private Statement statement;
	private PreparedStatement pStatement;
	private String sQuery;
	
	public ClientesDAO() {
		  try {
	        Class.forName("com.mysql.jdbc.Driver");
	        ConexionBD = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Registro",
	                "root", "Metalgear");
	       //recuperaCliente("AICP980316");
	        //borrarCliente("AICP980316");
	        //actualizarCliente("AICP980316", "Pedrisho", 16, 3);
	        //grabarCliente("AICP980317", "Pedrisho", 16, 3);
	        
	        for (Cliente c: consultarClientes()) {
	        	 System.out.println(c.getRFC());
	        }
	    } catch (Exception ex) {
	        System.out.println("ClientesDAO() Error -->" + ex.getMessage());
	    }
	}
	
	//Consultar tupla mediante el RFC
	public Cliente recuperaCliente(String sRFC) {
		Cliente objClient = null;
		try {
			statement = ConexionBD.createStatement();
			sQuery = "SELECT rfc, nombre, edad, idciudad FROM clientes WHERE rfc = '"+sRFC+"'";
			ResultSet rs= statement.executeQuery(sQuery);
			
			if(rs.next()) {
				objClient = new Cliente(rs.getObject("rfc").toString(), rs.getObject("nombre").toString(), (int)rs.getObject("edad"), (int) rs.getObject("idciudad"));
			}
					
		} catch (SQLException ex) {
			 System.out.println("recuperaCliente() Error -->" + ex.getMessage());
			 objClient = null;
		}
		
		return objClient;
	}
	
	//Remover tupla
	public int borrarCliente(String sRFC) {
		int iRespuesta = 0;
		try {
			statement = ConexionBD.createStatement();
			sQuery = "DELETE FROM clientes WHERE rfc = '"+sRFC+"'";
			iRespuesta = statement.executeUpdate(sQuery);
			
		} catch (SQLException ex) {
			 System.out.println("borrarCliente() Error -->" + ex.getMessage());
			 iRespuesta = 0;
		}
		return iRespuesta;
	}
	
	//Actualizar tupla
	public int actualizarCliente(String sRFC, String sNombre, int iEdad, int idCiudad) {
		int iRespuesta = 0;
		try {
			
			sQuery = "UPDATE clientes SET nombre = ?, edad = ?, idCiudad = ? WHERE rfc = ?";
			pStatement = ConexionBD.prepareStatement(sQuery);
			pStatement.setString(1,sNombre);
			pStatement.setInt(2,iEdad);
			pStatement.setInt(3,idCiudad);
			pStatement.setString(4,sRFC);
			iRespuesta = pStatement.executeUpdate();
			
			
		} catch (SQLException ex) {
			 System.out.println("actualizarCliente() Error -->" + ex.getMessage());
			 iRespuesta = 0;
		}
		return iRespuesta;
	}
	
	//Insertar tupla
	public int grabarCliente(String sRFC, String sNombre, int iEdad, int idCiudad) {
		int iRespuesta = 0;
		try {
			sQuery = "INSERT INTO clientes values (?,?,?,?)";
			pStatement = ConexionBD.prepareStatement(sQuery);
			pStatement.setString(1,sRFC);
			pStatement.setString(2,sNombre);
			pStatement.setInt(3,iEdad);
			pStatement.setInt(4,idCiudad);
			iRespuesta = pStatement.executeUpdate();
			
		} catch (SQLException ex) {
			 System.out.println("grabarCliente() Error -->" + ex.getMessage());
			 iRespuesta = 0;
		}
		return iRespuesta;
	}
	
	//Consultar todas las tuplas
	public ArrayList<Cliente> consultarClientes() {
		ArrayList<Cliente> arrClientes = new ArrayList<Cliente>();
		try {
			statement = ConexionBD.createStatement();
			sQuery = "SELECT rfc, nombre, edad, idciudad FROM clientes";
			ResultSet rs= statement.executeQuery(sQuery);
			
			while(rs.next()) {
				arrClientes.add(new Cliente(rs.getObject("rfc").toString(), rs.getObject("nombre").toString(), (int)rs.getObject("edad"), (int) rs.getObject("idciudad")));
			}
			
		} catch (SQLException ex) {
			 System.out.println("consultarClientes() Error -->" + ex.getMessage());
		}
		
		return arrClientes;
	}
	
}
