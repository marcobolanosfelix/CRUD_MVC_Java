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
	        
	        for (Cliente  c: consultarClientes())
	        {
	        	 System.out.println(c.getRFC());
	        }
	    } catch (Exception ex) {
	        System.out.println("ClientesDAO() Error -->" + ex.getMessage());
	    }
	}
	
	
	public Cliente recuperaCliente(String sRFC) {
		Cliente objClient = null;
		try {
			statement = ConexionBD.createStatement();
			sQuery = "SELECT rfc, nombre, edad, idCiudad FROM Clientes WHERE rfc = ?";
			pStatement = ConexionBD.prepareStatement(sQuery);
			pStatement.setString(1,sRFC);
			ResultSet rs= pStatement.executeQuery();
			
			if(rs.next())
				objClient = new Cliente(rs.getObject("rfc").toString(), rs.getObject("nombre").toString(), (int)rs.getObject("edad"), (int) rs.getObject("idciudad"));
			
		} catch (SQLException ex) {
			 System.out.println("recuperaCliente() Error -->" + ex.getMessage());
			 objClient = null;
		}	
		return objClient;
	}
	
	
	public int borrarCliente(String sRFC) {
		int iRespuesta = 0;
		try {			
			sQuery = "DELETE FROM Clientes WHERE rfc = ?";
			pStatement = ConexionBD.prepareStatement(sQuery);
			pStatement.setString(1,sRFC);
			
			iRespuesta = pStatement.executeUpdate();
			pStatement.close();
			
		} catch (SQLException ex) {
			 System.out.println("borrarCliente() Error -->" + ex.getMessage());
			 iRespuesta = 0;
		}
		return iRespuesta;
	}
	
	
	public int actualizarCliente(Cliente objCliente) {
		int iRespuesta = 0;
		try {
			sQuery = "UPDATE Clientes SET nombre = ?, edad = ?, idCiudad = ? WHERE rfc = ?";
			pStatement = ConexionBD.prepareStatement(sQuery);
			pStatement.setString(1,objCliente.getNombre());
			pStatement.setInt(2,objCliente.getEdad());
			pStatement.setInt(3,objCliente.getIdCiudad());
			pStatement.setString(4,objCliente.getRFC());
			iRespuesta = pStatement.executeUpdate();
			pStatement.close();
			
		} catch (SQLException ex) {
			 System.out.println("actualizarCliente() Error -->" + ex.getMessage());
			 iRespuesta = 0;
		}
		return iRespuesta;
	}
	
	
	public int grabarCliente(Cliente objCliente) {
		int iRespuesta = 0;
		try {		
			sQuery = "INSERT INTO Clientes values (?,?,?,?)";
			pStatement = ConexionBD.prepareStatement(sQuery);
			pStatement.setString(1,objCliente.getRFC());
			pStatement.setString(2,objCliente.getNombre());
			pStatement.setInt(3,objCliente.getEdad());
			pStatement.setInt(4,objCliente.getIdCiudad());
			iRespuesta = pStatement.executeUpdate();
			pStatement.close();
			
		} catch (SQLException ex) {
			 System.out.println("grabarCliente() Error -->" + ex.getMessage());
			 iRespuesta = 0;
		}
		return iRespuesta;
	}
	
	
	public ArrayList<Cliente> consultarClientes() {
		ArrayList<Cliente> arrClientes = new ArrayList<Cliente>();
		try {
			statement = ConexionBD.createStatement();
			sQuery = "SELECT rfc, nombre, edad, idCiudad FROM Clientes";
			ResultSet rs= statement.executeQuery(sQuery);
			
			while(rs.next()) {
				arrClientes.add(new Cliente(rs.getObject("rfc").toString(), rs.getObject("nombre").toString(), (int)rs.getObject("edad"), (int) rs.getObject("idCiudad")));
			}
			statement.close();
			rs.close();
			
		} catch (SQLException ex) {
			 System.out.println("consultarClientes() Error -->" + ex.getMessage());
		}	
		return arrClientes;
	}
	
}
