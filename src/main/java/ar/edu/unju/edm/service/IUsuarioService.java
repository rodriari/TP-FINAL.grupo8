package ar.edu.unju.edm.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Usuarios;

@Service
public interface IUsuarioService {
	public void guardarUsuario(@Valid Usuarios usuarioparaguardar);
	public void modificarUsuario(Usuarios usuario);
	public void eliminarUsuario(Long dni) throws Exception;
	public List<Usuarios> listarUsuarios();
	public Usuarios buscarUsuario(Long dni) throws Exception;
}