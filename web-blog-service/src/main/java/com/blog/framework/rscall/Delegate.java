package com.blog.framework.rscall;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.dubbo.common.json.JSON;
import com.blog.entity.ServiceEntity;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

/**
 * 远程接口的调度方，每个接口对应一个委托实例，实现接口调用
 * 如果一个项目中有太多的三方对接，那么用该接口可以统一调用
 * @author xin
 *
 */
public class Delegate {

	private static final MediaType JSON_CONTENT_TYPE = MediaType.parse("application/json");
	
	private static final MediaType TEXT_CONTENT_TYPE = MediaType.parse("text/plain");
	
	private static final MediaType XML_CONTENT_TYPE = MediaType.parse("application/xml");
	
	private static final OkHttpClient client;
	
	private static final OkHttpClient gzipClient;
	
	private ServiceEntity serviceEntity;
	
	private String errorMessage;
	
	private boolean isAvailable = true;
	
	protected final static ConcurrentHashMap<String, RequestHandle> requestHandles = new ConcurrentHashMap<>();
	
	protected final static ConcurrentHashMap<String, ResponseExtractor> responseExtractors = new ConcurrentHashMap<>();
	
	static {
		Builder clientBuilder = new Builder()
				.connectTimeout(60, TimeUnit.SECONDS)//设置连接超时时间
				.readTimeout(90, TimeUnit.SECONDS)//设置读取超时
				.writeTimeout(60, TimeUnit.SECONDS)//设置写入超时
				.retryOnConnectionFailure(true);//是否自动重连
		
		try {
			X509TrustManager trustManager = new MyX509TrustManager();
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, new TrustManager[] {
					trustManager}, new SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			clientBuilder.sslSocketFactory(ssf, trustManager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		client = clientBuilder.build();
		gzipClient = client.newBuilder().addInterceptor(new GzipRequestInterceptor()).build();
	}
	
	public Delegate (ServiceEntity serviceEntity) {
		this.serviceEntity = serviceEntity;
	}
	
	void init() {
		String requestHandle = serviceEntity.getRequestHandle();
		
		if (null != requestHandle && !"".equals(requestHandle)) {
			try {
				Class<?> clazz = ClassUtils.forName(requestHandle, ClassUtils.getDefaultClassLoader());
				RequestHandle instance = (RequestHandle) BeanUtils.instantiate(clazz);
				requestHandles.put(requestHandle, instance);
			} catch (ClassNotFoundException | LinkageError e) {
				errorMessage = String.format("接口【%s】初始化requestHandle出错，接口无法使用，请重新配置....", requestHandle);
				isAvailable = false;
				return;
			}
		}
		
		String responseExtractorHandle = serviceEntity.getResponseExtractorHandle();
		if (null != responseExtractorHandle && !"".equals(responseExtractorHandle)) {
			try {
				Class<?> clazz = ClassUtils.forName(responseExtractorHandle, ClassUtils.getDefaultClassLoader());
				ResponseExtractor instance = (ResponseExtractor)BeanUtils.instantiate(clazz);
				responseExtractors.put(responseExtractorHandle, instance);
			} catch (ClassNotFoundException | LinkageError e) {
                errorMessage = String.format("接口【%s】初始化responseExtractorHandle出错, 接口无法使用, 请重新配置...", responseExtractorHandle);
                isAvailable = false;
            } catch (Exception e) {
                errorMessage = String.format("接口【%s】初始化responseExtractorHandle出错, 接口无法使用, 请重新配置...", responseExtractorHandle);
                isAvailable = false;
            }
		}
	}
	
	public String getServiceName () {
		return serviceEntity.getName();
	}
	
	public boolean isAvailable () {
		return isAvailable;
	}
	
	public String getErrorMessage () {
		return errorMessage;
	}
	
	public <T> T invoke (Map<String, ?> parms, Class<T> clazz) throws RcCallException {
		if (!isAvailable) {
			throw new RcCallException(errorMessage);
		}
		
		okhttp3.Request.Builder builder = new Request.Builder().cacheControl(CacheControl.FORCE_NETWORK);
		if (serviceEntity.getRequestMethod().equals(RequestMethod.POST)) {
			
			RequestBody body = null;
			switch (serviceEntity.getRequestType()) {
			case file:
				break;
			case json:
				break;
			case xml:
				break;
			case text:
				break;
			default:
				break;
			}
			
			builder.url(serviceEntity.getUrl(parms)).post(body);
		} else {
			String queryString = buildUrlQueryString(parms);
			String url = serviceEntity.getUrl(parms);
			if (url.lastIndexOf("?") != -1) {
				builder.url(url + "&" + queryString);
			} else {
				builder.url(url + "?" + queryString);
			}
		}
		
		OkHttpClient runClient = client;
		if (null != serviceEntity.getIsZip() && serviceEntity.getIsZip()) {
			runClient = gzipClient;
		}
		
		Request request = builder.build();
		try {
			
			Response response = runClient.newCall(request).execute();
			if (response.isSuccessful()) {
				String result = response.body().string();
				switch (serviceEntity.getResultType()) {
				case json:
					ResponseExtractor<T> responseExtractor = (ResponseExtractor<T>)responseExtractors.get(serviceEntity.getResponseExtractorHandle());
					if (null != responseExtractor) {
						return responseExtractor.extraData(serviceEntity.getExtraParams(), result, clazz, 1);
					} else {
						try {
							return JSON.parse(result, clazz);
						} catch (Exception e) {
							throw new RcCallException(e);
						}
					}
				case body:
					break;
				}
				
				return null;
			} else {
				throw new RcCallException("");
			}
		} catch (IOException e) {
			
		}
		return null;
	}
	
	/**
	 * 构建GET 的url
	 * @return
	 */
	protected String buildUrlQueryString (Map<String, ?> parmeters) {
		LinkedHashMap<String, Object> newParms = serviceEntity.buildParmeterMap(parmeters);
		RequestHandle<?> requestHandle = requestHandles.get(serviceEntity.getRequestHandle());
		if (null != requestHandle) {
			requestHandle.buildExtraParam(newParms);
		}
		
		StringBuilder ret = new StringBuilder();
		for (String key : newParms.keySet()) {
			ret.append(percentDecode(key, true)).append("=");
		}
		return ret.substring(0, ret.length() - 1);
	}
	
	static String percentDecode(String encode, boolean plusIsSpace) {
		return percentDecode(encode, 0, encode.length(), plusIsSpace);
	}
	
	static String percentDecode (String encode, int pos, int limit, boolean plusIsSpace) {
		for (int i = pos; i < limit; i++) {
			char c = encode.charAt(i);
			if (c == '%' || (c == '+' && plusIsSpace)) {
				Buffer out = new Buffer();
				out.writeUtf8(encode, pos, i);
				percentDecode(out, encode, i, limit, plusIsSpace);
				return out.readUtf8();
			}
		}
		
		return encode.substring(pos, limit);
	}
	
	static void percentDecode (Buffer out, String encoded, int pos, int limit, boolean plusIsSpace) {
		int codePoint;
		for (int i = pos; i < limit; i += Character.charCount(codePoint)) {
			codePoint = encoded.codePointAt(i);
			if (codePoint == '%' && i + 2 < limit) {
				int d1 = decodeHexDigit(encoded.charAt(i + 1));
				int d2 = decodeHexDigit(encoded.charAt(i + 2));
				if (d1 != -1 && d2 != -1) {
					out.writeByte((d1 << 4) + d2);
					i += 2;
					continue;
				}
			} else if (codePoint == '+' && plusIsSpace){
				out.writeByte(' ');
				continue;
			}
			out.writeUtf8CodePoint(codePoint);
		}
	}
	
	static int decodeHexDigit(char c) {
		if (c >= '0' && c <= '9') {
			return c - '0';
		}
		if (c >= 'a' && c <= 'f') {
			return c - 'a' + 10;
		}
		if (c >= 'A' && c <= 'F') {
			return c - 'A' + 10;
		}
		return -1;
	}
	
	/**
	 * 信任所有证书
	 * @author xin
	 *
	 */
	private static class MyX509TrustManager implements X509TrustManager {

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) 
				throws CertificateException {
			
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) 
				throws CertificateException {
			
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}
	}
	
	static class GzipRequestInterceptor implements Interceptor {

		@Override
		public Response intercept(Chain chain) throws IOException {
			Request originalRequest = chain.request();
			if (originalRequest.body() == null || 
					originalRequest.header("Content-Encoding") != null) {
				return chain.proceed(originalRequest);
			}
			
			Request compressedRequest = originalRequest.newBuilder()
					.header("Content-Encoding", "gzip")
					.method(originalRequest.method(), gzip(originalRequest.body())).build();
			return chain.proceed(compressedRequest);
		}
		
		private RequestBody gzip(final RequestBody body) {
			return new RequestBody() {
				
				@Override
				public void writeTo(BufferedSink sink) throws IOException {
					BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
					body.writeTo(gzipSink);
					gzipSink.close();
				}
				
				@Override
				public MediaType contentType() {
					return body.contentType();
				}
				
				@Override
				public long contentLength() {
					return -1;
				}
			};
		}
	}
}
