package br.edu.ifpb.pos.enums;

/**
 *
 * @author João Marcos <joaomarccos.github.io>
 */
public enum Results {

    SUCCESS, ERROR;

    public String json() {
        return ("{result: " + this.name() + "}");
    }

}
