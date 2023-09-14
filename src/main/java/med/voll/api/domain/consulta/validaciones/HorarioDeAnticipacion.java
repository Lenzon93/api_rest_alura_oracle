package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;

import java.time.Duration;
import java.time.LocalDateTime;

public class HorarioDeAnticipacion implements ValidadorDeConsultas {

    public void validar(DatosAgendarConsulta datos){

        var ahora= LocalDateTime.now();
        var horaConsulta=datos.fecha();
        var direcencia30Min= Duration.between(ahora,horaConsulta).toMinutes()<30;

        if (direcencia30Min){

            throw new ValidationException("Las consultas deben tener almenos 30 min de anticipacion ");
        }

    }
}
