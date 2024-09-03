package pe.com.indra.api.tipo.cambio.tipocambio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("pe.com.indra.api.tipo.cambio.tipocambio.entity")
public class TipocambioApplication {

	public static void main(String[] args) {
		SpringApplication.run(TipocambioApplication.class, args);
	}

}
