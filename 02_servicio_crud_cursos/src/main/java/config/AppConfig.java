package config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import controller.config.MvcConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import service.config.ServiceConfig;

public class AppConfig implements WebApplicationInitializer {
	
	 @Override
	 public void onStartup(ServletContext container) {
		 
		 // Registra la clase de configuración del modelo
		 AnnotationConfigWebApplicationContext rootContext =new AnnotationConfigWebApplicationContext();
		 rootContext.register(ServiceConfig.class);
		 
		 // Registra la clase de configuración del controlador
		 //PONER la clase de configuración del controler --> MvcConfig
		 AnnotationConfigWebApplicationContext dispatcherContext =new AnnotationConfigWebApplicationContext();
		 dispatcherContext.register(MvcConfig.class);
		 
		 // Gestiona el ciclo de vida del contexto de aplicación
		 container.addListener(new ContextLoaderListener(rootContext)); 
		 
		 // Crea y registra el DispatcherServlet
		 ServletRegistration.Dynamic dispatcher =
		 container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
		 dispatcher.setLoadOnStartup(1);
		 dispatcher.addMapping("/");
	 }

}
