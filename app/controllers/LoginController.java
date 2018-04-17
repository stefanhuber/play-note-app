package controllers;

import middlewares.SessionAuthenticationMiddleware;
import models.User;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import services.EbeanUserRepository;

import javax.inject.Inject;

public class LoginController extends Controller {

    @Inject
    protected FormFactory factory;

    @Inject
    protected EbeanUserRepository userRepository;

    public Result index() {
        return ok(views.html.login.render());
    }

    public Result login() {
        DynamicForm form = factory.form().bindFromRequest();

        User user = userRepository.getUserByUsername(form.get("username"));
        if (user != null &&
            user.comparePasswords(form.get("password"))) {
            session().put("username", user.getUsername());
            return redirect("/");
        }

        return redirect("/login");
    }

    @With(SessionAuthenticationMiddleware.class)
    public Result logout() {
        session().clear();
        return redirect("/login");
    }

    @With(SessionAuthenticationMiddleware.class)
    public Result passwordForm() {
        return ok(views.html.password.render());
    }

    @With(SessionAuthenticationMiddleware.class)
    public Result password() {
        User user = userRepository.getCurrentUser();
        DynamicForm form = factory.form().bindFromRequest();

        if (user.comparePasswords(form.get("old_password")) &&
            form.get("new_password").equals(form.get("new_password_repeat"))) {
            flash().put("success", "Password changed successfully");
            user.setPasswordInCleartext(form.get("new_password"));
            userRepository.saveUser(user);
            return redirect("/");
        } else {
            flash().put("error", "Password inputs incorrect, please try again.");
            return redirect("/password");
        }
    }

}
