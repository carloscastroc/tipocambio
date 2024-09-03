package pe.com.indra.api.tipo.cambio.tipocambio.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.indra.api.tipo.cambio.tipocambio.entity.Moneda;
import pe.com.indra.api.tipo.cambio.tipocambio.repository.TipoCambioRepository;
import pe.com.indra.api.tipo.cambio.tipocambio.response.VerTipoCambioResponse;

@Service
public class TipoCambioService {
    @Autowired
    private TipoCambioRepository tipoCambioRepository;

    public Moneda crearMoneda(Moneda moneda) {
        return tipoCambioRepository.save(moneda);
    }

    public Moneda actualizarTipoCambio(Long id, BigDecimal nuevoTipoCambio) {
        Moneda moneda = tipoCambioRepository.findById(id).orElse(null);
        moneda.setTipoCambio(nuevoTipoCambio);
        return tipoCambioRepository.save(moneda);
    }

    public List<Moneda> listarMonedas() {
        return tipoCambioRepository.findAll();
    }

    public VerTipoCambioResponse verTipoCambio(BigDecimal monto, String monedaOrigen, String monedaDestino) {
        Moneda moneda = tipoCambioRepository
                .findByMoneda(monedaOrigen, monedaDestino)
                .orElse(null);
        if (moneda == null) {
            return null;
        }
        BigDecimal montoConvertido = monto.multiply(moneda.getTipoCambio());

        return new VerTipoCambioResponse(monto, monedaOrigen,
                monedaDestino, montoConvertido, moneda.getTipoCambio());
    }

}
