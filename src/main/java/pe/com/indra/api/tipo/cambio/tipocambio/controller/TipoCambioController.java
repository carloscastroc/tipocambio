package pe.com.indra.api.tipo.cambio.tipocambio.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.indra.api.tipo.cambio.tipocambio.entity.Moneda;
import pe.com.indra.api.tipo.cambio.tipocambio.request.ActualizarTipoCambioRequest;
import pe.com.indra.api.tipo.cambio.tipocambio.response.VerTipoCambioResponse;
import pe.com.indra.api.tipo.cambio.tipocambio.service.TipoCambioService;

@RestController
@RequestMapping("/api/tipocambio")
public class TipoCambioController {
    @Autowired
    private TipoCambioService monedaService;

    @PostMapping("/crear")
    public ResponseEntity<Moneda> crearMoneda(@RequestBody Moneda moneda) {
        Moneda monedaCreada = monedaService.crearMoneda(moneda);
        return ResponseEntity.ok(monedaCreada);
    }

    @PostMapping("/actualizar")
    public ResponseEntity<Moneda> actualizarTipoCambio(
            @RequestBody ActualizarTipoCambioRequest actualizarTipoCambioRequest) {
        Moneda monedaActualizada = monedaService.actualizarTipoCambio(actualizarTipoCambioRequest.getId(),
                actualizarTipoCambioRequest.getNuevoTipoCambio());
        return ResponseEntity.ok(monedaActualizada);
    }

    @GetMapping("/listarMonedas")
    public ResponseEntity<List<Moneda>> listarMonedas() {
        List<Moneda> monedas = monedaService.listarMonedas();
        return ResponseEntity.ok(monedas);
    }

    @GetMapping("/verTipoCambio")
    public ResponseEntity<VerTipoCambioResponse> verTipoCambio(@RequestParam BigDecimal monto,
            @RequestParam String monedaOrigen, @RequestParam String monedaDestino) {
        VerTipoCambioResponse verTipoCambioResponse = monedaService.verTipoCambio(monto, monedaOrigen, monedaDestino);
        if (verTipoCambioResponse == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(verTipoCambioResponse);
    }

}
