package es.deusto.ingenieria.sd.pay.server;

import java.util.Date;

public class Pago {

	private String Identificativo;
	private int Cantidad;
	private Date FechaPago;
	
	
	public Pago(String identificativo, int cantidad, Date fechaPago) {
		super();
		Identificativo = identificativo;
		Cantidad = cantidad;
		FechaPago = fechaPago;
	}
	public String getIdentificativo() {
		return Identificativo;
	}
	public void setIdentificativo(String identificativo) {
		Identificativo = identificativo;
	}
	public int getCantidad() {
		return Cantidad;
	}
	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}
	public Date getFechaPago() {
		return FechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		FechaPago = fechaPago;
	}
	
	
	
}
