package middlewares;

import models.User;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import services.EbeanUserRepository;

import javax.inject.Inject;
import java.util.Base64;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class SessionAuthenticationMiddleware extends Action.Simple {

    @Inject
    protected EbeanUserRepository userRepository;

    @Override
    public CompletionStage<Result> call(Http.Context ctx) {
        Optional<String> header = ctx.request().header("Authorization");

        if(ctx.session().containsKey("username")) {
            User user = userRepository.getUserByUsername(ctx.session().get("username"));
            return delegate.call(ctx);
        }

        Result result = redirect("/login");
        return CompletableFuture.completedFuture(result);
    }
}
