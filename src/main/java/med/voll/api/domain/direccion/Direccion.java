package med.voll.api.domain.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {

    private String calle;

    private String distrito;
    private String numero;
    private String complemento;
    private String ciudad;

    public Direccion(DatosDireccion direccion) {
        this.calle=direccion.calle();
        this.distrito=direccion.distrito();
        this.numero=direccion.numero();
        this.complemento=direccion.complemento();
        this.ciudad=direccion.ciudad();
    }

    public Direccion actualizarDireccion(DatosDireccion direccion) {
        this.calle=direccion.calle();
        this.distrito=direccion.distrito();
        this.numero=direccion.numero();
        this.complemento=direccion.complemento();
        this.ciudad=direccion.ciudad();
        return this;
    }
}