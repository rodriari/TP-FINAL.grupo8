package ar.edu.unju.edm.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.edm.model.Peliculas;

@Component
public class ListaPeliculas {

		private List<Peliculas> listado = new ArrayList<>();
		
		//Constructor por defecto
		public ListaPeliculas() {
			// TODO Auto-generated constructor stub
		}

		//Getter and Setters
		public List<Peliculas> getListado() {
			return listado;
		}

		public void setListado(List<Peliculas> listado) {
			this.listado = listado;
		}
}
