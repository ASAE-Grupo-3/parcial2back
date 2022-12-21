package co.edu.unicauca.asae.core.proyecto.services.services.clienteServices;

import java.util.List;

import co.edu.unicauca.asae.core.proyecto.services.DTO.EstudianteDTO;

public interface IEstudianteService {

	public List<EstudianteDTO> findAll();

	public EstudianteDTO findById(Integer id);

	public List<EstudianteDTO> findByNoIdentificacionContaining(Integer id);

	public EstudianteDTO save(EstudianteDTO cliente);

	public EstudianteDTO update(Integer id, EstudianteDTO cliente);

	public boolean delete(Integer id);
}
