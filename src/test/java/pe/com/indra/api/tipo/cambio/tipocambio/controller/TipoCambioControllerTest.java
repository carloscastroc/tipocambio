package pe.com.indra.api.tipo.cambio.tipocambio.controller;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import pe.com.indra.api.tipo.cambio.tipocambio.entity.Moneda;
import pe.com.indra.api.tipo.cambio.tipocambio.request.ActualizarTipoCambioRequest;
import pe.com.indra.api.tipo.cambio.tipocambio.response.VerTipoCambioResponse;
import pe.com.indra.api.tipo.cambio.tipocambio.service.TipoCambioService;

@SpringBootTest
public class TipoCambioControllerTest {
    @InjectMocks
    private TipoCambioController tipoCambioController;
    @Mock
    private TipoCambioService tipoCambioService;



    @Test
    public void testCrearMoneda() {
        Moneda moneda = new Moneda();
        when(tipoCambioService.crearMoneda(moneda)).thenReturn(moneda);

        ResponseEntity<Moneda> response = tipoCambioController.crearMoneda(moneda);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == moneda;
    }

    @Test
    public void testActualizarTipoCambio() {
        ActualizarTipoCambioRequest actualizarTipoCambioRequest = new ActualizarTipoCambioRequest();
        actualizarTipoCambioRequest.setId(1L);
        actualizarTipoCambioRequest.setNuevoTipoCambio(BigDecimal.valueOf(3.5));
        Moneda monedaActualizada = new Moneda();
        when(tipoCambioService.actualizarTipoCambio(1L, BigDecimal.valueOf(3.5))).thenReturn(monedaActualizada);

        ResponseEntity<Moneda> response = tipoCambioController.actualizarTipoCambio(actualizarTipoCambioRequest);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == monedaActualizada;
    }

    @Test
    public void testListarMonedas() {
        List<Moneda> monedas = new ArrayList<>();
        when(tipoCambioService.listarMonedas()).thenReturn(monedas);

        ResponseEntity<List<Moneda>> response = tipoCambioController.listarMonedas();

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == monedas;
    }

    @Test
    public void testVerTipoCambio() {
        BigDecimal monto = BigDecimal.valueOf(100);
        String monedaOrigen = "USD";
        String monedaDestino = "EUR";
        VerTipoCambioResponse verTipoCambioResponse = new VerTipoCambioResponse();
        when(tipoCambioService.verTipoCambio(monto, monedaOrigen, monedaDestino)).thenReturn(verTipoCambioResponse);

        ResponseEntity<VerTipoCambioResponse> response = tipoCambioController.verTipoCambio(monto, monedaOrigen, monedaDestino);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == verTipoCambioResponse;
    }
}