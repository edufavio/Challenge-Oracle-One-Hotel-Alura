package factory;

public class ValorReserva {
	
	private static final Long valorReserva = 5000L;
	
	public static Long getValor(Long fechaSalida, Long fechaEntrada) {
		long diferenciaEnMilisegundos = fechaSalida - fechaEntrada;
		long diferenciaEnDias = (diferenciaEnMilisegundos <= 0) ? 1 : (diferenciaEnMilisegundos / (24 * 60 * 60 * 1000)) + 1;
		
		return diferenciaEnDias * valorReserva;
	}

}
