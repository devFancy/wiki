package com.internal.team.wiki.global.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.internal.team.wiki.global.dto.ErrorResponse;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@JsonSerialize(using = ApiResultResponseSerializer.class)
public class ApiResultResponse<T> {

    private final boolean success;
    private final Map<String, T> data = new HashMap<>();
    private final Map<String, ErrorResponse> error = new HashMap<>();

    public ApiResultResponse(final boolean success,
                             final T data, final String domainName,
                             final ErrorResponse error, final String errorMessage) {
        this.success = success;
        if (data != null) {
            this.data.put(domainName, data);
        }
        if (error != null) {
            this.error.put(errorMessage, error);
        }
    }

    public static <T> ApiResultResponse<T> success(final T data, final String domainName) {
        return new ApiResultResponse<>(true, data, domainName, null, null);
    }

    public static ApiResultResponse<ErrorResponse> failure(final ErrorResponse errorResponse, final String error) {
        return new ApiResultResponse<>(false, null, null, errorResponse, error);
    }
}