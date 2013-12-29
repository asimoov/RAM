package br.ufba.hupes.hospitaladmissionforram.connection;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.web.client.RestTemplate;

import br.ufba.hupes.hospitaladmissionforram.model.Hospital;
import br.ufba.hupes.hospitaladmissionforram.model.Research;

@Rest(rootUrl = "http://reacao-adversa-medicamentos.herokuapp.com", interceptors = { RequestInterceptor.class }, converters = { MyConverter.class })
public interface RestConnection {

	RestTemplate getRestTemplate();

	@Get("/hospitals")
	Hospital[] getHospitals();

	@Post("/researches")
	String newResearch(Research research);
}