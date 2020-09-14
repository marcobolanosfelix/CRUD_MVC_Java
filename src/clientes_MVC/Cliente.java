package clientes_MVC;

public class Cliente {
	private String sRFC;
	private String sNombre;
	private int iEdad;
	private int idCiudad;
	
	public Cliente() {
		
	}
	
	public Cliente(String sRFC, String sNombre, int iEdad,int idCiudad){
		this.sRFC = sRFC;
		this.sNombre = sNombre;
		this.iEdad = iEdad;
		this.idCiudad = idCiudad;
	}
	
	public String getRFC() {
		return sRFC;
	}
	public void setRFC(String sRFC) {
		this.sRFC = sRFC;
	}
	public String getNombre() {
		return sNombre;
	}
	public void setNombre(String sNombre) {
		this.sNombre = sNombre;
	}
	public int getEdad() {
		return iEdad;
	}
	public void setEdad(int iEdad) {
		this.iEdad = iEdad;
	}
	public int getIdCiudad() {
		return idCiudad;
	}
	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}
	

}
