package com.sapient.vertx.vertx_example;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class VertxRestRequest {
	
	@SuppressWarnings("unused")
	public static void main( String[] args ){

		//Interface static method.
		Vertx vertx = Vertx.vertx();
		// Create HTTP sever from Vert.X interface.
		HttpServer httpServer = vertx.createHttpServer();
		
		// Router is similar to the Rest Controller in Spring MVC, 
		// it will be useful in creating REST resources.
		Router router = Router.router(vertx);
		
		// If you want to restrict to specific HTTP method.
		//Router router = (Router) Router.router(vertx).route().method(HttpMethod.GET);
		
		/*A Router is one of the core concepts of Vert.x-Web. 
		 It’s an object which maintains zero or more Routes. 
		 A router takes an HTTP request and finds the first matching route for that request, 
		 and passes the request to that route.*/
		// It will be called every request.
		
		// In order to put path like we do in case of @RequestMapping we have to put within route()
		// By default it will accept all GET, POST, PUT etc.if we don't specify explicitly
		
		Route handler1 = router.get("/vertx/:name").handler(routingContext ->{
			String responseName = routingContext.request().getParam("name");
			// If chunked is true, this response will use HTTP chunked encoding, 
			// and each call to write to the body will correspond to a new HTTP chunk sent on the wire.
			HttpServerResponse response = routingContext.response();
			response.setChunked(true);
			// We are not using response.end(), as it will end the stream.
			response.write("Hi "+responseName);
			// After this particular Vert.X go to the next route.
			response.end();
		});
		
		// Only consume JSON messages
		Route handler2 = router.post("/vertx").consumes("*/json").handler(routingContext ->{
			HttpServerResponse response = routingContext.response();
			// We are not using response.end(), as it will end the stream.
			response.setChunked(true);
			response.write("Hi Susmit from POST!!");
			// After this particular Vert.X go to the next route.
			response.end();
		});
		httpServer.requestHandler(router::accept).listen(8600);
	}
}
