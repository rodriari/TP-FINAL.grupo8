package ar.edu.unju.edm.service.imp;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Boleto;
import ar.edu.unju.edm.repository.BoletoRepository;
import ar.edu.unju.edm.service.IBoletoService;

@Service
public class IBoletoImp implements IBoletoService{
	@Autowired
	Boleto boleto;
	
	@Autowired 
	BoletoRepository boletorepository;

		@Override
		public Boleto nuevoUsuarioCine() {
			// TODO Auto-generated method stub
			return boleto;
		}

		@Override
		public void guardarUsuarioCine(Boleto usuariocine) {
			// TODO Auto-generated method stub
			boletorepository.save (usuariocine);
		}

		@Override
		public void eliminarUsuarioCine(Integer id) throws Exception {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void modificarUsuarioCine(Boleto usuariocine) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Boleto> listadoUsuariosCine() {
			// TODO Auto-generated method stub
			return (List<Boleto>) boletorepository.findAll();
		}

		@Override
		public Boleto buscarUsuarioCine(Integer id) throws Exception {
			// TODO Auto-generated method stub
			return boletorepository.findById(id).orElseThrow(()-> new Exception("UsuarioCine no encontrado"));
		}
}
		
