package ar.edu.unju.edm.util;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Component;
import ar.edu.unju.edm.model.Usuarios;

@Component
public class ListaUsuario {

	private List<Usuarios> listado = new ArrayList<>();
	public ListaUsuario() {
		// TODO Auto-generated constructor stub
	}
	public List<Usuarios> getListado() {
		return listado;
	}
	public void setListado(List<Usuarios> listado) {
		this.listado = listado;
	}
	
}
