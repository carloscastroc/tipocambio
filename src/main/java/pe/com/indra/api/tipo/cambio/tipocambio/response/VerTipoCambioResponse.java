package pe.com.indra.api.tipo.cambio.tipocambio.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VerTipoCambioResponse {

  private BigDecimal monto;
  private String monedaOrigen;
  private String monedaDestino;
  private BigDecimal montoTipoCambio;
  private BigDecimal tipoCambio;
  
}
