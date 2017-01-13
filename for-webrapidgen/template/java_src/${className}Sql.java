<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${conditionPath};

import ${basepackage}.BaseSql;
import ${basepackage}.util.VTool;

/**
 * ${table.tableAlias}
 * <#include "/author.include">
 */
public class ${className?substring(3)}Sql extends BaseSql {

    public ${className?substring(3)}Sql() {
        super("${table.sqlName}");
    }
    public ${className?substring(3)}Sql(int isDelete) {
        super("${table.sqlName}", isDelete);
    }
    public ${className?substring(3)}Sql(Integer page, Integer rows) {
        super("${table.sqlName}", page, rows);
    }
    public ${className?substring(3)}Sql(Integer page, Integer rows, int isDelete) {
        super("${table.sqlName}", page, rows, isDelete);
    }

    /**
     * 添加条件 ( sql() )
     * @param condition
     * @return
     */
    public ${className?substring(3)}Sql _Con(String condition) {
        super.putCondition(condition);
        return this;
    }

    /** START 把字段拼接到sql语句 ( sql() )*/
    <#list table.columns as column>
	<#if !column.pk>
    /**
	 * @Description: 查询字段${column.columnAlias}
	 */
    public ${className?substring(3)}Sql _${column.columnName}(${column.javaType} ${column.columnNameLower}, Object... objs) {
        if(!VTool.isEmpty(${column.columnNameLower})){
            super.addToCondition("${column.sqlName}", ${column.columnNameLower}, objs);
        }
        return this;
    }
    /**
	 * @Description: 查询字段 NOT 或者 NOT NULL
	 */
    public ${className?substring(3)}Sql n${column.columnName}(${column.javaType} ${column.columnNameLower}, Object... objs) {
    	super.addToCondition("${column.sqlName}", ${column.columnNameLower}, objs);
        return this;
    }
	</#if>
	</#list>
    /** END 把字段拼接到sql语句 ( sql() )*/


}
