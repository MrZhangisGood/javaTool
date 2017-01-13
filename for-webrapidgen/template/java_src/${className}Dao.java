<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?substring(3)?uncap_first>
package ${daoPath};

import ${basepackage}.BaseDao;
import ${basepackage}.annotation.MybatisDao;
import ${entityPath}.${className?substring(3)};
import ${conditionPath}.${className?substring(3)}Sql;

/**
 * <br/>
 * <#include "/author.include">
 * @version 1.0
 */
@MybatisDao
public interface ${className?substring(3)}Dao extends BaseDao<${className?substring(3)}, ${className?substring(3)}Sql> {
	
}