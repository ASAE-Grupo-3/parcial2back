package co.edu.unicauca.distribuidos.core.proyecto.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Direccion")
public class DireccionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEstudiante;
	@Column(name="numeroTelefono", nullable = false, length = 150)
	private String numeroTelefono;
	@Column(name="tipoTelefono", nullable = false, length = 150)
	private String tipoTelefono;

	@OneToOne(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
	@JoinColumn(name = "idPersona", nullable = false)
	private EstudianteEntity objEstudiante;

}
