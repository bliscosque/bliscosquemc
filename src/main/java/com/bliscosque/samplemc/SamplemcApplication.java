package com.bliscosque.samplemc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bliscosque.samplemc.domain.Categoria;
import com.bliscosque.samplemc.domain.Cidade;
import com.bliscosque.samplemc.domain.Cliente;
import com.bliscosque.samplemc.domain.Endereco;
import com.bliscosque.samplemc.domain.Estado;
import com.bliscosque.samplemc.domain.Produto;
import com.bliscosque.samplemc.domain.enums.TipoCliente;
import com.bliscosque.samplemc.repositories.CategoriaRepository;
import com.bliscosque.samplemc.repositories.CidadeRepository;
import com.bliscosque.samplemc.repositories.ClienteRepository;
import com.bliscosque.samplemc.repositories.EnderecoRepository;
import com.bliscosque.samplemc.repositories.EstadoRepository;
import com.bliscosque.samplemc.repositories.ProdutoRepository;

@SpringBootApplication
public class SamplemcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SamplemcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1= new Estado(null, "Minas Gerais");
		Estado est2= new Estado(null, "Sao Paulo");
		
		Cidade c1= new Cidade(null, "Uberlandia", est1);
		Cidade c2= new Cidade(null, "Sao Paulo", est2);
		Cidade c3= new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria", "mari@gmail.com", "456123789", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("551111", "552222"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "ap 1", "Jardim", "38888", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Matos", "105", "s20", "Centro", "9999", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
	}

}
