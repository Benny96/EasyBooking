package es.deusto.ingenieria.sd.eb.server.gateway;

import java.util.ArrayList;

import es.deusto.ingenieria.sd.eb.server.data.Aeropuerto;

public interface IGatewayAir {
	public ArrayList <Aeropuerto> buscarVuelos();
}
