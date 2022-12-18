package co.edu.unicauca.asae.core.proyecto.services.DTO;


import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class PersonaDTO {

	
//	@Size(min = 5, max = 45, message = "la cantidad de caracteres del nombre debe estar entre 5 y 45")
	private Integer idPersona;
	@NotNull(message = "{estudiante.identificacion.null}")
	@NotEmpty(message = "{estudiante.identificacion.empty}")
	private String noIdentificacion;
	@NotNull(message = "{estudiante.tipoIdentificacion.null}")
	@NotEmpty(message = "{estudiante.tipoIdentificacion.empty}")
	private String tipoIdentificacion;
	@NotNull(message = "{estudiante.nombres.null}")
	@NotEmpty(message = "{estudiante.nombres.empty}")
	private String nombres;
	@NotNull(message = "{estudiante.apellidos.null}")
	@NotEmpty(message = "{estudiante.apellidos.empty}")
	private String apellidos;
	
}
