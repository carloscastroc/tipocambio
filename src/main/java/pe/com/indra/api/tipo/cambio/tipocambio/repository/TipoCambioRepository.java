package pe.com.indra.api.tipo.cambio.tipocambio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.indra.api.tipo.cambio.tipocambio.entity.Moneda;

@Repository
public interface TipoCambioRepository extends JpaRepository<Moneda, Long> {

  Optional<Moneda> findById(Long id);

  @Query("SELECT r FROM Moneda r WHERE r.monedaOrigen=:monedaOrigen AND r.monedaDestino = :monedaDestino")
  Optional<Moneda> findByMoneda(@Param("monedaOrigen") String monedaOrigen,
      @Param("monedaDestino") String monedaDestino);

}
