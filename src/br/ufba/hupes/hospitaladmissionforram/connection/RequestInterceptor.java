package br.ufba.hupes.hospitaladmissionforram.connection;

import java.io.IOException;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;
import org.androidannotations.annotations.RootContext;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import android.content.Context;

@EBean(scope = Scope.Singleton)
public class RequestInterceptor implements ClientHttpRequestInterceptor {
	
	@RootContext
	Context context;
	
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    	 HttpHeaders headers = request.getHeaders();
         HttpAuthentication auth = new HttpBasicAuthentication("awe@awe.com", "12345678");
         headers.setAuthorization(auth);
         return execution.execute(request, body);
    }
}