package med.voll.api.domain.consulta.desafio;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosCancelamientoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioDeAntecedenciacaCancelamiento")
public class ValidadorHorarioDeAntecedencia implements ValidadorDeCancelamientoConsulta{

    private ConsultaRepository repository;


    @Override
    public void validar(DatosCancelamientoConsulta datos) {

        var consulta=repository.getReferenceById(datos.idConsulta());
        var ahora= LocalDateTime.now();
        var diferenciaEnHoras= Duration.between(ahora,consulta.getFecha()).toHours();

        if (diferenciaEnHoras<24){

            throw new ValidationException("Cnsulta solo puede ser cancelada con diferencia minima de 24 horas");
        }
    }
}