package com.forkjoin.core.dao;


import java.util.Map;

import com.forkjoin.core.PageResult;
import com.forkjoin.core.dao.grid.Columns;
import com.forkjoin.core.dao.grid.GridPageResult;


public interface SqlQueryDao {

	GridPageResult<Map<String, Object>> query(String sql, int page, int pageSize);

	PageResult<Map<String, Object>> queryData(Columns columns,String sql, int page, int pageSize);

}
