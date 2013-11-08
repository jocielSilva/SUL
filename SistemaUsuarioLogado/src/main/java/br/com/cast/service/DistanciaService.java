package br.com.cast.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;

import javax.xml.ws.WebServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.entidades.Cidade;
import br.com.cast.entidades.Usuario;
import br.com.cast.to.DistanciaTO;
import br.com.cast.to.EnderecoTO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class DistanciaService implements Serializable {

	private static final long serialVersionUID = 1465712083543021816L;
	
	@Autowired
	private ContatoService serviceContato;
	
	public EnderecoTO recuperarEndereco(Integer cep){
		Gson gson = new Gson();
		String json = preparaEndereco("http://cep.republicavirtual.com.br/web_cep.php?cep="+cep+"&formato=json");
		return gson.fromJson(json, new TypeToken<EnderecoTO>() {
		}.getType());
	}
	
	public String preparaEndereco(String caminho){
		try {
			URL url = new URL(caminho);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String result;
			String json ="";
			while ((result = br.readLine()) != null) {
				json = json+result;
			}
			br.close();
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebServiceException(e.getMessage());
		}
	}
	
	public DistanciaTO calcularDistanciaWebService(Usuario usuarioGrid, Usuario usuarioSessao){
		Cidade cidadeUsuarioGrid = serviceContato.recuperarContatoUsuario(usuarioGrid).getCidade();
		Cidade cidadeUsuarioSessao = serviceContato.recuperarContatoUsuario(usuarioSessao).getCidade();
		Gson gson = new Gson();
		String json = preparaEndereco("http://localhost:8081/WebServiceCalcularDistancia/jersey/distancia/calcular/"+ cidadeUsuarioGrid.getLatitude() +"/"+ cidadeUsuarioGrid.getLongitude() +"/"+ cidadeUsuarioSessao.getLatitude() +"/"+cidadeUsuarioSessao.getLongitude());
		return gson.fromJson(json, new TypeToken<DistanciaTO>() {
		}.getType());
	}

}
