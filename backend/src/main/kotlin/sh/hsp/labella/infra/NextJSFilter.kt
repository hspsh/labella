package sh.hsp.labella.infra

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper

class NextJSFilter : Filter {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        if (request !is HttpServletRequest) {
            chain?.doFilter(request, response)
        }

        val httpRequest = request as HttpServletRequest
        val path = httpRequest.pathInfo

        when {
            path.startsWith("/api") -> chain?.doFilter(request, response)
            path.startsWith("/_next") -> chain?.doFilter(request, response)

            path == "/favicon.ico" ||
                    path == "/index.html" ||
                    path == "/404.html"
            -> chain?.doFilter(request, response)

            else -> {
                chain?.doFilter(request, response)
            }
        }


    }
}