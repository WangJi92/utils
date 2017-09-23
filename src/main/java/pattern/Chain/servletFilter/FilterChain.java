package pattern.Chain.servletFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * descrption: 过滤链的抽象
 * authohr: wangji
 * date: 2017-09-23 11:44
 */
public class FilterChain {

    List<Filter> filters = new ArrayList<Filter>();
    int index = 0;

    // 链式编程
    public FilterChain addFilter(Filter filter){
        filters.add(filter);
        return this;
    }

    public void doFilter(Request request, Response response) {
        if(index == filters.size()) return;
        Filter filter = filters.get(index);
        index++;
        filter.doFilter(request, response, this);//拥有过滤链的抽象
    }
}
