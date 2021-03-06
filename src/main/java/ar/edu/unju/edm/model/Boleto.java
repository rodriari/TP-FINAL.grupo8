package ar.edu.unju.edm.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component					//mapeo del modelo relacional hibernate
@Entity
public class Boleto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idboleto;
	
	@ManyToOne(fetch=FetchType.LAZY)//lazy trae solo una parte
	@JoinColumn(name="dni")//parte comun de dos conjuntos
	private Usuario usuario;
	
	@ManyToOne(fetch=FetchType.LAZY)//lazy trae solo una parte
	@JoinColumn(name="id")//parte comun de dos conjuntos
	private Pelicula pelicula;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaDeCompra;
	
	@JoinColumn(name="comentario")//parte comun de dos conjuntos
	private String comentario;
	
	@JoinColumn(name="valoracion")//parte comun de dos conjuntos
	private String valoracion;
	

	public Boleto() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdboleto() {
		return idboleto;
	}

	public void setIdboleto(Integer idboleto) {
		this.idboleto = idboleto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula= pelicula;
	}

	public LocalDate getFechaDeCompra() {
		return fechaDeCompra;
	}

	public void setFechaDeCompra (LocalDate fechaDeCompra) {
		this.fechaDeCompra = fechaDeCompra;
	}
}
