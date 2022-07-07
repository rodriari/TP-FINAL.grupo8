package ar.edu.unju.edm.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.edm.model.Usuarios;

public interface UsuarioRepository extends CrudRepository <Usuarios,Long> {

	public Optional<Usuarios> findByemail (Boolean Estado);
}
