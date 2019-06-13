package com.gymclub.api.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * @author Xiaoming.
 * Created on 2019/06/09 17:06.
 */
public final class RequestUtil {
    public static int parseIntParam(ServerRequest request, String paramName){
        final Optional<String> param = request.queryParam(paramName);
        if (param.isPresent()) {
            return Integer.parseInt(param.get());
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
