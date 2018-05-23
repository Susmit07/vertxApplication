package com.sapient.vertx.vertx_example;


import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class VertxRouting 
{
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
	/*	router.route("/vertx").handler(routingContext ->{
			HttpServerResponse response = routingContext.response();
			// In spring we use annotations, we can use JSON as well
			response.putHeader("content-type", "text/plain");
			// Message to be Pushed to the UI.
			response.end("Hi Susmit!!");
		});*/
		
		// If you want to create multiple routes.
		Route handler1 = router.route("/vertx").handler(routingContext ->{
			// Routing context is similar to application contect in REST world.
			HttpServerResponse response = routingContext.response();
			// If chunked is true, this response will use HTTP chunked encoding, 
			// and each call to write to the body will correspond to a new HTTP chunk sent on the wire.
			// We don't have to worry about the content length header
			response.setChunked(true);
			// We are not using response.end(), as it will end the stream.
			response.write("Hi Susmit from handler 1!!");
			// After this particular Vert.X go to the next route.
			routingContext.vertx().setTimer(50, route -> routingContext.next());
			
		});
		
		
		// Second Route. Chaining to 2nd route.
		Route handler2 = router.route("/vertx").handler(routingContext ->{
			HttpServerResponse response = routingContext.response();
			// No need to set chunk again as it is already set before
			//response.setChunked(true);
			// We are not using response.end(), as it will end the stream.
			response.write("Hi Susmit from handler 2!!");
			// After this particular Vert.X go to the next route.
			routingContext.vertx().setTimer(500, route -> routingContext.next());
		});
		
		// Third Route. After this we won't proceed.
		Route handler3 = router.route("/vertx").handler(routingContext ->{
			HttpServerResponse response = routingContext.response();
			response.write("Hi Susmit from handler 3!!");
			response.end("Ended");
		});
		
		httpServer.requestHandler(router::accept).listen(8200);

	}
}
