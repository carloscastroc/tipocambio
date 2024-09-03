package pe.com.indra.api.tipo.cambio.tipocambio.service;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import pe.com.indra.api.tipo.cambio.tipocambio.entity.Moneda;
import pe.com.indra.api.tipo.cambio.tipocambio.repository.TipoCambioRepository;
import pe.com.indra.api.tipo.cambio.tipocambio.response.VerTipoCambioResponse;

@SpringBootTest
public class TipoCambioServiceTest {
    @InjectMocks
    private TipoCambioService tipoCambioService;
    @Mock
    private TipoCambioRepository tipoCambioRepository;

    @Test
    public void testCrearMoneda() {
        Moneda moneda = new Moneda();
        when(tipoCambioRepository.save(moneda)).thenReturn(moneda);

        Moneda createdMoneda = tipoCambioService.crearMoneda(moneda);

        assert createdMoneda != null;
        assert createdMoneda == moneda;
    }

    @Test
    public void testActualizarTipoCambio() {
        Long id = 1L;
        BigDecimal nuevoTipoCambio = new BigDecimal("2.5");
        Moneda moneda = new Moneda();
        moneda.setId(id);
        moneda.setTipoCambio(new BigDecimal("1.5"));

        when(tipoCambioRepository.findById(id)).thenReturn(Optional.of(moneda));
        when(tipoCambioRepository.save(moneda)).thenReturn(moneda);

        Moneda updatedMoneda = tipoCambioService.actualizarTipoCambio(id, nuevoTipoCambio);

        assert updatedMoneda != null;
        assert updatedMoneda.getId().equals(id);
        assert updatedMoneda.getTipoCambio().equals(nuevoTipoCambio);
    }

    @Test
    public void testListarMonedas() {
        List<Moneda> monedas = new ArrayList<>();
        monedas.add(new Moneda());
        monedas.add(new Moneda());
        monedas.add(new Moneda());

        when(tipoCambioRepository.findAll()).thenReturn(monedas);

        List<Moneda> result = tipoCambioService.listarMonedas();

        assert result != null;
        assert result.size() == 3;
        assert result.containsAll(monedas);
    }

    @Test
    public void testVerTipoCambio() {
        BigDecimal monto = new BigDecimal("100");
        String monedaOrigen = "USD";
        String monedaDestino = "EUR";
        BigDecimal tipoCambio = new BigDecimal("1.2");

        Moneda moneda = new Moneda();
        moneda.setTipoCambio(tipoCambio);

        when(tipoCambioRepository.findByMoneda(monedaOrigen, monedaDestino)).thenReturn(Optional.of(moneda));

        VerTipoCambioResponse response = tipoCambioService.verTipoCambio(monto, monedaOrigen, monedaDestino);

        assert response != null;
        assert response.getMonto().equals(monto);
        assert response.getMonedaOrigen().equals(monedaOrigen);
        assert response.getMonedaDestino().equals(monedaDestino);
        assert response.getMontoTipoCambio().equals(monto.multiply(tipoCambio));
        assert response.getTipoCambio().equals(tipoCambio);
    }
}
