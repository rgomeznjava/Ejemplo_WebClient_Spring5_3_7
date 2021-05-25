package com.ejemplo.clienteshttp;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.reactive.function.client.WebClient;

     
/**
 * 	CLIENTE  WEB CLIENT DE SPRING EJEMPLO CRUD
 * 
 * 
 * @author GRN 
 *
 */
public class ClienteWebClient {
	
    private static final Logger LOG = LogManager.getLogger(ClienteWebClient.class.getName());
	
	private static final int MIN_COD_ERROR_HTTP = 300;
	
	private Properties prop = new Properties();
	
	//Constantes TLS
	public static final String TLSv1_2 = "TLSv1.2";
	public static final String TLSv1_3 = "TLSv1.3";
	
	//Version TLS. Por defecto  TLSv1.3
	private String VERSION_TLS = TLSv1_3; 
	
	//En milisegundos x Defecto (60 segundos)
	private static int CONNECTION_TIME_OUT = 60000;  
	
	//Charset UTF-8
	private static final  String CHARSET_UTF_8 = "UTF-8";	
	
	//Salto linea
	private static String NEW_LINE = System.getProperty("line.separator");	


	private void loadProperties() {
		
		 try (InputStream input = ClienteWebClient.class.getClassLoader().getResourceAsStream("app.properties")) {

	         
	            if (input == null) {
	                System.out.println("Sorry, unable to find app.properties");
	                return;
	            }

	            //load a properties file from class path, inside static method
	            this.prop.load(input);
	           
	            if (prop.getProperty("VERSION_TLS")!=null) VERSION_TLS = prop.getProperty("VERSION_TLS");
	            if (prop.getProperty("CONNECTION_TIME_OUT")!=null) CONNECTION_TIME_OUT = Integer.parseInt(prop.getProperty("CONNECTION_TIME_OUT"));

	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	}
	
	
	/**
	 * Constructor
	 * 
	 */
	public ClienteWebClient() {
		
		loadProperties();
	}

	
	public   RespuestaClienteHttp realizarPeticion_GET(String urlPeticion) throws Exception {
		
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
	
}
