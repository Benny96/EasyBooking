package es.deusto.ingenieria.sd.pay.server;

public class CuentaTarjeta {

	private String email;
	private double cant;
		
	public CuentaTarjeta(String email, double cant) {
		super();
		this.email = email;
		this.cant = cant;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getCant() {
		return cant;
	}
	public void setCant(double cant) {
		this.cant = cant;
	}
}
