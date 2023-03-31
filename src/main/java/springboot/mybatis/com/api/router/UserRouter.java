package springboot.mybatis.com.api.router;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import springboot.mybatis.com.api.handler.user.UserHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;

@Component
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> userRouters(UserHandler userHandler){
        String url = "/user";
        return RouterFunctions
                .route(POST(url +"/list"), userHandler ::getAll)
                .andRoute(POST(url +"/detail"), userHandler::getById);
    }
}
