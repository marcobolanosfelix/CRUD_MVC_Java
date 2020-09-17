package clientes_MVC;

public class Expresiones {
	
	/*
	public static void main(String[] args) {
		Expresiones_Regulares obj = new Expresiones_Regulares();
		
		System.out.println("Marco Bolaños: "+obj.validarNombre("Marco Bolaños"));
		System.out.println(": "+obj.validarNombre(""));
		System.out.println("Marco  Bolaños: "+obj.validarNombre("Marco  Bolaños"));
		System.out.println("Marco Bolaños 123: "+obj.validarNombre("Marco Bolaños 123"));
		
		/*
		System.out.println("BOFM961208: "+obj.validarRFC("BOFM961208"));
		System.out.println("B2FM961208: "+obj.validarRFC("B2FM961208"));
		System.out.println(" BOFM961208: "+obj.validarRFC(" BOFM961208"));
		System.out.println("BOFM96 1208: "+obj.validarRFC("BOFM96 1208"));
		*/
		/*
		System.out.println("18: "+obj.validarEdad("18"));
		System.out.println("100: "+obj.validarEdad("100"));
		System.out.println("0: "+obj.validarEdad("0"));
		System.out.println("Marco: "+obj.validarEdad("Marco"));
	}
		*/

	
	public boolean validarNombre(String nombre) {
		return nombre.matches("^([A-Z|Ñ]{1}[a-z|ñ]+[ ]*){1,3}$");
	}
	
	public boolean validarRFC(String rfc) {
		return rfc.matches("([A-Z|Ñ]{4}[0-9]{6})");
	}
	
	public boolean validarEdad(String edad) {
		return edad.matches("^([1-9]{2})$");
	}

}
