package br.edu.ifpb.pos.resources;

import br.edu.ifpb.pos.dao.DAO;
import br.edu.ifpb.pos.dao.DaoFactory;
import br.edu.ifpb.pos.entitys.Person;
import br.edu.ifpb.pos.enums.Results;
import br.edu.ifpb.pos.util.JsonConverter;
import org.restlet.data.MediaType;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 *
 * @author João Marcos <joaomarccos.github.io>
 */
public class PersonFinder extends ServerResource {

    @Get
    public StringRepresentation findByCode() {
        String code = (String) getRequest().getAttributes().get("code");

        return new StringRepresentation(JsonConverter.convertToJson(DaoFactory.
                createPersonDao().buscar(code, Person.class)),
                MediaType.APPLICATION_ALL_JSON);

    }

    @Delete
    public StringRepresentation deleteBycode() {
        String code = (String) getRequest().getAttributes().get("code");
        DAO<Person> dao = DaoFactory.createPersonDao();
        Person person = dao.buscar(code, Person.class);
        if (person != null) {
            dao.excluir(person);
        } else {
            return new StringRepresentation(Results.ERROR.json(),
                    MediaType.APPLICATION_ALL_JSON);
        }
        return new StringRepresentation(Results.SUCCESS.json(),
                MediaType.APPLICATION_ALL_JSON);
    }
}
