package springboot.mybatis.com.api.handler.user;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import springboot.mybatis.com.api.service.UserService;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
@RequiredArgsConstructor
public class UserHandler {

    private final UserService userService;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ServerResponse.ok().body(request.bodyToMono(JsonNode.class).flatMap(userService::getAll), JsonNode.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        return ServerResponse.ok().body(request.bodyToMono(JsonNode.class).flatMap(userService::getUserById), JsonNode.class);
    }
}
