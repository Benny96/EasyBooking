package es.deusto.ingenieria.sd.eb.server.gateway;

public interface IGatewayAuth {
	public int darAltaUsuario(String correo, String pass);
	public int logearUsuario(String correo, String pass);
}
