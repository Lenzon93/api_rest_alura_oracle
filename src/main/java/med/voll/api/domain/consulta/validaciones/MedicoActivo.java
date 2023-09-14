package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoActivo implements ValidadorDeConsultas {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DatosAgendarConsulta datos){

        if (datos.idMedico()==null){
            return;

        }
        var medicoActico=medicoRepository.findActivoById(datos.idMedico());

        if (!medicoActico){
            throw new ValidationException("No se puede agendar con medicos inactivos");
        }



    }
}
