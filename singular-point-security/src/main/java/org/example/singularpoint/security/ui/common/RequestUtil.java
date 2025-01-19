package org.example.singularpoint.security.ui.common;

import jakarta.servlet.http.HttpServletRequest;

public class RequestUtil {
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equals(requestType);
    }
}
