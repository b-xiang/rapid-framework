/*
 * Alipay.com Inc.
 * Copyright (c) 2004 All Rights Reserved.
 */
package ${basepackage}.daointerface;
import org.springframework.dao.DataAccessException;
import ${basepackage}.query.*;

import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import com.iwallet.biz.common.util.PageList;
import com.iwallet.biz.common.util.money.Money;

/**
 * 
 *
 */
public interface ${tableConfig.tableClassName}DAO {

<#list tableConfig.sqls as sql>

	/**
	 * ${sql.remarks!}
	 * sql:
	 * <pre>
	 ${sql.executeSql}
	 * </pre>	 
	 */
	public <@generateResultClassName sql/> ${sql.operation}(<@generateOperationArguments sql/>) throws DataAccessException;
</#list>

}



