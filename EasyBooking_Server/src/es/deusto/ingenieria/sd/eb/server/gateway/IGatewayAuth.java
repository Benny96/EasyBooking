package es.deusto.ingenieria.sd.eb.server.gateway;

/**
 * Variación de Strategy hecha con Interfaz para login en vez de clase abstracta.
 * @author Beñat
 */
public interface IGatewayAuth {
	public int darAltaUsuario(String correo);
}
