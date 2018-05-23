package com.sapient.vertx.vertx_example;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class VertxRestResponse {

	@SuppressWarnings("unused")
	public static void main( String[] args ){

		//Interface static method.
		Vertx vertx = Vertx.vertx();
		// Create HTTP sever from Vert.X interface.
		HttpServer httpServer = vertx.createHttpServer();

		// Router is similar to the Rest Controller in Spring MVC, 
		// it will be useful in creating REST resources.
		Router router = Router.router(vertx);
		/*A Router is one of the core concepts of Vert.x-Web. 
		 It’s an object which maintains zero or more Routes. 
		 A router takes an HTTP request and finds the first matching route for that request, 
		 and passes the request to that route.*/
		// It will be called every request.

		// In order to put path like we do in case of @RequestMapping we have to put within route()
		// By default it will accept all GET, POST, PUT etc.if we don't specify explicitly

		Route handler1 = router.get("/vertx").handler(routingContext ->{
			HttpServerResponse response = routingContext.response();
			// If chunked is true, this response will use HTTP chunked encoding, 
			// and each call to write to the body will correspond to a new HTTP chunk sent on the wire.
			response.setChunked(true);
			response.write("Hi Susmit from GET!!");
			response.end();
		});

		Route handler2 = router.post("/vertx").handler(routingContext ->{
			HttpServerResponse response = routingContext.response();
			response.setChunked(true);
			response.write("Hi Susmit from POST!!");
			response.end();
		});
		httpServer.requestHandler(router::accept).listen(8900);
	}
}
