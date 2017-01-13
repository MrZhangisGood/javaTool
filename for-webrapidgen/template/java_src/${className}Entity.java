<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${entityPath};

import ${basepackage}.BaseEntity;

import java.io.Serializable;

/**
 * ${table.tableAlias}
 * <#include "/author.include">
 */
public class ${className?substring(3)} extends BaseEntity implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    <@generateFields/>
    <@generateProperties/>
}
<#macro generateFields>
    //columns START
    <#list table.columns as column>
    <#if 
    	!column.pk && 
    	column.columnNameLower!="remark" &&
    	column.columnNameLower!="status" &&
    	column.columnNameLower!="createDate" &&
    	column.columnNameLower!="createBy" &&
    	column.columnNameLower!="updateDate" && 
    	column.columnNameLower!="updateBy" && 
		column.columnNameLower!="isDelete"
    >
    /**
     * ${column.columnAlias!} db_column: ${column.sqlName} 
     */     
    private ${column.javaType} ${column.columnNameLower};
    </#if>
    </#list>
    //columns END
</#macro>



<#macro generateProperties>
    <#list table.columns as column>
	    <#if 
	    !column.pk &&
        column.columnNameLower!="remark" &&
        column.columnNameLower!="status" &&
    	column.columnNameLower!="createDate" && 
    	column.columnNameLower!="createBy" && 
    	column.columnNameLower!="updateDate" && 
    	column.columnNameLower!="updateBy" && 
		column.columnNameLower!="isDelete"
	>
    /**
	 * @Description: 获取${column.columnAlias}
	 * <#include "/author.include">
	 * @return 
	 */
    public ${column.javaType} get${column.columnName}() {
        return this.${column.columnNameLower};
    }
    /**
     * @Description: 设置${column.columnAlias}
     * <#include "/author.include">
     * @param ${column.columnName}
     */
    public void set${column.columnName}(${column.javaType} ${column.columnNameLower}) {
        this.${column.columnNameLower} = ${column.columnNameLower};
    }
    </#if>
    </#list>
</#macro>


