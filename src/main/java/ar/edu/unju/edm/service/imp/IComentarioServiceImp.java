package ar.edu.unju.edm.service.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unju.edm.model.Comentario;
import ar.edu.unju.edm.repository.ComentarioRepository;
import ar.edu.unju.edm.service.ComentarioService;

@Service
public class IComentarioServiceImp implements ComentarioService{
    @Autowired 
    Comentario comentario;
    @Autowired
    ComentarioRepository comentarioRepository; 
	@Override
	public Comentario nuevocomentario() {
		// TODO Auto-generated method stub
		return comentario;
	}

	@Override
	public void guardarComentario(Comentario comentario) {
		// TODO Auto-generated method stub
		comentarioRepository.save(comentario);
	}

	@Override
	public List<Comentario> mostrarcomentario() {
		// TODO Auto-generated method stub
		return (List<Comentario>) comentarioRepository.findAll();
	}

}