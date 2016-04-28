package br.edu.ifpb.pos;

import br.edu.ifpb.pos.resources.PersonResource;
import br.edu.ifpb.pos.resources.UserResource;
import br.edu.ifpb.pos.resources.PersonFilterResource;
import br.edu.ifpb.pos.resources.UserFilterResource;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

/**
 *
 * @author João Marcos F
 */
public class App {

    public static void main(String[] args) throws Exception {
        Component component = new Component();
        component.getServers().add(Protocol.HTTP, 8080);
        
        Router router = new Router();
        router.attach("/person", PersonResource.class);
        router.attach("/user", UserResource.class);
        router.attach("/person/{code}", PersonFilterResource.class);
        router.attach("/user/{code}", UserFilterResource.class);
        
        Application application = new Application();
        application.setInboundRoot(router);
        
        component.getDefaultHost().attach("", application);
        component.start();

    }
}
