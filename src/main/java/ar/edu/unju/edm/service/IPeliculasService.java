package ar.edu.unju.edm.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Peliculas;
	@Service
	public interface IPeliculasService {
		public void guardarPelicula(@Valid Peliculas Peliculaparaguardar);
		public void modificarPelicula(Peliculas Pelicula);
		public void eliminarPelicula(Long idPelicula) throws Exception;
		public Peliculas buscarPelicula(Long idPelicula) throws Exception;
		public List<Peliculas> listarPelicula(); 
}