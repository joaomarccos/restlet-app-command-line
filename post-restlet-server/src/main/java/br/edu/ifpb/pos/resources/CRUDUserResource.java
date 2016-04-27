package br.edu.ifpb.pos.resources;

import br.edu.ifpb.pos.dao.DaoFactory;
import br.edu.ifpb.pos.enums.Results;
import br.edu.ifpb.pos.util.JsonConverter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

/**
 *
 * @author João Marcos <joaomarccos.github.io>
 */
public class CRUDUserResource extends ServerResource {

    @Post
    public StringRepresentation insert(Representation r) {
        try {
            String data = r.getText();
            DaoFactory.createUserDao().salvar(JsonConverter.convertToUser(data));
        } catch (Exception ex) {
            Logger.getLogger(CRUDUserResource.class.getName()).log(Level.SEVERE, null, ex);
            return new StringRepresentation(Results.ERROR.json(), MediaType.APPLICATION_ALL_JSON);
        }
        return new StringRepresentation(Results.SUCCESS.json(), MediaType.APPLICATION_ALL_JSON);
    }

    @Put
    public StringRepresentation update(Representation r) {
        try {
            String data = r.getText();
            DaoFactory.createUserDao().atualizar(JsonConverter.convertToUser(data));
        } catch (Exception ex) {
            Logger.getLogger(CRUDUserResource.class.getName()).log(Level.SEVERE, null, ex);
            return new StringRepresentation(Results.ERROR.json(), MediaType.APPLICATION_ALL_JSON);
        }
        return new StringRepresentation(Results.SUCCESS.json(), MediaType.APPLICATION_ALL_JSON);
    }
   

    @Get
    public StringRepresentation list() {
        try {            
            return new StringRepresentation(JsonConverter.convertToJson(
                    DaoFactory.createUserDao().consultaLista("user.all", null)),
                    MediaType.APPLICATION_ALL_JSON);
        } catch (Exception ex) {
            Logger.getLogger(CRUDUserResource.class.getName()).log(Level.SEVERE, null, ex);
            return new StringRepresentation(Results.ERROR.json(), MediaType.APPLICATION_ALL_JSON);
        }
    }
}