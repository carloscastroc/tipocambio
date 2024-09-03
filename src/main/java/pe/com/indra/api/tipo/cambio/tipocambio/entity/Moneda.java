package pe.com.indra.api.tipo.cambio.tipocambio.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "moneda", uniqueConstraints={@UniqueConstraint(columnNames={"moneda_origen", "moneda_destino"})})
public class Moneda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "moneda_origen")
    private String monedaOrigen;
    @Column(name = "moneda_destino")
    private String monedaDestino;
    @Column(name = "tipo_cambio")
    private BigDecimal tipoCambio;
    
}
