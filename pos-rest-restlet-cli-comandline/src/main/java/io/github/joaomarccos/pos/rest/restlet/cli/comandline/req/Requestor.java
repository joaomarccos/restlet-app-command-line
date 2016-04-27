package io.github.joaomarccos.pos.rest.restlet.cli.comandline.req;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import org.apache.commons.cli.ParseException;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;

/**
 *
 * @author João Marcos <joaomarccos.github.io>
 */
public class Requestor {

    public static final String USERS = "http://localhost:8080/users";
    public static final String PERSONS = "http://localhost:8080/persons";

    public String get(String url) throws IOException {
        ClientResource cli = new ClientResource(url);
        return cli.get().getText();
    }

    public String get(String url, String filter) throws IOException, ParseException {
        Gson gson = new GsonBuilder().create();
        try {
            ClientResource cli = new ClientResource(url + "/" + gson.fromJson(filter, Query.class).getKey());
            return cli.get().getText();
        } catch (NullPointerException ex) {
            throw new ParseException("Filtro inválido");
        }
    }

    public String post(String url, String data) throws IOException {
        ClientResource cli = new ClientResource(url);
        StringRepresentation representation = new StringRepresentation(data, MediaType.APPLICATION_ALL_JSON);
        Representation post = cli.post(representation);
        return post.getText();
    }

    public String put(String url, String data) throws IOException {
        ClientResource cli = new ClientResource(url);
        StringRepresentation representation = new StringRepresentation(data, MediaType.APPLICATION_ALL_JSON);
        Representation put = cli.put(representation);
        return put.getText();
    }

    public String delete(String url, String filter) throws IOException, ParseException {
        try {
            Gson gson = new GsonBuilder().create();
            ClientResource cli = new ClientResource(url + "/" + gson.fromJson(filter, Query.class).getKey());
            Representation delete = cli.delete();
            return delete.getText();
        } catch (NullPointerException ex) {
            throw new ParseException("Filtro inválido");
        }
    }

    private class Query {

        private String key;

        public Query() {
        }

        public Query(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

    }

}
