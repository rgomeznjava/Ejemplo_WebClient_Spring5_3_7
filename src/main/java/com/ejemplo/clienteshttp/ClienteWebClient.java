package com.ejemplo.clienteshttp;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.web.reactive.function.client.WebClient;

     
/**
 * 	CLIENTE HTTP CLIENT EJEMPLO CRUD
 * 
 * 	- IMPLEMENTADO CON WebClient de SpringFlux
 * 
 * @author Raul Gomez Nieto
 *
 */
public class ClienteWebClient {
	
	
    private static final Logger LOG = LogManager.getLogger(ClienteWebClient.class.getName());

    //Charset UTF-8
  	private static final  String CHARSET_UTF_8 = "UTF-8";	
  	
  	//Salto linea
  	private static String NEW_LINE = System.getProperty("line.separator");	

	
	private static final int MIN_COD_ERROR_HTTP = 300;
	
	
	//Constantes TLS
	public static final String TLSv1_2 = "TLSv1.2";
	public static final String TLSv1_3 = "TLSv1.3";
	
	//Version TLS. Por defecto  TLSv1.3
	private String versionTLS = TLSv1_3; 
	
	//Puerto ssl
	private int portSSL = 443;  
		
	//En milisegundos x Defecto (60 segundos)
	private int connectionTimeOut = 60000;  
	

	
	/**
	 * Constructor
	 * 
	 */
	public ClienteWebClient(Properties properties) {
		
		// Sobreescribir variables si vienen: versionTLS, portSSL, connectionTimeOut
        if (properties.getProperty("VERSION_TLS")!=null) {
        	versionTLS = properties.getProperty("VERSION_TLS").trim();
        }
        if (properties.getProperty("PORT_SSL")!=null && properties.getProperty("PORT_SSL").trim().length()>0) {
        	portSSL = Integer.parseInt(properties.getProperty("PORT_SSL").trim());
        }
        if (properties.getProperty("CONNECTION_TIME_OUT")!=null  && properties.getProperty("CONNECTION_TIME_OUT").trim().length()>0) {
        	connectionTimeOut = Integer.parseInt(properties.getProperty("CONNECTION_TIME_OUT").trim());
        }

	}



	public  RespuestaClienteHttp realizarPeticion_GET(String urlPeticion) throws Exception {
		
		//Objeto de negocio, que devolvemos
		RespuestaClienteHttp respCliente = new RespuestaClienteHttp();
		
		String resultado = "";
		
		//WebClient wc = WebClient.create("http://localhost:8080"); .uri(String.join("", "/users/", id))
		WebClient wc = WebClient.create();
		
		
		//HttpComponentsClientHttpConnector, HttpHandlerConnector, JettyClientHttpConnector, MockMvcHttpConnector, ReactorClientHttpConnector
		/*
		TcpClient  tcpClient = TcpClient
	            .create()
	            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT)
	            .doOnConnected(connection -> {
	                connection.addHandlerLast(new ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
	                connection.addHandlerLast(new WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
	            });*/
		
		/*
		WebClient wc = WebClient.builder()
	           // .baseUrl(urlPeticion)
	            .clientConnector(new ReactorClientHttpConnector(new reactor.netty.http.client.HttpClient() {
				}))
	           .build();*/
		
		
		/*
		  final var tcpClient = TcpClien
	                .create()
	                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT)
	                .doOnConnected(connection -> {
	                    connection.addHandlerLast(new ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
	                    connection.addHandlerLast(new WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
	                });
	
	        return WebClient.builder()
	                .baseUrl(BASE_URL)
	                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
	                .build();
		*/
		
		
		resultado = wc.get()
		.uri(urlPeticion)
	    .retrieve()
	    .bodyToMono(String.class)
	    .block();
		
		LOG.info(resultado);
		
		respCliente.setResultado(resultado);
		
		 /*
		ResponseEntity<String> response = wc.get()
	            .uri(url)
	            .exchange()
	            .flatMap(r -> r.toEntity(String.class))
	            .block();*/
		
		return respCliente;
		
	}
	
	

	public  RespuestaClienteHttp realizarPeticion_POST(String urlPeticion, String datos) throws Exception {
		
		//Objeto de negocio, que devolvemos
		RespuestaClienteHttp respCliente = new RespuestaClienteHttp();
			
	 return respCliente;	
	}
	
	public  RespuestaClienteHttp realizarPeticion_PUT(String urlPeticion, String datos) throws Exception {
			
		//Objeto de negocio, que devolvemos
		RespuestaClienteHttp respCliente = new RespuestaClienteHttp();
			
	 return respCliente;	
	}

	public  RespuestaClienteHttp realizarPeticion_DELETE(String urlPeticion) throws Exception {
		
		//Objeto de negocio, que devolvemos
		RespuestaClienteHttp respCliente = new RespuestaClienteHttp();
			
	 return respCliente;	
	}
		
		
	
}
