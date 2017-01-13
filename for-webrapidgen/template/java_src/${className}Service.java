<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?substring(3)?uncap_first>
package ${servicePath};

import ${basepackage}.BaseService;
import ${daoPath}.${className?substring(3)}Dao;
import ${entityPath}.${className?substring(3)};
import ${conditionPath}.${className?substring(3)}Sql;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <br/>
 * <#include "/author.include">
 * @version 1.0
 */
@Service
public class ${className?substring(3)}Service extends BaseService<${className?substring(3)}, ${className?substring(3)}Sql> {

	protected static final Logger logger = Logger.getLogger(${className?substring(3)}Service.class);

	@Autowired
	private ${className?substring(3)}Dao ${classNameLower}Dao;

}

