package com.shmily.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultHttpResponseParser;
import org.apache.http.impl.conn.DefaultHttpResponseParserFactory;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.LineParser;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author lin
 * @description Most of configration copy from apache httpclient website .
 *              version = 4.5
 * @date 2015年8月20日上午8:42:48
 */
public class HttpUtil {
	// -------------------------------the follows are copy from google browser
	protected static final String Accept = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";

	protected static final String AcceptEncoding = "gzip, deflate, sdch";

	protected static final String AcceptLanguage = "zh-CN,zh;q=0.8";

	protected static final String UserAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36";
	// ---------------------------------------------------------------------
	protected static final int maxHeaderCount = 200;

	protected static final int maxLineLength = 2000;

	protected static final int maxRetry = 3;

	protected static final int maxTotal = 100;

	protected static final int defaultMaxPerRoute = 50;

	protected static final int connectionTimeout = 60000*3;

	protected static final int connectionRequestTimeout = 60000*3;

	protected static final int socketTimeout = 60000*3;

	protected static final Charset defaultCharset = Consts.UTF_8; //change the default charset like this : Charset defaultCharset = Charset.forName("GBK");
	// ---------------------------------------------------------------------
	protected static CloseableHttpClient httpclient = null;

	// When using a ResponseHandler, HttpClient will automatically take care of
	// ensuring release of the connection back to the connection manager
	// regardless whether the request execution succeeds or causes an exception.
	protected static ResponseHandler<String> rh = new ResponseHandler<String>() {

//		@Override
		public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
			// TODO Auto-generated method stub
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, defaultCharset);
			EntityUtils.consume(entity);
			return result;
		}
	};

	static {
		// config the pool httpclient managerment
		PoolingHttpClientConnectionManager phccm = new PoolingHttpClientConnectionManager(
				RegistryBuilder.<ConnectionSocketFactory> create()
						.register("http", PlainConnectionSocketFactory.INSTANCE)
						.register("https", new SSLConnectionSocketFactory(SSLContexts.createDefault())).build(),
				new ManagedHttpClientConnectionFactory(new DefaultHttpRequestWriterFactory(),
						new DefaultHttpResponseParserFactory() {

							@Override
							public HttpMessageParser<HttpResponse> create(SessionInputBuffer buffer,
									MessageConstraints constraints) {
								// TODO Auto-generated method stub
								LineParser lineParser = new BasicLineParser() {

									@Override
									public Header parseHeader(CharArrayBuffer buffer) throws ParseException {
										// TODO Auto-generated method stub
										try {
											return super.parseHeader(buffer);
										} catch (ParseException ex) {
											return new BasicHeader(buffer.toString(), null);
										}
									}

								};

								return new DefaultHttpResponseParser(buffer, lineParser,
										DefaultHttpResponseFactory.INSTANCE, constraints) {

									@Override
									protected boolean reject(CharArrayBuffer line, int count) {
										// TODO Auto-generated method stub
										return false;
									}

								};
							}

						}));

		phccm.setDefaultSocketConfig(SocketConfig.custom().setTcpNoDelay(true).build());

		phccm.setValidateAfterInactivity(1000);

		phccm.setDefaultConnectionConfig(ConnectionConfig.custom().setMalformedInputAction(CodingErrorAction.IGNORE)
				.setUnmappableInputAction(CodingErrorAction.IGNORE).setCharset(defaultCharset)
				.setMessageConstraints(MessageConstraints.custom().setMaxHeaderCount(maxHeaderCount)
						.setMaxLineLength(maxLineLength).build())
				.build());

		phccm.setMaxTotal(maxTotal);

		phccm.setDefaultMaxPerRoute(defaultMaxPerRoute);

		// constructor the httpclient object
		httpclient = HttpClients.custom().setConnectionManager(phccm).setDefaultCookieStore(new BasicCookieStore())
				.setDefaultCredentialsProvider(
						new BasicCredentialsProvider())
				.setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT)
						.setExpectContinueEnabled(true)
						.setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
						.setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
						.setConnectTimeout(connectionTimeout).setConnectionRequestTimeout(connectionRequestTimeout)
						.setSocketTimeout(socketTimeout).build())
				.setRetryHandler(new HttpRequestRetryHandler() {

					@SuppressWarnings("static-access")
//					@Override
					public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
						// TODO Auto-generated method stub
						// Do not retry if over max retry count
						if (executionCount <= maxRetry && exception instanceof ConnectTimeoutException) {
							try {
								Thread.currentThread().sleep(executionCount * 300);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return true;
						}
//						HttpClientContext clientContext = HttpClientContext.adapt(context);
//				        HttpRequest request = clientContext.getRequest();
//				        boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
//				        if (idempotent) {
//				            // Retry if the request is considered idempotent
//				            return true;
//				        }
						return false;
					}
				}).setKeepAliveStrategy(new ConnectionKeepAliveStrategy() {

//					@Override
					public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
						// TODO Auto-generated method stub
						// Honor 'keep-alive' header
						HeaderElementIterator it = new BasicHeaderElementIterator(
								response.headerIterator(HTTP.CONN_KEEP_ALIVE));
						while (it.hasNext()) {
							HeaderElement he = it.nextElement();
							String param = he.getName();
							String value = he.getValue();
							if (value != null && param.equalsIgnoreCase("timeout")) {
								try {
									return Long.parseLong(value) * 1000;
								} catch (NumberFormatException ignore) {
								}
							}
						}
						// Keep alive for 5 minutes only
						return 5 * 60 * 1000;
					}
				}).build();
	}

	protected static void setHeader(HttpUriRequest method) {
		method.setHeader("Accept", Accept);
		method.setHeader("Accept-Encoding", AcceptEncoding);
		method.setHeader("Accept-Language", AcceptLanguage);
		method.setHeader("User-Agent", UserAgent);
	}

	/**
	 * get方式调用
	 * 
	 * @param url
	 * @return 接口返回结果
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String get(String url) throws ClientProtocolException, IOException {
		HttpGet httpget = new HttpGet(url);
		setHeader(httpget);
		return httpclient.execute(httpget, rh);
	}

	/**
	 * post方式调用，类似form表单形式进行传参
	 * 
	 * @param url
	 * @param map
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String post(String url, Map<String, String> map) throws ClientProtocolException, IOException {
		HttpPost httppost = new HttpPost(url);
		if (map != null && !map.isEmpty()) {
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			for (Entry<String, String> entry : map.entrySet()) {
				formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, defaultCharset);
			httppost.setEntity(entity);
		}
		setHeader(httppost);
		return httpclient.execute(httppost, rh);
	}

	/**
	 * post方式调用，参数以字符串的形式用流写出
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String post(String url, String params) throws ClientProtocolException, IOException {
		HttpPost httppost = new HttpPost(url);
		if (StringUtils.isNotBlank(params)) {
			httppost.setEntity(new StringEntity(params, defaultCharset));
		}
		setHeader(httppost);
		return httpclient.execute(httppost, rh);
	}
}