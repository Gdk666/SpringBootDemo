package Guoz.config.shiro;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 自定义过滤器
 */
@WebFilter(filterName="customFilter",urlPatterns="/*")
public class CustomFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("CustomFilter过滤器销毁");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
						 FilterChain chain) throws IOException, ServletException {
		if (!(request instanceof HttpServletRequest)) {
			chain.doFilter(request, response);
			return;
		}
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		if (httpRequest.isRequestedSessionIdFromURL()) {
			HttpSession session = httpRequest.getSession();
			if (session != null) {
				session.invalidate();
			}
		}
		HttpServletResponseWrapper wrappedResponse = new HttpServletResponseWrapper(
				httpResponse) {
			@Override
			public String encodeRedirectUrl(String url) {
				return url;
			}

			@Override
			public String encodeRedirectURL(String url) {
				return url;
			}

			@Override
			public String encodeUrl(String url) {
				return url;
			}

			@Override
			public String encodeURL(String url) {
				return url;
			}
		};
		chain.doFilter(request, wrappedResponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("CustomFilter初始化......");
	}


}