package pattern.Chain.servletFilter;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ConcreteFileterB implements Filter {
    public void doFilter(Request request, Response response, FilterChain chain) {
        log.info("ConcreteFileterB add  String: \"add a String\"; begin");
        String msg = request.getRequest()+" add a String";
        request.setRequest(msg);
        chain.doFilter(request, response);
        response.setResponse(response.getResponse() + "--->add  String: \"add a String\";");
        log.info("ConcreteFileterB add  String: \"add a String\"; end");
    }
}
