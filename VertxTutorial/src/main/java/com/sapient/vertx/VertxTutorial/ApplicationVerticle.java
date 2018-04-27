package com.sapient.vertx.VertxTutorial;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

/**
 * Hello world!
 *
 */
public class ApplicationVerticle extends AbstractVerticle  
{
    
	@Override
	public void start(io.vertx.core.Future<Void> startFuture) throws Exception {
		System.out.println("Application Verticle is starting!!");
		
		vertx.createHttpServer().requestHandler(r ->{
			r.response().end("<h1> Application Vertex first scenario");
			
		}).listen(8555, response ->{
			if(response.succeeded()) {
				startFuture.complete();
			}else {
				startFuture.fail("Cannot start server!!");
			}
		});
	}
	
	public static void main( String[] args ){
		
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new ApplicationVerticle());
    }
}
