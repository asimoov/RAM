package br.ufba.hupes.hospitaladmissionforram.connection;

import java.io.IOException;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import com.google.gson.GsonBuilder;

public class MyConverter extends GsonHttpMessageConverter {

	public MyConverter() {
		setGson(new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create());
	}
	
	@Override
	protected void writeInternal(Object arg0, HttpOutputMessage arg1)
			throws IOException, HttpMessageNotWritableException {
		super.writeInternal(arg0, arg1);
	}
	
	@Override
	protected Object readInternal(Class<? extends Object> arg0, HttpInputMessage arg1) throws IOException, HttpMessageNotReadableException {
		Object readInternal = super.readInternal(arg0, arg1);
		return readInternal;
	}

}
