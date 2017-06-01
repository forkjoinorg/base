package org.forkjoin.jdbckit.core;


import org.forkjoin.jdbckit.core.grid.Columns;
import org.forkjoin.jdbckit.core.grid.GridPageResult;

import java.util.Map;


public interface SqlQueryDao {

	GridPageResult<Map<String, Object>> query(String sql, int page, int pageSize);

	PageResult<Map<String, Object>> queryData(Columns columns, String sql, int page, int pageSize);

}
