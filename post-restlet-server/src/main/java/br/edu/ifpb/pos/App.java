package br.edu.ifpb.pos;

import br.edu.ifpb.pos.resources.CRUDPersonResource;
import br.edu.ifpb.pos.resources.CRUDUserResource;
import br.edu.ifpb.pos.resources.PersonFinder;
import br.edu.ifpb.pos.resources.UserFinder;
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
        router.attach("/persons", CRUDPersonResource.class);
        router.attach("/users", CRUDUserResource.class);
        router.attach("/persons/{code}", PersonFinder.class);
        router.attach("/users/{code}", UserFinder.class);
        
        Application application = new Application();
        application.setInboundRoot(router);
        
        component.getDefaultHost().attach("", application);
        component.start();

    }
}
