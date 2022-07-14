package ar.edu.unju.edm.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ar.edu.unju.edm.model.Comentario;

@Service
public interface ComentarioService {
	public Comentario nuevocomentario();
	public void guardarComentario(Comentario comentario);
	public List<Comentario> mostrarcomentario();
}