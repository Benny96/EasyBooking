package es.deusto.ingenieria.sd.eb.server.gateway;

public interface IGatewayPago {
	public int efectuarPago(String correo, double cantidad);
}
