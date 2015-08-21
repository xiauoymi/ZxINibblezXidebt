package com.nibbledebt.intuit.cad.interceptor;

import com.nibbledebt.intuit.cad.core.Context;
import com.nibbledebt.intuit.cad.core.IAuthorizer;
import com.nibbledebt.intuit.cad.core.MethodType;
import com.nibbledebt.intuit.cad.exception.AggCatException;
import com.nibbledebt.intuit.cad.exception.ConfigurationException;
import com.nibbledebt.intuit.cad.retry.IntuitRetryPolicyHandler;
import com.nibbledebt.intuit.cad.util.Config;
import com.nibbledebt.intuit.cad.util.CopyInputStream;
import com.nibbledebt.intuit.cad.util.PropertyHelper;
import com.nibbledebt.intuit.cad.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyStore;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;

public class ConnectionInterceptor implements Interceptor {
	private static final org.slf4j.Logger LOG = com.nibbledebt.intuit.cad.util.Logger
			.getLogger();
	private static final int PORT_443 = 443;

	public void execute(IntuitMessage intuitMessage) throws AggCatException {
		LOG.debug("Enter ConnectionInterceptor...");

		DefaultHttpClient client = new DefaultHttpClient();

		RequestElements intuitRequest = intuitMessage.getRequestElements();

		IntuitRetryPolicyHandler handler = getRetryHandler();
		client.setHttpRequestRetryHandler(handler);

		HttpHost proxy = getProxy();

		if (proxy != null) {
			client.getParams().setParameter("http.route.default-proxy", proxy);
			Scheme sch = getSSLScheme();
			if (sch != null) {
				client.getConnectionManager().getSchemeRegistry().register(sch);
			}
		}

		URI uri = null;
		try {
			uri = new URI((String) intuitRequest.getRequestParameters().get(
					"resource-url"));
		} catch (URISyntaxException e) {
			throw new AggCatException("URISyntaxException", e);
		}

		String methodType = (String) intuitRequest.getRequestParameters().get(
				"method-type");
		HttpRequestBase httpRequest = getHttpRequestBase(methodType, uri,
				intuitRequest.getSerializedData());

		populateRequestHeaders(httpRequest, intuitRequest.getRequestHeaders());

		authorizeRequest(intuitRequest.getContext(), httpRequest);

		LOG.debug("Request URI : " + uri);
		LOG.debug("Http Method : " + methodType);
		LOG.debug("Request XML : " + intuitRequest.getSerializedData());

		HttpResponse httpResponse = null;
		try {
			HttpHost target = new HttpHost(uri.getHost(), -1, uri.getScheme());
			httpResponse = client.execute(target, httpRequest);
		} catch (ClientProtocolException e) {
			throw new ConfigurationException(
					"Error in Http Protocol definition", e);
		} catch (IOException e) {
			throw new AggCatException(e);
		}

		LOG.debug("Connection status : " + httpResponse.getStatusLine());

		CopyInputStream copyInputStream = null;
		try {
			copyInputStream = new CopyInputStream(httpResponse.getEntity()
					.getContent());
			LOG.debug("Response XML : "
					+ StringUtils.getContent(copyInputStream.getCopy()));
		} catch (IOException e) {
			LOG.error(
					"IOException in while reading content from HttpResponse.",
					e);
			throw new AggCatException(e);
		}

		ResponseElements responseElements = intuitMessage.getResponseElements();
		responseElements.setHttpResponse(httpResponse);
		responseElements.setResponseStream(copyInputStream.getCopy());
		LOG.debug("Exit ConnectionInterceptor.");
	}

	private void populateRequestHeaders(HttpRequestBase httpRequest,
			Map<String, String> requestHeaders) {
		Set keySet = requestHeaders.keySet();
		Iterator keySetIterator = keySet.iterator();
		while (keySetIterator.hasNext()) {
			String key = (String) keySetIterator.next();
			String value = (String) requestHeaders.get(key);
			httpRequest.addHeader(key, value);
		}

		PropertyHelper propertyHelper = PropertyHelper.getInstance();
		String requestSource = propertyHelper.getRequestSource()
				+ propertyHelper.getVersion();
		httpRequest.addHeader(propertyHelper.getRequestSourceHeader(),
				requestSource);
	}

	private void authorizeRequest(Context context, HttpRequestBase httpRequest)
			throws AggCatException {
		context.getAuthorizer().authorize(httpRequest);
	}

	private IntuitRetryPolicyHandler getRetryHandler() throws AggCatException {
		IntuitRetryPolicyHandler handler = null;
		String policy = Config.getProperty("retry.mode");
		if (policy.equalsIgnoreCase("fixed")) {
			String retryCountStr = Config.getProperty("retry.fixed.count");
			String retryIntervalStr = Config
					.getProperty("retry.fixed.interval");
			try {
				handler = new IntuitRetryPolicyHandler(
						Integer.parseInt(retryCountStr),
						Integer.parseInt(retryIntervalStr));
			} catch (NumberFormatException e) {
				throw new ConfigurationException(e);
			}
		} else if (policy.equalsIgnoreCase("incremental")) {
			String retryCountStr = Config
					.getProperty("retry.incremental.count");
			String retryIntervalStr = Config
					.getProperty("retry.incremental.interval");
			String retryIncrementStr = Config
					.getProperty("retry.incremental.increment");
			try {
				handler = new IntuitRetryPolicyHandler(
						Integer.parseInt(retryCountStr),
						Integer.parseInt(retryIntervalStr),
						Integer.parseInt(retryIncrementStr));
			} catch (NumberFormatException e) {
				throw new ConfigurationException(e);
			}
		} else if (policy.equalsIgnoreCase("exponential")) {
			String retryCountStr = Config
					.getProperty("retry.exponential.count");
			String minBackoffStr = Config
					.getProperty("retry.exponential.minBackoff");
			String maxBackoffStr = Config
					.getProperty("retry.exponential.maxBackoff");
			String deltaBackoffStr = Config
					.getProperty("retry.exponential.deltaBackoff");
			try {
				handler = new IntuitRetryPolicyHandler(
						Integer.parseInt(retryCountStr),
						Integer.parseInt(minBackoffStr),
						Integer.parseInt(maxBackoffStr),
						Integer.parseInt(deltaBackoffStr));
			} catch (NumberFormatException e) {
				throw new ConfigurationException(e);
			}
		}

		return handler;
	}

	public Scheme getSSLScheme() throws AggCatException {
		String path = Config.getProperty("proxy.keystore.path");
		String pass = Config.getProperty("proxy.keystore.password");
		Scheme scheme = null;
		FileInputStream instream = null;
		KeyStore trustStore = null;
		SSLSocketFactory factory = null;

		if ((path != null) && (pass != null)) {
			try {
				trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
				instream = new FileInputStream(new File(path));
				trustStore.load(instream, pass.toCharArray());
				factory = new SSLSocketFactory(trustStore);
				scheme = new Scheme("https", 443, factory);
			} catch (Exception e) {
				LOG.error("Exception while getting the SSL Scheme", e);
				throw new AggCatException(e);
			} finally {
				try {
					instream.close();
				} catch (IOException e) {
					LOG.error("IOException", e);
					throw new AggCatException(e);
				}
			}
		}
		return scheme;
	}

	public HttpHost getProxy() {
		String host = Config.getProperty("proxy.host");
		String portStr = Config.getProperty("proxy.port");
		HttpHost proxy = null;

		if ((host != null) && (portStr != null)) {
			int port = Integer.parseInt(portStr);
			proxy = new HttpHost(host, port);
		}
		return proxy;
	}

	private HttpRequestBase getHttpRequestBase(String methodType, URI uri,
			String serializedData) throws AggCatException {
		HttpRequestBase httpRequest = null;
		if (StringUtils.hasText(methodType)) {
			if (methodType.equals(MethodType.GET.toString())) {
				httpRequest = new HttpGet(uri);
			} else if (methodType.equals(MethodType.POST.toString())) {
				httpRequest = new HttpPost(uri);
				if (serializedData != null) {
					HttpEntity entity;
					try {
						entity = new StringEntity(serializedData);
					} catch (UnsupportedEncodingException e) {
						throw new AggCatException(
								"UnsupportedEncodingException", e);
					}
					((HttpPost) httpRequest).setEntity(entity);
				}
			} else if (methodType.equals(MethodType.PUT.toString())) {
				httpRequest = new HttpPut(uri);
				if (serializedData != null) {
					HttpEntity entity;
					try {
						entity = new StringEntity(serializedData);
					} catch (UnsupportedEncodingException e) {
						throw new AggCatException(
								"UnsupportedEncodingException", e);
					}
					((HttpPut) httpRequest).setEntity(entity);
				}
			} else if (methodType.equals(MethodType.DELETE.toString())) {
				httpRequest = new HttpDelete(uri);
			}
		}

		return httpRequest;
	}
}