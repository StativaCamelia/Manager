package com.taskManager.taskManager.configuration

import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class Filtres : Filter {
    override fun init(p0: FilterConfig?) {
        TODO("not implemented")
    }

    override fun destroy() {
        TODO("not implemented")
    }

    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        val response = servletResponse as HttpServletResponse
        val request = servletRequest as HttpServletRequest

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081")
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS")
        response.setHeader("Access-Control-Allow-Headers", "Content-Type")
        response.setHeader("Access-Control-Allow-Credentials", true.toString())

        if ("OPTIONS".equals(request.method, ignoreCase = true)) {
            response.status = HttpServletResponse.SC_OK
        } else {
            filterChain.doFilter(servletRequest, servletResponse)
        }
    }
}