package pattern.Chain.servletFilter;

/**
 * descrption:模拟Servlet的Filter
 * authohr: wangji
 * date: 2017-09-23 11:45
 */
public interface Filter {
    //每个Filter均为FilterChain的成员, Filter持有FilterChain的引用，以便调用链条中的各处理者
    void doFilter(Request request, Response response, FilterChain chain);
}
