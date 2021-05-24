package com.ejemplo.clienteshttp;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


/**
 *   TEST CLIENTE HTTP EJEMPLO (Junit)
 * 
 */
public class Test_ClienteHttp4513 {

	//Salto linea
	private static final String NEW_LINE = System.getProperty("line.separator");	

	//Cliente  
	private ClienteHttp4513 clienteHttp;
	
	//Credenciales (caso de usarlas)
	private	static	String USUARIO = "XXXXXXXX";	  	 
	private	static	String PASSWORD = "XXXXXXXX";	     
	
	//CARPETA Para archivos xml, json,etc.
	private static  String RUTA_PRUEBAS = "/PRUEBAS/";
		
	//URL PETICION DE PRUEBA
	private static String URL_PETICION= "xxxxxxxxxxxxxxxxxx";
	
	
	/**
	 *  Inicializar ClienteHttp4512  y datos comunes para todos los Test
	 * 
	 * @throws Exception
	 */
	@Before
	public  void _testInicializar() throws Exception {
		
		//RUTA_PRUEBAS = "E:/PRUEBAS/";
		
		USUARIO = "XXXXXX";    
		PASSWORD = "XXXXXX";
		
		URL_PETICION = "https://dummy.restapiexample.com/api/v1/employees";
		URL_PETICION = "https://www.google.es";

		
	}	
	 
	
	//@Ignore ("descomentar para ignorar")	
	@Test
	public void prueba_GET_TLSv1_2() throws Exception {
					
		System.out.println(NEW_LINE+"TEST ---> PRUEBA GET (HTTPS-TLSv1.2) ...");
		
		//Datos de prueba: parametros
		String p1 = "aaaa"; 
		String p2 =  "bbbbb";	
		String p3 = "cccc";   
		//url peticion
		//String urlPeticion = URL_PETICION + "solicitarDatosFormulario/"+p1+ "/"+p2 +"/"+p3;	
		String urlPeticion = URL_PETICION;			
		
		//CALL WS
		clienteHttp = new ClienteHttp4513();	

		//clienteHttp = new ClienteHttp4513Ejemplo(ClienteHttp4513Ejemplo.TLSv1_2, USUARIO, PASSWORD);	
		//Objeto de negocio respuesta cliente 
		RespuestaClienteHttp respuestaCliente = clienteHttp.realizarPeticion_GET(urlPeticion);
		
		//200 Ok, 201 Created, 202 Accepted, 400 Bad request, 406 Not acceptable, 500 Error Server
		
		//Todos los igual o mayores de 3xx..4xx..5xx ser�an error: 406 Not acceptable, 500 Error Server
		//excepto 400 Bad Request --> LO UTILIZAMOS COMO ERRORES DE VALIDACION O LOGICA (Error controlado)
	 
		//El TEST es OK, si trae cod.estado correspodiente a la prueba y  recibe XML con confirmacion true/false
		Assert.assertTrue("TEST GET_TLSv1_2  NO HA PASADO: SE ESPERABA 200 OK", 
				respuestaCliente.getCodigoEstado()==200 
				&& respuestaCliente.getResultado().contains("Tiger Nixon"));
	}
	
	
	
	
	
	
	@Ignore ("descomentar para ignorar")	
	@Test
	public void solicitarDatosFormulario_GET_TLSv1_3() throws Exception {
					
		System.out.println(NEW_LINE+"TEST ---> SOLICITAR DATOS FORMULARIO GET (HTTPS-TLSv1.3) ...");
		
		//Datos de prueba: parametros
		String perito = "00032"; 
		String tramitacion =  "2021009999901";	
		String tipoFormulario = "DT11";   
		//url peticion
		String urlPeticion = URL_PETICION + "solicitarDatosFormulario/"+perito+ "/"+tramitacion +"/"+tipoFormulario;	
					
		//CALL WS
		clienteHttp = new ClienteHttp4513();
		//Objeto de negocio respuesta cliente 
		RespuestaClienteHttp respuestaCliente = clienteHttp.realizarPeticion_GET(urlPeticion);
		
		//200 Ok, 201 Created, 202 Accepted, 400 Bad request, 406 Not acceptable, 500 Error Server
		
		//Todos los igual o mayores de 3xx..4xx..5xx ser�an error: 406 Not acceptable, 500 Error Server
		//excepto 400 Bad Request --> LO UTILIZAMOS COMO ERRORES DE VALIDACION O LOGICA (Error controlado)
	 
		//El TEST es OK, si trae cod.estado correspodiente a la prueba y  recibe XML con confirmacion true/false
		Assert.assertTrue("TEST SOLICITAR DATOS FORMULARIO NO HA PASADO: SE ESPERABA 400 BAD REQUEST", 
						(respuestaCliente.getCodigoEstado()==400)
						&& respuestaCliente.getResultado().contains("<confirmacion>") );
	}
	


	@Ignore ("descomentar para ignorar")	
	@Test
	public void solicitarDatosFormulario_GET_TLSv1_2() throws Exception {
		
		System.out.println(NEW_LINE+"TEST ---> SOLICITAR DATOS FORMULARIO GET (HTTPS-TLSv1.2) ...");
		
		//Datos de prueba: parametros
		String perito = "00032"; 
		String tramitacion =  "2021009999901";	
		String tipoFormulario = "DT11";   
		//url peticion
		String urlPeticion = URL_PETICION + "solicitarDatosFormulario/"+perito+ "/"+tramitacion +"/"+tipoFormulario;	
			
		//CALL WS
		//Establecemos TLSv1.2 en constructor
		clienteHttp = new ClienteHttp4513();	
		//Objeto de negocio respuesta cliente 
		RespuestaClienteHttp respuestaCliente = clienteHttp.realizarPeticion_GET(urlPeticion);
		
		//El TEST es OK, si trae cod.estado correspodiente a la prueba y  recibe XML con confirmacion true/false
		Assert.assertTrue("TEST SOLICITAR DATOS FORMULARIO NO HA PASADO: SE ESPERABA 400 BAD REQUEST", 
						(respuestaCliente.getCodigoEstado()==400)
						&& respuestaCliente.getResultado().contains("<confirmacion>") );
	} 
	
	
	
	@Ignore ("descomentar para ignorar")	 
	@Test
	public void registrarFormulario_POST() throws Exception {
		
		System.out.println(NEW_LINE+"TEST ---> REGISTRAR FORMULARIO POST....");
		
		String urlPeticion = URL_PETICION + "registrarFormulario/"; 	 
		
		//Datos XML a enviar, obtenidos de file
		String nombreArchivoXML = "datos.xml";
		File ficheroDatosXML = new File(RUTA_PRUEBAS + nombreArchivoXML);			
		String datosXML = FileUtils.readFileToString(ficheroDatosXML, "UTF-8");		
		
		//CALL WS
		clienteHttp = new ClienteHttp4513();
		//Objeto respuesta de negocio
		RespuestaClienteHttp respuestaCliente  = clienteHttp.realizarPeticion_POST(urlPeticion, datosXML);
			
		//El TEST es OK, si trae cod.estado correspondiente a la prueba y  recibe XML con confirmacion true/false
		Assert.assertTrue("TEST REGISTRAR FORMULARIO NO HA PASADO: SE ESPERABA 406 - Not Acceptable", respuestaCliente.getCodigoEstado()==406);
	}
	

	@Ignore ("descomentar para ignorar")	
	@Test
	public void borrarFormulario_DELETE() throws Exception {
		 	
		System.out.println(NEW_LINE+"TEST ---> BORRAR FORMULARIO DELETE....");
		
		//Datos de prueba: parametros
		String perito = "00032"; 
		String tramitacion =  "2021009999901";	
		String tipoFormulario = "DT11";   
		//url peticion
		String urlPeticion = URL_PETICION + "borrarFormulario/"+perito+ "/"+tramitacion +"/"+tipoFormulario;	
					
		//CALL WS
		clienteHttp = new ClienteHttp4513();
		//Objeto de negocio respuesta cliente 
		RespuestaClienteHttp respuestaCliente = clienteHttp.realizarPeticion_DELETE(urlPeticion);
	
		//El TEST es OK, si trae cod.estado correspodiente a la prueba y  recibe XML con confirmacion true/false
		Assert.assertTrue("TEST BORRAR FORMULARIO NO HA PASADO: SE ESPERABA 400 BAD REQUEST", 
						(respuestaCliente.getCodigoEstado()==400)
						&& respuestaCliente.getResultado().contains("<confirmacion>") );
	}


	@Ignore ("descomentar para ignorar")	 
	@Test
	public void modificarFormulario_PUT() throws Exception {
		
		System.out.println(NEW_LINE+"TEST ---> MODIFICAR FORMULARIO PUT....");
		 
		String urlPeticion = URL_PETICION + "modificarFormulario/"; 	 
		
		//Datos XML a enviar, obtenidos de file
		String nombreArchivoXML = "datos.xml";
		File ficheroDatosXML = new File(RUTA_PRUEBAS + nombreArchivoXML);			
		String datosXML = FileUtils.readFileToString(ficheroDatosXML, "UTF-8");		
	 
		//CALL HTTP PUT
		clienteHttp = new ClienteHttp4513();	
		//Objeto respuesta de negocio
		RespuestaClienteHttp respuestaCliente  = clienteHttp.realizarPeticion_PUT(urlPeticion, datosXML);
			
		//El TEST es OK, si trae cod.estado correspondiente a la prueba y  recibe XML con confirmacion true/false
		Assert.assertTrue("TEST MODIFICAR FORMULARIO NO HA PASADO: SE ESPERABA 406 - Not Acceptable", respuestaCliente.getCodigoEstado()==406);
	}
	
	
} //_fin
	
