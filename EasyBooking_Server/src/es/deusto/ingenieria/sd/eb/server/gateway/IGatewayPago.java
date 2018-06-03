package es.deusto.ingenieria.sd.eb.server.gateway;

/**
 * Variación de Strategy hecha con Interfaz para Pago en vez de clase abstracta.
 * @author Beñat
 */
public interface IGatewayPago {
	public int efectuarPago(String correo, double cantidad);
}
