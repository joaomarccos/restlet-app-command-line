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
import org.restlet.resource.ServerResource;

/**
 *
 * @author João Marcos <joaomarccos.github.io>
 */
public class PersonResource extends ServerResource {

    @Post
    public StringRepresentation insert(Representation r) {
        try {
            String data = r.getText();
            DaoFactory.createPersonDao().salvar(JsonConverter.convertToPerson(data));
        } catch (Exception ex) {
            Logger.getLogger(PersonResource.class.getName()).log(Level.SEVERE, null, ex);
            return new StringRepresentation(Results.ERROR.json(), MediaType.TEXT_JAVASCRIPT);
        }
        return new StringRepresentation(Results.SUCCESS.json(), MediaType.TEXT_JAVASCRIPT);
    }   

    @Get
    public StringRepresentation list() {        
        try {
            return new StringRepresentation(JsonConverter.convertToJson(
                    DaoFactory.createPersonDao().consultaLista("person.all", null)),
                    MediaType.TEXT_JAVASCRIPT);
        } catch (Exception ex) {
            Logger.getLogger(PersonResource.class.getName()).log(Level.SEVERE, null, ex);
            return new StringRepresentation(Results.ERROR.json(), MediaType.TEXT_JAVASCRIPT);
        }
    }
}
