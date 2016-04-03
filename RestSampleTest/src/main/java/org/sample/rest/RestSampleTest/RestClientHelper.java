package org.sample.rest.RestSampleTest;


import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.lumeris.innovation.core.logging.Logger;
import com.lumeris.innovation.core.models.ValueResult;
import com.lumeris.innovation.core.rest.CertificateTrustManager;
import com.lumeris.innovation.core.rest.ResponseErrorHandler;
import com.lumeris.innovation.core.rest.RestHelper;
import com.lumeris.innovation.core.rest.RestJsonFault; 





public class RestClientHelper {
	
	 // region " Private Declarations "
    private String accessToken;
    private Map<String, String> additionalHeaders;
    private Map<String, Object> additionalParameters;
    private String contentType = "application/json";
    private ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private String refreshToken;
    private RestTemplate restTemplate = new RestTemplate();
    private String serviceUri;
    // endregion
    // region " Public Properties "
    /**
     * the access token used by the rest client
     *
     * @return the access token used by the rest client
     */
    public String getAccessToken() {
        return accessToken;
    }
    /**
     * set the access token
     *
     * @param value the access token to set
     */
    public void setAccessToken(String value) {
        accessToken = value;
    }
    /**
     * custom headers
     *
     * @return the custom headers
     */
    public Map<String, String> getAdditionalHeaders() {
        return additionalHeaders;
    }
    /**
     * set the additional headers
     *
     * @param value the additional headers to set
     */
    public void setAdditionalHeaders(Map<String, String> value) {
        additionalHeaders = value;
    }
    /**
     * the custom parameters
     *
     * @return the custom parameters
     */
    public Map<String, Object> getAdditionalParameters() {
        return additionalParameters;
    }
    /**
     * set additional parameters
     *
     * @param value the additional parameters to set
     */
    public void setAdditionalParameters(Map<String, Object> value) {
        additionalParameters = value;
    }
    /**
     * the content type
     *
     * @return the content type
     */
    public String getContentType() {
        return contentType;
    }
    /**
     * set the content type
     *
     * @param value the content type to set
     */
    public void setContentType(String value) {
        contentType = value;
    }
    /**
     * the refresh token
     *
     * @return the refresh token
     */
    public String getRefreshToken() {
        return refreshToken;
    }
    /**
     * set the refresh token
     *
     * @param value the refresh token to set
     */
    public void setRefreshToken(String value) {
        refreshToken = value;
    }
    /**
     * the service uri
     *
     * @return the service uri
     */
    public String getServiceUri() {
        return serviceUri;
    }
    /**
     * set the service uri
     *
     * @param value the service uri to set
     */
    public void setServiceUri(String value) {
        serviceUri = StringUtils.trimToEmpty(value);
    }
    // endregion
    // region " Constructors "
    /**
     * create a new rest client helper with the specified parameters
     *
     * @param serviceUri the service uri
     */
    public RestClientHelper(String serviceUri) {
        this(serviceUri, null, null, "application/json", null, null);
    }
    /**
     * create a new rest client helper with the specified parameters
     *
     * @param serviceUri the service uri
     * @param accessToken the access token
     */
    public RestClientHelper(String serviceUri, String accessToken) {
        this(serviceUri, accessToken, null, "application/json", null, null);
    }
    /**
     * create a new rest client helper with the specified parameters
     *
     * @param serviceUri the service uri
     * @param accessToken the access token
     * @param refreshToken the refresh token
     */
    public RestClientHelper(
            String serviceUri,
            String accessToken,
            String refreshToken) {
        this(serviceUri, accessToken, refreshToken, "application/json", null, null);
    }
    /**
     * create a new rest client helper with the specified parameters
     *
     * @param serviceUri the service uri
     * @param accessToken the access token
     * @param refreshToken the refresh token
     * @param contentType the content type
     */
    public RestClientHelper(
            String serviceUri,
            String accessToken,
            String refreshToken,
            String contentType) {
        this(serviceUri, accessToken, refreshToken, contentType, null, null);
    }
    /**
     * create a new rest client helper with the specified parameters
     *
     * @param serviceUri the service uri
     * @param accessToken the access token
     * @param refreshToken the refresh token
     * @param contentType the content type
     * @param additionalHeaders the additional headers
     */
    public RestClientHelper(
            String serviceUri,
            String accessToken,
            String refreshToken,
            String contentType,
            Map<String, String> additionalHeaders) {
        this(serviceUri, accessToken, refreshToken, contentType, additionalHeaders, null);
    }
    /**
     * create a new rest client helper with the specified parameters
     *
     * @param serviceUri the service uri
     * @param accessToken the access token
     * @param refreshToken the refresh token
     * @param contentType the content type
     * @param additionalHeaders the additional headers
     * @param additionalParameters the additional parameters
     */
    public RestClientHelper(
            String serviceUri,
            String accessToken,
            String refreshToken,
            String contentType,
            Map<String, String> additionalHeaders,
            Map<String, Object> additionalParameters) {
        setAccessToken(accessToken);
        setAdditionalHeaders(additionalHeaders);
        setAdditionalParameters(additionalParameters);
        setContentType(contentType);
        setRefreshToken(refreshToken);
        setServiceUri(serviceUri);
        restTemplate.setErrorHandler(new ResponseErrorHandler());
    }
    // endregion
    // region " Public Methods "
    /**
     * execute a DELETE request against the target service
     *
     * @param resource the resource string, ex. '/content'
     * @param responseType the type of the response object
     * @param <T> the response type
     * @return the cast operation result
     * @throws Exception
     */
    public <T> T delete(String resource, Class<T> responseType) throws Exception {
        RequestEntity<?> request = buildRequestEntity(resource, null, HttpMethod.DELETE);
        return executeRestRequest(request, responseType);
    }
    /**
     * execute a DELETE request against the target service
     *
     * @param resource the resource string, ex. '/content'
     * @return the response entity
     * @throws Exception
     */
    public ResponseEntity<String> delete(String resource) throws Exception {
        return delete2(resource).getValue();
    }
    /**
     * execute a DELETE request against the target service
     *
     * @param resource the resource string, ex. '/content'
     * @return the rest response
     * @throws MalformedURLException
     * @throws URISyntaxException
     */
    public ValueResult<ResponseEntity<String>> delete2(String resource)
            throws MalformedURLException,
            URISyntaxException {
        RequestEntity<?> request = buildRequestEntity(resource, null, HttpMethod.DELETE);
        return executeRestRequest2(request);
    }
    /**
     * execute a rest request against the messaging service
     *
     * @param resource the resource string, ex. '/content'
     * @param method the method (or verb)
     * @return the rest response
     * @throws MalformedURLException
     * @throws URISyntaxException
     */
    public <T> T executeRequest(String resource, HttpMethod method, Class<T> responseType) throws Exception {
        return executeRequest(resource, null, method, responseType);
    }
    /**
     * execute a rest request against the messaging service
     *
     * @param resource the resource string, ex. '/content'
     * @param method the method (or verb)
     * @return the rest response
     * @throws MalformedURLException
     * @throws URISyntaxException
     */
    public ResponseEntity<String> executeRequest(String resource, HttpMethod method) throws Exception {
        return executeRequest2(resource, null, method).getValue();
    }
    /**
     * execute a rest request against the messaging service
     *
     * @param resource the resource string, ex. '/content'
     * @param body the object that makes up the body of the request
     * @param method the method (or verb)
     * @return the rest response
     * @throws MalformedURLException
     * @throws URISyntaxException
     */
    public <T> T executeRequest(String resource, Object body, HttpMethod method, Class<T> responseType)
            throws Exception {
        RequestEntity<?> request = buildRequestEntity(resource, body, method);
        return executeRestRequest(request, responseType);
    }
    /**
     * execute a rest request against the messaging service
     *
     * @param resource the resource string, ex. '/content'
     * @param body the object that makes up the body of the request
     * @param method the method (or verb)
     * @return the rest response
     * @throws MalformedURLException
     * @throws URISyntaxException
     */
    public ResponseEntity<String> executeRequest(String resource, Object body, HttpMethod method)
            throws MalformedURLException,
            URISyntaxException {
        return executeRequest2(resource, body, method).getValue();
    }
    /**
     * execute a rest request against the messaging service
     *
     * @param resource the resource string, ex. '/content'
     * @param method the method (or verb)
     * @return the rest response
     * @throws MalformedURLException
     * @throws URISyntaxException
     */
    public ValueResult<ResponseEntity<String>> executeRequest2(String resource, HttpMethod method)
            throws MalformedURLException,
            URISyntaxException {
        return executeRequest2(resource, null, method);
    }
    /**
     * execute a rest request against the messaging service
     *
     * @param resource the resource string, ex. '/content'
     * @param body the object that makes up the body of the request
     * @param method the method (or verb)
     * @return the rest response
     * @throws MalformedURLException
     * @throws URISyntaxException
     */
    public ValueResult<ResponseEntity<String>> executeRequest2(String resource, Object body, HttpMethod method)
            throws MalformedURLException,
            URISyntaxException {
        RequestEntity<?> request = buildRequestEntity(resource, body, method);
        return executeRestRequest2(request);
    }
    /**
     * execute a GET request against the target service
     *
     * @param resource the resource string, ex. '/content'
     * @param responseType the type of the response object
     * @param <T> the response type
     * @return the cast operation result
     * @throws Exception
     */
    public <T> T get(String resource, Class<T> responseType) throws Exception {
        RequestEntity<?> request = buildRequestEntity(resource, null, HttpMethod.GET);
        return executeRestRequest(request, responseType);
    }
    /**
     * execute a GET request against the target service
     *
     * @param resource the resource string, ex. '/content'
     * @return the response entity
     * @throws Exception
     */
    public ResponseEntity<String> get(String resource) throws Exception {
        return get2(resource).getValue();
    }
    /**
     * execute a GET request against the target service
     *
     * @param resource the resource string, ex. '/content'
     * @return the rest response
     * @throws MalformedURLException
     * @throws URISyntaxException
     */
    public ValueResult<ResponseEntity<String>> get2(String resource)
            throws MalformedURLException,
            URISyntaxException {
        RequestEntity<?> request = buildRequestEntity(resource, null, HttpMethod.GET);
        return executeRestRequest2(request);
    }
    /**
     * execute a GET image request against the target service
     *
     * @param resource
     * @return the image
     * @throws MalformedURLException
     * @throws URISyntaxException
     */
    public byte[] getImage(String resource) throws MalformedURLException, URISyntaxException {
        RequestEntity<?> request = buildRequestEntity(resource, null, HttpMethod.GET);
        ResponseEntity<byte[]> response = executeRestRequest3(request, byte[].class);
        return response.getBody();
    }
    /**
     * execute a POST request against the target service
     *
     * @param resource the resource string, ex. '/content'
     * @param body the object that makes up the body of the request
     * @param responseType the type of the response object
     * @param <T> the response type
     * @return the cast operation result
     * @throws Exception
     */
    public <T> T post(String resource, Object body, Class<T> responseType) throws Exception {
        RequestEntity<?> request = buildRequestEntity(resource, body, HttpMethod.POST);
        return executeRestRequest(request, responseType);
    }
    /**
     * execute a POST request against the target service
     *
     * @param resource the resource string, ex. '/content'
     * @param body the object that makes up the body of the request
     * @return the cast operation result
     * @throws Exception
     */
    public ResponseEntity<String> post(String resource, Object body) throws Exception {
        return post2(resource, body).getValue();
    }
    /**
     * execute a POST request against the target service
     *
     * @param resource the resource string, ex. '/content'
     * @param body the object that makes up the body of the request
     * @return the cast operation result
     * @throws Exception
     */
    public ValueResult<ResponseEntity<String>> post2(String resource, Object body) throws Exception {
        RequestEntity<?> request = buildRequestEntity(resource, body, HttpMethod.POST);
        return executeRestRequest2(request);
    }
    /**
     * execute a PUT request against the target service
     *
     * @param resource the resource string, ex. '/content'
     * @param body the object that makes up the body of the request
     * @param responseType the type of the response object
     * @param <T> the response type
     * @return the cast operation result
     * @throws Exception
     */
    public <T> T put(String resource, Object body, Class<T> responseType) throws Exception {
        RequestEntity<?> request = buildRequestEntity(resource, body, HttpMethod.PUT);
        return executeRestRequest(request, responseType);
    }
    /**
     * execute a PUT request against the target service
     *
     * @param resource the resource string, ex. '/content'
     * @param body the object that makes up the body of the request
     * @return the cast operation result
     * @throws Exception
     */
    public ResponseEntity<String> put(String resource, Object body) throws Exception {
        return put2(resource, body).getValue();
    }
    /**
     * execute a PUT request against the target service
     *
     * @param resource the resource string, ex. '/content'
     * @param body the object that makes up the body of the request
     * @return the cast operation result
     * @throws Exception
     */
    public ValueResult<ResponseEntity<String>> put2(String resource, Object body) throws Exception {
        RequestEntity<?> request = buildRequestEntity(resource, body, HttpMethod.PUT);
        return executeRestRequest2(request);
    }
    // endregion
    // region " Private Methods "
    /**
     * get a new request entity
     *
     * @param resource the resource string, ex. 'content'
     * @param body the request body
     * @param method the method (or verb)
     * @return a formatted request entity
     * @throws MalformedURLException
     * @throws URISyntaxException
     */
    private RequestEntity<Object> buildRequestEntity(String resource, Object body, HttpMethod method)
            throws MalformedURLException,
            URISyntaxException {
        URI url = buildUrl(resource, method);
        HttpHeaders headers = getHeaders();
        Object requestBody = buildBody(body, method);
        return new RequestEntity<Object>(requestBody, headers, method, url);
    }
    private Object buildBody(Object body, HttpMethod method) {
        if (body == null || getAdditionalParameters() == null || getAdditionalParameters().isEmpty() ||
                (method != HttpMethod.POST && method != HttpMethod.PUT)) {
            return body;
        }
        MapType mapType = objectMapper.getTypeFactory().constructMapType(
                LinkedHashMap.class,
                String.class,
                Object.class);
        Map<String, Object> hashMap = objectMapper.convertValue(body, mapType);
        hashMap.putAll(getAdditionalParameters());
        Object value;
        try {
            value = objectMapper.writeValueAsString(hashMap);
        } catch (Exception e) {
            value = body;
        }
        return value;
    }
    /**
     * construct the request url
     *
     * @param resource the requested resource
     * @param method the http method
     * @return the url as a URI
     * @throws MalformedURLException
     * @throws URISyntaxException
     */
    private URI buildUrl(String resource, HttpMethod method) throws MalformedURLException, URISyntaxException {
        String serviceUri = getServiceUri();
        resource = StringUtils.trimToEmpty(resource);
        if (!serviceUri.endsWith("/")) {
            serviceUri = serviceUri + "/";
        }
        StringUtils.stripStart(resource, "/");
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(serviceUri)
                .path(resource);
        if (getAdditionalParameters() == null || getAdditionalParameters().isEmpty()) {
            return builder.build().toUri();
        }
        if (method != HttpMethod.POST && method != HttpMethod.PUT) {
            for (Map.Entry<String, Object> entry : getAdditionalParameters().entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }
        return builder.build().toUri();
    }
    /**
     * convert the response to the specified object type
     *
     * @param response the response entity
     * @param responseType the response type
     * @param <T> the object type
     * @return the converted object
     * @throws Exception
     */
    private <T> T convertRestResult(ResponseEntity<String> response, Class<T> responseType) throws Exception {
        if (RestHelper.isError(response.getStatusCode())) {
            throw new Exception(extractError(response));
        }
        return objectMapper.readValue(response.getBody(), responseType);
    }
    /**
     * convert the result to a value result containing the response
     *
     * @param response the response entity
     * @return the converted object
     */
    private ValueResult<ResponseEntity<String>> convertToValueResult(ResponseEntity<String> response) {
        ValueResult<ResponseEntity<String>> result = new ValueResult<ResponseEntity<String>>();
        result.setValue(response);
        if (RestHelper.isError(response.getStatusCode())) {
            result.setError(extractError(response));
        }
        return result;
    }
    /**
     * enable SSL for an https request
     */
    private void enableSSL() {
        TrustManager[] trustAllCerts = new TrustManager[] { new CertificateTrustManager() };
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            Logger.current().logError(Thread.currentThread().getStackTrace()[1], "Error loading SSL certificate");
        }
    }
    /**
     * execute a rest request against the messaging service
     *
     * @param request the request entity
     * @param responseType the response type
     * @param <T> the object type
     * @return the response entity
     * @throws Exception
     */
    private <T> T executeRestRequest(RequestEntity<?> request, Class<T> responseType) throws Exception {
        ValueResult<ResponseEntity<String>> response = executeRestRequest2(request);
        return convertRestResult(response.getValue(), responseType);
    }
    /**
     * execute a rest request against the messaging service
     *
     * @param request the request entity
     * @return the response entity
     */
    private ValueResult<ResponseEntity<String>> executeRestRequest2(RequestEntity<?> request) {
        ResponseEntity<String> response = executeRestRequest3(request, String.class);
        return convertToValueResult(response);
    }
    private <T> ResponseEntity<T> executeRestRequest3(RequestEntity<?> request, Class<T> responseType) {
        if (request.getUrl().getScheme().contains("https")) {
            enableSSL();
        }
        return restTemplate.exchange(request, responseType);
    }
    /**
     * try to extract a meaningful error string
     *
     * @param response the response entity
     * @return the error message
     */
    private String extractError(ResponseEntity<String> response) {
        try {
            RestJsonFault fault = objectMapper.readValue(response.getBody(), RestJsonFault.class);
            return String.format(
                    "[%s] Reason : %s - %s ",
                    fault.getStatus(),
                    fault.getError(),
                    fault.getMessage());
        } catch (Exception ex) {
            return String.format(
                    "[%s] Reason : %s",
                    response.getStatusCode().toString(),
                    response.getStatusCode().getReasonPhrase());
        }
    }
    /**
     * get the appropriate headers for the request
     *
     * @return the headers
     */
    private HttpHeaders getHeaders() {
        Map<String, String> additionalHeaders = getAdditionalHeaders();
        HttpHeaders httpHeaders = new HttpHeaders();
        if (MapUtils.isNotEmpty(additionalHeaders)) {
            httpHeaders.setAll(additionalHeaders);
        }
        if (StringUtils.isNotEmpty(getContentType())) {
            httpHeaders.setContentType(MediaType.parseMediaType(getContentType()));
        }
        if (StringUtils.isNotEmpty(getAccessToken())) {
            httpHeaders.add("access_token", getAccessToken());
        }
        if (StringUtils.isNotEmpty(getRefreshToken())) {
            httpHeaders.add("refresh_token", getRefreshToken());
        }
        return httpHeaders;
    }
    // endregion 

}
