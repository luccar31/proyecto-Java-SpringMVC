package ar.edu.unlam.tallerweb1.domain.alertas;

import java.util.List;

public interface ServicioAlertas {
    String obtenerAlertasInventario();

    List<Alerta> obtenerAlertasCaja();
}
