package co.edu.unicauca.asae.core.proyecto.services.DTO;


import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DireccionDTO {

	private Integer idEstudiante;
	@NotNull(message = "{direccion.calle.null}")
	@NotEmpty(message = "{direccion.calle.empty}")
    @Size(min=5, max = 25, message = "{direccion.calle.size}")
	private String calle;
<<<<<<< Updated upstream

	@NotNull(message = "{direccion.localidad.null}")
	@NotEmpty(message = "{direccion.localidad.empty}")
	private String localidad;

=======
	
	@NotNull(message = "{direccion.localidad.null}")
	@NotEmpty(message = "{direccion.localidad.empty}")
	private String localidad;
	@Valid
>>>>>>> Stashed changes
	@JsonIgnoreProperties(value="objDireccion")
	private EstudianteDTO objEstudiante;

}
