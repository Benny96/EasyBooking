package es.deusto.ingenieria.sd.eb.server.data;

public class Reserva_Persona {
	
	private String CodigoReserva;
	private String emailUsuarioReserva;
	private String Pasaporte;	
	
	public Reserva_Persona(String cod_reserva, String email, String pas)
	{
		CodigoReserva=cod_reserva;
		emailUsuarioReserva=email;
		Pasaporte=pas;
	}
	
	public String getCodigoReserva() {
		return CodigoReserva;
	}
	public void setCodigoReserva(String codigoReserva) {
		CodigoReserva = codigoReserva;
	}
	public String getEmailUsuarioReserva() {
		return emailUsuarioReserva;
	}
	public void setEmailUsuarioReserva(String emailUsuarioReserva) {
		this.emailUsuarioReserva = emailUsuarioReserva;
	}
	public String getPasaporte() {
		return Pasaporte;
	}
	public void setPasaporte(String pasaporte) {
		Pasaporte = pasaporte;
	}	

}
