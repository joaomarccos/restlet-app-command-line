package br.edu.ifpb.pos.resources;

import br.edu.ifpb.pos.dao.DAO;
import br.edu.ifpb.pos.dao.DaoFactory;
import br.edu.ifpb.pos.entitys.User;
import br.edu.ifpb.pos.enums.Results;
import br.edu.ifpb.pos.util.JsonConverter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

/**
 *
 * @author João Marcos <joaomarccos.github.io>
 */
public class UserFilterResource extends ServerResource {

    @Get
    public StringRepresentation findByCode() {
        String code = (String) getRequest().getAttributes().get("code");
        return new StringRepresentation(JsonConverter.convertToJson(DaoFactory.
                createUserDao().buscar(code, User.class)),
                MediaType.APPLICATION_ALL_JSON);

    }

    @Put
    public StringRepresentation update(Representation r) {
        String code = (String) getRequest().getAttributes().get("code");
        try {
            DAO<User> dao = DaoFactory.createUserDao();
            User user = mountUser(dao.buscar(code, User.class), r.getText());
            dao.atualizar(user);
        } catch (Exception ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
            return new StringRepresentation(Results.ERROR.json(), MediaType.APPLICATION_ALL_JSON);
        }
        return new StringRepresentation(Results.SUCCESS.json(), MediaType.APPLICATION_ALL_JSON);
    }

    private User mountUser(User u, String newInfoJson) {
        User newUser = JsonConverter.convertToUser(newInfoJson);
        u.setName(newUser.getName());
        u.setPassword(newUser.getPassword());
        return u;
    }

    @Delete
    public StringRepresentation deleteBycode() {
        String code = (String) getRequest().getAttributes().get("code");
        DAO<User> dao = DaoFactory.createUserDao();
        User user = dao.buscar(code, User.class);
        if (user != null) {
            dao.excluir(user);
        } else {
            return new StringRepresentation(Results.ERROR.json(),
                    MediaType.APPLICATION_ALL_JSON);
        }
        return new StringRepresentation(Results.SUCCESS.json(),
                MediaType.APPLICATION_ALL_JSON);
    }
}
