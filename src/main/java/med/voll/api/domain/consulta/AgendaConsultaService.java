package med.voll.api.domain.consulta;

import jdk.jfr.Category;
import med.voll.api.domain.consulta.desafio.ValidadorDeCancelamientoConsulta;
import med.voll.api.domain.consulta.validaciones.ValidadorDeConsultas;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaConsultaService {



    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    List<ValidadorDeConsultas> validadores;

    private List<ValidadorDeCancelamientoConsulta> validadoresDeCancelamiento;



    public DatosDetalleConsulta agendar(DatosAgendarConsulta datos){

        if (!pacienteRepository.findById(datos.idPaciente()).isPresent()){

            throw new ValidacionDeIntegridad("Este id para el paciente no fue encontrado!");
        }
        if(datos.idMedico()!=null &&!medicoRepository.existsById(datos.idMedico())){

            throw new ValidacionDeIntegridad("Este id para el Medico no fue encontrado!");
        }

        validadores.forEach(v->v.validar(datos));
        var paciente=pacienteRepository.findById(datos.idPaciente()).get();

        var medico=seleccionarMedico(datos);

        if (medico==null){

            throw new ValidacionDeIntegridad("No Existen Medicos disponibles para este horario y especialidad ");
        }


        var consulta= new Consulta(medico,paciente,datos.fecha());
        consultaRepository.save(consulta);

        return new DatosDetalleConsulta(consulta);

    }

    public void cancelar(DatosCancelamientoConsulta datos){
        if(!consultaRepository.existsById(datos.idConsulta())){
            throw new ValidacionDeIntegridad("El id de la consulta no existe");

        }

        validadoresDeCancelamiento.forEach(v-> v.validar(datos));

        var consulta=consultaRepository.getReferenceById(datos.idConsulta());

        consulta.cancelar(datos.motivo());



    }

    private Medico seleccionarMedico(DatosAgendarConsulta datos) {
        if (datos.idMedico()!=null){

            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if(datos.especialidad()==null){
            throw new ValidacionDeIntegridad("debe seleccionarse una especialidad para el medico");

        }



        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datos.especialidad(),datos.fecha());
    }

}

