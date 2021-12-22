package com.bliscosque.samplemc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bliscosque.samplemc.domain.Pedido;
import com.bliscosque.samplemc.repositories.PedidoRepository;
import com.bliscosque.samplemc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado! Id:" + id + ", Tipo: " + Pedido.class.getName()));
	}

}
