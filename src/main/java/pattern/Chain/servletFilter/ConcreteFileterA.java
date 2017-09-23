package pattern.Chain.servletFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * descrption: 简单的模拟过滤器替换一个姓名
 * authohr: wangji
 * date: 2017-09-23 13:23
 */
@Slf4j
public class ConcreteFileterA implements Filter {
    public void doFilter(Request request, Response response, FilterChain chain) {
        log.info("ConcreteFileterA replace  wangji to 汪吉 begin");
        String msg = request.getRequest().replace("wangji", "汪吉");
        request.setRequest(msg);
        chain.doFilter(request, response);
        response.setResponse(response.getResponse() + "--->replace  wangji to 汪吉");
        log.info("ConcreteFileterA replace  wangji to 汪吉 end");
    }
}
