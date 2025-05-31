package com.learning.myfitapp.common.web.error;


import java.util.List;


public record ApiErrorResponse(String errorCode, String message, List<ApiParamErrorField> errors) {
}
