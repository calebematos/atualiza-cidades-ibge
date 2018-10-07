package br.com.calebematos.api.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.calebematos.api.model.Cidade;
import br.com.calebematos.api.model.Estado;
import br.com.calebematos.api.model.ibge.Municipio;
import br.com.calebematos.api.model.ibge.Uf;
import br.com.calebematos.api.repository.CidadeRepository;
import br.com.calebematos.api.repository.EstadoRepository;

@Service
public class BuscaDadosDoIbge {

	@Autowired
	private ComunicacaoIbge comunicacaoIbge;

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;

//	@PostConstruct
	private void buscarEAtualizarEstados() {
		List<Uf> ufs = comunicacaoIbge.buscarEstados();

		List<Estado> estados = new ArrayList<>();

		for (Uf uf : ufs) {
			Estado estado = new Estado(uf.getId(), uf.getNome());
			estados.add(estado);
		}

		estadoRepository.saveAll(estados);
	}

	@PostConstruct
	private void buscarEAtualizarCidades() {
		List<Municipio> municipios = comunicacaoIbge.buscarCidades();
		List<Cidade> cidades = new ArrayList<>();

		for (Municipio municipio : municipios) {
			Cidade cidade = new Cidade();
			cidade.setCodigo(municipio.getId());
			cidade.setNome(municipio.getNome());
			cidade.setEstado(new Estado(municipio.getMicrorregiao().getMesorregiao().getUF().getId()));
			
			cidades.add(cidade);
		}
		
		cidadeRepository.saveAll(cidades);
	}
}
