package ar.edu.unju.edm.util;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import ar.edu.unju.edm.model.Comentario;

@Component
public class ListaComentario  {
	private List <Comentario> listado = new ArrayList<>();
	
	public ListaComentario() {
		// TODO Auto-generated constructor stub
	}

	public List<Comentario> getListado() {
		return listado;
	}

	public void setListado(List<Comentario> listado) {
		this.listado = listado;
	}
	
}