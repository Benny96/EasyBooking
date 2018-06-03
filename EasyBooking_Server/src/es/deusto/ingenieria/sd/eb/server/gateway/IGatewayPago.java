package es.deusto.ingenieria.sd.eb.server.gateway;

/**
 * Variaci�n de Strategy hecha con Interfaz para Pago en vez de clase abstracta.
 * @author Be�at
 */
public interface IGatewayPago {
	public int efectuarPago(String correo, double cantidad);
}
