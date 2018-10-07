package br.com.calebematos.api.service;

import java.util.List;

import org.slf4j.Logger;
import java.lang.reflect.Type;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.calebematos.api.model.ibge.Municipio;
import br.com.calebematos.api.model.ibge.Uf;

@Service
public class ComunicacaoIbge {

	private final Logger LOGGER = LoggerFactory.getLogger(ComunicacaoIbge.class.getSimpleName());

	private RestTemplate restTemplate;
	private HttpHeaders headers;

	public ComunicacaoIbge() {
		this.restTemplate = new RestTemplate();
		this.headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "*/*");
	}

	public List<Municipio> buscarCidades() {
		LOGGER.info("Buscado cidades do IBGE");
		String url = "https://servicodados.ibge.gov.br/api/v1/localidades/municipios";

		Type listType = new TypeToken<List<Municipio>>() {
		}.getType();
		HttpEntity<String> entity = new HttpEntity<>(headers);

		try {
			ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			List<Municipio> municipios = new Gson().fromJson(exchange.getBody(), listType);
			return municipios;
		} catch (RestClientException e) {
			throw new RestClientException("Falha ao comunicar com IBGE - " + e.getMessage());
		}
	}

	public List<Uf> buscarEstados() {
		LOGGER.info("Buscado estados do IBGE");
		String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";

		Type listType = new TypeToken<List<Uf>>() {
		}.getType();

		HttpEntity<String> entity = new HttpEntity<>(headers);

		try {
			ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			List<Uf> estados = new Gson().fromJson(exchange.getBody(), listType);
			return estados;
		} catch (RestClientException e) {
			throw new RestClientException("Falha ao comunicar com IBGE - " + e.getMessage());
		}
	}
}
