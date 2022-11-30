package co.edu.unicauca.distribuidos.core.proyecto.services.DTO;

    import java.io.Serializable;
import java.util.List;


    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class AsignaturaDTO implements Serializable{
    
        /**
		 * 
		 */
		private static final long serialVersionUID = -7946102287893593415L;
		private Integer idAsignatura;
        private String nombre;
        
        private List<DocenteDTO> docentes;

        private List<CursoDTO> Cursos;
        
    }