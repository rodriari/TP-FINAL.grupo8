package ar.edu.unju.edm.service;

import java.util.List;


import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Boleto;
@Service
public interface IBoletoService {
	public Boleto nuevoUsuarioCine();
	public void guardarUsuarioCine(Boleto usuariocine);
	public void eliminarUsuarioCine(Long id) throws Exception;
	public void modificarUsuarioCine(Boleto usuariocine);
	public List<Boleto> listadoUsuariosCine(); 
	public Boleto buscarUsuarioCine(Long id) throws Exception; 

}
