package middlewares;

import play.Logger;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Base64;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class BasicAuthenticationMiddleware extends Action.Simple {

    @Override
    public CompletionStage<Result> call(Http.Context ctx) {
        Optional<String> header = ctx.request().header("Authorization");

        Logger.info("header: " + header);
        if(header.isPresent() && header.get().startsWith("Basic ")) {
            String auth = header.get().substring(6);
            auth = new String(Base64.getDecoder().decode(auth));

            Logger.info("header decoded: " + auth);

            if (auth.equals("user:notes")) {
                return delegate.call(ctx);
            }
        }
        Result result = unauthorized("Authentication required")
                .withHeader("WWW-Authenticate","Basic realm=\"Secure Area\"");

        return CompletableFuture.completedFuture(result);
    }

}
