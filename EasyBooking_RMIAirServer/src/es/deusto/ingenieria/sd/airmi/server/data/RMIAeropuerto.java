package es.deusto.ingenieria.sd.airmi.server.data;

/**TODO: ESTA CLASE SOBRA DE AQUÍ, HAY QUE PONERLO EN EL GATEWAY*/

public class RMIAeropuerto
{
	private String Nombre;
	private String Codigo;
	
	
    public RMIAeropuerto(String nombre, String codigo) {
		super();
		Nombre = nombre;
		Codigo = codigo;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getCodigo() {
		return Codigo;
	}
	public void setCodigo(String codigo) {
		Codigo = codigo;
	}
}
