//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.
//


package br.ufba.hupes.hospitaladmissionforram.connection;

import java.util.ArrayList;
import java.util.HashMap;
import br.ufba.hupes.hospitaladmissionforram.model.Hospital;
import br.ufba.hupes.hospitaladmissionforram.model.Research;
import br.ufba.hupes.hospitaladmissionforram.model.User;
import org.androidannotations.api.rest.RestErrorHandler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public final class RestConnection_
    implements RestConnection
{

    private RestTemplate restTemplate;
    private String rootUrl;
    private RestErrorHandler restErrorHandler;

    public RestConnection_() {
        restTemplate = new RestTemplate();
        rootUrl = "http://reacao-adversa-medicamentos.herokuapp.com";
        restTemplate.getMessageConverters().add(new MyConverter());
        restTemplate.setInterceptors(new ArrayList<ClientHttpRequestInterceptor>());
        restTemplate.getInterceptors().add(new RequestInterceptor());
    }

    @Override
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    @Override
    public Hospital[] getHospitals() {
        try {
            return restTemplate.exchange(rootUrl.concat("/hospitals"), HttpMethod.GET, null, Hospital[].class).getBody();
        } catch (RestClientException e) {
            if (restErrorHandler!= null) {
                restErrorHandler.onRestClientExceptionThrown(e);
                return null;
            } else {
                throw e;
            }
        }
    }

    @Override
    public Research[] getResearches() {
        try {
            return restTemplate.exchange(rootUrl.concat("/researches"), HttpMethod.GET, null, Research[].class).getBody();
        } catch (RestClientException e) {
            if (restErrorHandler!= null) {
                restErrorHandler.onRestClientExceptionThrown(e);
                return null;
            } else {
                throw e;
            }
        }
    }

    @Override
    public User login() {
        try {
            return restTemplate.exchange(rootUrl.concat("/users/show"), HttpMethod.GET, null, User.class).getBody();
        } catch (RestClientException e) {
            if (restErrorHandler!= null) {
                restErrorHandler.onRestClientExceptionThrown(e);
                return null;
            } else {
                throw e;
            }
        }
    }

    @Override
    public void newResearch(Research research) {
        HttpEntity<Research> requestEntity = new HttpEntity<Research>(research);
        try {
            restTemplate.exchange(rootUrl.concat("/researches"), HttpMethod.POST, requestEntity, null);
        } catch (RestClientException e) {
            if (restErrorHandler!= null) {
                restErrorHandler.onRestClientExceptionThrown(e);
            } else {
                throw e;
            }
        }
    }

    @Override
    public void updateResearch(String id, Research research) {
        HttpEntity<Research> requestEntity = new HttpEntity<Research>(research);
        HashMap<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("id", id);
        try {
            restTemplate.exchange(rootUrl.concat("/researches/{id}"), HttpMethod.PUT, requestEntity, null, urlVariables);
        } catch (RestClientException e) {
            if (restErrorHandler!= null) {
                restErrorHandler.onRestClientExceptionThrown(e);
            } else {
                throw e;
            }
        }
    }

}
