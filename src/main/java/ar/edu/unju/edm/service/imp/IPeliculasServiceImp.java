package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.controller.UsuarioController;
import ar.edu.unju.edm.model.Peliculas;
import ar.edu.unju.edm.repository.PeliculaRepository;
import ar.edu.unju.edm.service.IPeliculasService;
import ar.edu.unju.edm.util.ListaPeliculas;

@Service
	public class IPeliculasServiceImp implements IPeliculasService{
		private static final Log GRUPO8 = LogFactory.getLog(UsuarioController.class);
		
		@Autowired
		ListaPeliculas lista;
		
		@Autowired
		PeliculaRepository peliculaRepository;
		
		public void guardarPelicula(@Valid Peliculas peliculaparaguardar) {
			// TODO Auto-generated method stub
			
			peliculaRepository.save(peliculaparaguardar);
		}

		public void modificarPelicula(Peliculas pelicula) {
			// TODO Auto-generated method stub
			
		}

		public void eliminarPelicula(Long idp) throws Exception {
			// TODO Auto-generated method stub
			
		}

		public Peliculas buscarPelicula(Long idp) throws Exception {
			// TODO Auto-generated method stub
			Peliculas peliculaEncontrada = new Peliculas();
			peliculaEncontrada = peliculaRepository.findById(idp).orElseThrow(()->new Exception("Pelicula No Encontrada"));
			return null;
		}

		public List<Peliculas> listaPelicula() {
			// TODO Auto-generated method stub
			
			List<Peliculas> auxiliar = new ArrayList<>();
			GRUPO8.info("ingresando al metodo arraylist: listar peliculas");
			auxiliar=(List<Peliculas>) peliculaRepository.findAll();
			return auxiliar;
		}

		@Override
		public List<Peliculas> listarPelicula() {
			// TODO Auto-generated method stub
			return null;
		}

		
	}

