package br.ufba.hupes.hospitaladmissionforram.connection;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Put;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.web.client.RestTemplate;

import br.ufba.hupes.hospitaladmissionforram.model.Hospital;
import br.ufba.hupes.hospitaladmissionforram.model.Research;
import br.ufba.hupes.hospitaladmissionforram.model.User;

@Rest(rootUrl = "http://reacao-adversa-medicamentos.herokuapp.com", interceptors = { RequestInterceptor.class }, converters = { MyConverter.class })
//@Rest(rootUrl = "http://192.168.0.10:3000/", interceptors = { RequestInterceptor.class }, converters = { MyConverter.class })
public interface RestConnection {
 
	RestTemplate getRestTemplate();

	@Get("/hospitals")
	Hospital[] getHospitals();

	@Post("/researches")
	void newResearch(Research research);
	
	@Put("/researches/{id}")
	void updateResearch(String id, Research research);

	@Get("/researches")
	Research[] getResearches();

	@Get("/users/show")
	User login();
}