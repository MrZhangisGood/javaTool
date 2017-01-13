<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${conditionPath};

import ${basepackage}.enums.EBaseEntity;
import ${basepackage}.util.VTool;

import ${entityPath}.${className?substring(3)};

/**
 * ${table.tableAlias}
 * <#include "/author.include">
 */
public class ${className?substring(3)}Set extends ${className?substring(3)} {

    public ${className?substring(3)}Set() {
        super.setIsDelete(EBaseEntity.USE.ordinal());
    }
    public ${className?substring(3)}Set(int isDelete) {
        super.setIsDelete(isDelete);
    }
    public ${className?substring(3)}Set(Integer page, Integer rows) {
        //分页
        if(!VTool.isEmpty(page) && !VTool.isEmpty(rows)){
            super.setFrom((page - 1) * rows);
            super.setRows(rows);
        }
        super.setIsDelete(EBaseEntity.USE.ordinal());
    }
    public ${className?substring(3)}Set(Integer page, Integer rows, int isDelete) {
        //分页
        if(!VTool.isEmpty(page) && !VTool.isEmpty(rows)){
            super.setFrom((page - 1) * rows);
            super.setRows(rows);
        }
        super.setIsDelete(isDelete);
    }

    /** START */
    /**
	 * @Description: 排除当前的id
	 */
    public ${className?substring(3)}Set nId(java.lang.String id) {
        if(!VTool.isEmpty(id)){
            super.setId(id);
        }
        return this;
    }
    <#list table.columns as column>
   	<#if !column.pk>
    /**
	 * @Description: 查询字段${column.columnAlias}
	 */
    public ${className?substring(3)}Set _${column.columnName}(${column.javaType} ${column.columnNameLower}) {
        if(!VTool.isEmpty(${column.columnNameLower})){
            super.set${column.columnName}(${column.columnNameLower});
        }
        return this;
    }
	</#if>
   	</#list>
    /** END */


}
