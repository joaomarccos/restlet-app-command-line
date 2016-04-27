package io.github.joaomarccos.pos.rest.restlet.cli.comandline;

import io.github.joaomarccos.pos.rest.restlet.cli.comandline.req.Requestor;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author João Marcos <joaomarccos.github.io>
 */
public class App {

    private static final String JSON_PERSON = "json_person";
    private static final String JSON_USER = "json_user";
    private static final String HELP = "help";
    private static final String INSERT = "insert";
    private static final String UPDATE = "update";
    private static final String SELECT = "select";
    private static final String DELETE = "delete";
    private static final String TYPE = "type";

    private static Options getOptions() {
        Options options = new Options();
        options.addOption(null, HELP, false, "Exibir ajuda");
        options.addOption(null, INSERT, true, "Inserir um nova informação baseado no valor --" + TYPE);
        options.addOption(null, UPDATE, true, "Atualizar informaçoes baseado no valor --" + TYPE);
        options.addOption(null, DELETE, true, "Deletar informações baseado no valor --" + TYPE);
        options.addOption(null, SELECT, true, "Buscar informações baseado no valor --" + TYPE);
        options.addOption(null, TYPE, true, "Tipo de informação [json_person|json_user]");
        return options;
    }

    private static void getHelper() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("App\n\n <arg> - Um texto no formato Json. Exemplos:\n--delete {key:value} --type [json_person|"
                + "json_user]\n--insert|update {...} --type "
                + "[json_person|json_user]\n--select{key:value} --type[json_person|json_user]\n\n", getOptions());
    }

    private static String checkCommands(CommandLine line) throws ParseException, IOException {
        Requestor requestor = new Requestor();

        if (line.hasOption(HELP)) {
            getHelper();
        }
        if (line.hasOption(INSERT) && line.hasOption(TYPE)) {

            String url = getInfoType(line.getOptionValue(TYPE));
            return requestor.post(url, line.getOptionValue(INSERT));

        }
        if (line.hasOption(UPDATE) && line.hasOption(TYPE)) {

            String url = getInfoType(line.getOptionValue(TYPE));
            return requestor.put(url, line.getOptionValue(UPDATE));

        }
        if (line.hasOption(DELETE) && line.hasOption(TYPE)) {

            String url = getInfoType(line.getOptionValue(TYPE));
            return requestor.delete(url, line.getOptionValue(DELETE));

        }
        if (line.hasOption(SELECT) && line.hasOption(TYPE)) {

            String url = getInfoType(line.getOptionValue(TYPE));
            String optionValue = line.getOptionValue(SELECT);
            if (optionValue == null) {
                return requestor.get(url);
            } else {
                return requestor.get(url, optionValue);
            }
        }

        throw new ParseException("Comando inválido");
    }

    private static String getInfoType(String type) throws ParseException {
        switch (type) {
            case JSON_PERSON:
                return Requestor.PERSONS;
            case JSON_USER:
                return Requestor.USERS;
            default:
                throw new ParseException("tipo inválido");
        }
    }

    public static void main(String[] args) {

        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine line = parser.parse(getOptions(), args);
            System.out.println("----------------- Result -------------------\n\n"
                    +checkCommands(line) + "\n");           
        } catch (ParseException exp) {
            System.out.println("Unexpected exception:" + exp.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
