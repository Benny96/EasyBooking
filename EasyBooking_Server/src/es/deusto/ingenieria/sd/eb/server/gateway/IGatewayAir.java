package es.deusto.ingenieria.sd.eb.server.gateway;

import java.util.ArrayList;
import java.util.Date;

import es.deusto.ingenieria.sd.eb.server.data.Aeropuerto;

public interface IGatewayAir {
	public ArrayList <Aeropuerto> buscarVuelos(String cod_aero_or, String cod_aero_dest, Date fecha);
}
