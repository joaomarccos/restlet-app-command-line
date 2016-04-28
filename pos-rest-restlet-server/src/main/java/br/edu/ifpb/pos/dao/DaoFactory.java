package br.edu.ifpb.pos.dao;

import br.edu.ifpb.pos.entitys.Person;
import br.edu.ifpb.pos.entitys.User;

/**
 *
 * @author João Marcos <joaomarccos.github.io>
 */
public class DaoFactory {
    
    public static DAO<Person> createPersonDao(){
        return new DAOJPA<>();
    }
    
    public static DAO<User> createUserDao(){
        return new DAOJPA<>();
    }
    
}
