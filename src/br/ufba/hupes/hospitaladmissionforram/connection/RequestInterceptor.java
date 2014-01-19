package br.ufba.hupes.hospitaladmissionforram.connection;

import java.io.IOException;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import br.ufba.hupes.hospitaladmissionforram.MainApp;

@EBean(scope = Scope.Singleton)
public class RequestInterceptor implements ClientHttpRequestInterceptor {
	
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    	 HttpHeaders headers = request.getHeaders();
         HttpAuthentication auth = new HttpBasicAuthentication(getLogin(), getPass());
         headers.setAuthorization(auth);
         return execution.execute(request, body);
    }

	public String getLogin() {
		return MainApp.getInstance().getUser().login().get();
	}

	public void setLogin(String login) {
		MainApp.getInstance().getUser().login().put(login);
	}

	public String getPass() {
		return MainApp.getInstance().getUser().pass().get();
	}

	public void setPass(String pass) {
		MainApp.getInstance().getUser().pass().put(pass);
	}
}