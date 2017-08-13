package org.forkjoin.apikit.core;


import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * page 是1开始，第一页表示 1
 *
 * @param <V>
 */
public class PageResult<V> extends Result<List<V>> {
    public static final int START = 1;

    /**
     *
     */
    public static final <V> PageResult<V> createPage(final int count, final int page, final int pageSize) {
        return createPage(count, page, pageSize, null);
    }

    public static final <V> PageResult<V> createPageByAllList(final int page, final int pageSize, final List<V> list) {
        int count = list.size();
        PageResult<V> result = createPage(count, page, pageSize, null);
        int start = result.getStart();
        if (start < count) {
            List<V> ds = new ArrayList<>(list.subList(result.getStart(), result.getStart() + Math.min(count - start, pageSize)));
            result.setData(ds);
        }
        return result;
    }

    public static final <V extends List> ArrayList<V> createListByFromTo(final int from, final int to, final List<V> list) {
        int count = list.size();
        ArrayList<V> result = new ArrayList<V>();
        int start = (from - 1) > 0 ? (from - 1) : 0;
        int end = (to > count) ? (count - 1) : (to - 1);
        if (end >= start) {
            for (int i = start; i <= end; i++) {
                result.add(list.get(i));
            }
        }
        return result;
    }

    public static final <V> PageResult<V> createPage(final int count, final int page, final int pageSize, final List<V> list) {
        return new PageResult<V>(list, count, page, pageSize);
    }

    private int count;
    private int page;
    private int pageSize;

    public PageResult() {
    }

    public PageResult(int count) {
        this.count = count;
    }

    public PageResult(int count, int page, int pageSize) {
        super(SUCCESS);
        this.count = count;
        this.page = page;
        this.pageSize = pageSize;
    }

    public PageResult(List<V> data, int count, int page, int pageSize) {
        super(SUCCESS, data);
        this.count = count;
        this.page = page;
        this.pageSize = pageSize;
    }

    /**
     * 返回一共多少页
     */
    @Transient
    public int getPageCount() {
        int m = count % pageSize;
        int s = count / pageSize;
        return m > 0 ? ++s : s;
    }

    public <R> PageResult<R> map(Function<? super V, R> mapper) {
        List<V> data = getData();
        if (data != null) {
            List<R> list = data.stream().map(mapper).collect(Collectors.toList());
            return PageResult.createPage(getCount(), getPage(), getPageSize(), list);
        }
        return null;
    }

    /**
     * 返回开始位置
     */
    @Transient
    public int getStart() {
        return (page - 1) * pageSize;
    }

    @Transient
    public boolean isHasPrev() {
        return page > 1;
    }

    @Transient
    public boolean isHasNext() {
        return page < getPageCount();
    }


    /**
     * 返记录集的总数
     */

    public int getCount() {
        return count;
    }

    /**
     * 返回当前页
     */
    public int getPage() {
        return page;
    }

    /**
     * 返回页长度
     */
    public int getPageSize() {
        return pageSize;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "count=" + count +
                ", page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}