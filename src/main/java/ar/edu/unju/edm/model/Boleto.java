package ar.edu.unju.edm.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component					//mapeo del modelo relacional hibernate
@Entity
@Table (name = "Boleto")
public class Boleto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idBoleto;
	
	@ManyToOne(fetch=FetchType.LAZY)//lazy trae solo una parte
	@JoinColumn(name="id")//parte comun de dos conjuntos
	private Usuarios usuario;
	
	@ManyToOne(fetch=FetchType.LAZY)//lazy trae solo una parte
	@JoinColumn(name="idp")//parte comun de dos conjuntos
	private Peliculas pelicula;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fechaDeCompra;
	
	@JoinColumn(name="comentario")//parte comun de dos conjuntos
	private String comentario;
	
	@JoinColumn(name="valoracion")//parte comun de dos conjuntos
	private String valoracion;
	
	public Boleto() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdUsuarioPelicula() {
		return idBoleto;
	}

	public void setIdBoleto(Long idBoleto) {
		this.idBoleto = idBoleto;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public Peliculas getPelicula() {
		return pelicula;
	}

	public void setPelicula(Peliculas pelicula) {
		this.pelicula= pelicula;
	}

	public LocalDate getFechaDeCompra() {
		return fechaDeCompra;
	}

	public void setFechaDeCompra (LocalDate fechaDeCompra) {
		this.fechaDeCompra = fechaDeCompra;
	}
}
