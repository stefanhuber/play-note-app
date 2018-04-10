package services;

import io.ebean.Ebean;
import models.User;

public class EbeanUserRepository {

    public EbeanUserRepository() {
        if(Ebean.find(User.class).findCount() <= 0 ) {
            User user1 = new User();
            user1.setUsername("user1");
            user1.setPasswordInCleartext("pwd1");
            Ebean.save(user1);

            User user2 = new User();
            user2.setUsername("hans");
            user2.setPasswordInCleartext("pwd2");
            Ebean.save(user2);

        }

    }

    public User getUserByUsername(String username) {
        return Ebean.find(User.class)
                .where()
                .eq("username", username)
                .findOne();
    }
}
