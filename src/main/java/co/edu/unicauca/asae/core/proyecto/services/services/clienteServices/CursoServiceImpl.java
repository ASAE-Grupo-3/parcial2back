package co.edu.unicauca.asae.core.proyecto.services.services.clienteServices;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.asae.core.proyecto.exceptionControllers.exceptions.EntidadNoExisteException;
import co.edu.unicauca.asae.core.proyecto.exceptionControllers.exceptions.EntidadYaExisteException;
import co.edu.unicauca.asae.core.proyecto.models.AsignaturaEntity;
import co.edu.unicauca.asae.core.proyecto.models.CursoEntity;
import co.edu.unicauca.asae.core.proyecto.repositories.AsignaturaRepository;
import co.edu.unicauca.asae.core.proyecto.repositories.CursoRepository;
import co.edu.unicauca.asae.core.proyecto.services.DTO.CursoDTO;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service
public class CursoServiceImpl implements ICursoService {

    @Autowired
	private CursoRepository servicioAccesoBaseDatos;
    
    @Autowired
    private AsignaturaRepository servicioAsignatura;

    @Autowired
	@Qualifier("mapperCurso")
	private ModelMapper modelMapper;

	@Override
	@Transactional(readOnly = true)
	public List<CursoDTO> findAll() {
		Iterable<CursoEntity> cursosEntity = this.servicioAccesoBaseDatos.findAll();
		System.out.println("antes de la consulta"+ cursosEntity);	
		List<CursoDTO> cursosDTO = this.modelMapper.map(cursosEntity, new TypeToken<List<CursoDTO>>() {}.getType());
		return cursosDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public CursoDTO findById(Integer id) {
		System.out.println("antes de la consulta");
		CursoDTO cursoDTO = null;
		Optional<CursoEntity> optional = this.servicioAccesoBaseDatos.findById(id);
		if (optional.isPresent()) {
			CursoEntity curso = optional.get();
			
			cursoDTO = this.modelMapper.map(curso, CursoDTO.class);
		}else {
			EntidadNoExisteException objException = new EntidadNoExisteException("Curso con idCurso: "
					+ id+" no existe en la Base De Datos.");
			throw objException;
		}
		
		return cursoDTO;
	}
	

	@Override
	@Transactional()
	public CursoDTO save(CursoDTO cursoDTO) {

		validarCursosExistentes(cursoDTO);
		validarIdCurso(cursoDTO);
		AsignaturaEntity asignatura = this.servicioAsignatura.findById(cursoDTO.getObjAsignatura().getIdAsignatura()).orElse(null);
		CursoDTO cursoRespuesta = null;
		if (cursoDTO.getObjAsignatura()!=null && asignatura!=null) {
			
			CursoEntity cursoEntity = this.modelMapper.map(cursoDTO, CursoEntity.class);
			cursoEntity.setObjAsignatura(asignatura);
			if(cursoEntity.getObjAsignatura().getCursos()!=null)
				cursoEntity.getObjAsignatura().getCursos().add(cursoEntity);
			
			CursoEntity objCursoEntity = this.servicioAccesoBaseDatos.save(cursoEntity);
			cursoRespuesta = this.modelMapper.map(objCursoEntity, CursoDTO.class);
		}
		
		return cursoRespuesta;
	}

	
	private void validarIdCurso(CursoDTO curso) {
		if (curso.getIdCurso()!=null) {
			if (this.servicioAccesoBaseDatos.existsById(curso.getIdCurso())) {
				EntidadYaExisteException objExcepcion = new EntidadYaExisteException("CURSO con IdCurso: "
			+curso.getIdCurso()+" existe en la Base De Datos.");
				throw objExcepcion;
			}
		}
	}

	private void validarCursosExistentes(CursoDTO cursos) {
		List<CursoEntity> cursosEntityRequest = this.servicioAccesoBaseDatos.findByNombre(cursos.getNombre());
		
		if (!cursosEntityRequest.isEmpty()) {
			boolean isCurso = false;
			for (CursoEntity objCurso : cursosEntityRequest) {
				if (objCurso.getNombre().equals(cursos.getNombre())) {
					isCurso = true;
					break;
				}
			}
			if (isCurso) {
				EntidadYaExisteException objExcepcion = new EntidadYaExisteException("El Curso: "+cursos.getNombre()+
						" ya existe en la Base De Datos.");
				throw objExcepcion;
			}
		}
	}

	@Override
	@Transactional(readOnly = false)
	public CursoDTO update(Integer id, CursoDTO objcursoConDatosNuevos) {
        return null;
	}
	
	@Override
	@Transactional(readOnly = false)
	public boolean delete(Integer id) {
		
		boolean estado = false;
		if(this.servicioAccesoBaseDatos.existsById(id)) {
			this.servicioAccesoBaseDatos.deleteById(id);
			estado = !this.servicioAccesoBaseDatos.existsById(id);
		}
		else {
			EntidadNoExisteException objException = new EntidadNoExisteException("Curso con idCurso: "
					+ id+" no existe en la Base De Datos.");
			throw objException;
		}
		return estado;
	}
}
