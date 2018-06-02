package es.deusto.ingenieria.sd.eb.server.gateway;

import java.io.IOException;

public interface IGatewayAuth {
	public int darAltaUsuario(String correo) throws IOException;
}
