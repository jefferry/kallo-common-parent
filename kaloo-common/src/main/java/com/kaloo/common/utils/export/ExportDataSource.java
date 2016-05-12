package com.kaloo.common.utils.export;

import java.util.List;

/**
 * 描述: 数据导出,数据源
 */
public interface ExportDataSource<T> {
	List<T> getData();
}
