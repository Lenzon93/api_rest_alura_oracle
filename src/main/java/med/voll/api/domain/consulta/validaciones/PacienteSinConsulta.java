package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DatosAgendarConsulta datos){


        var primerHorario=datos.fecha().withHour(7);
        var ultimoHorario=datos.fecha().withHour(18);

        var pacienteConConsulta=consultaRepository.existsByPacienteIdAndFechaBetween(datos.idPaciente()
        ,primerHorario,ultimoHorario);

        if (pacienteConConsulta){

            throw new ValidationException("EL paciente ya tiene una consulta Agendada para ese dia");
        }
    }
}
