<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?substring(3)?uncap_first>
package ${controllerPath};

import ${basepackage}.BaseController;
import ${entityPath}.${className?substring(3)};
import ${servicePath}.${className?substring(3)}Service;
import ${conditionPath}.${className?substring(3)}Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <br/>
 * <#include "/author.include">
 * @version 1.0
 */
@Controller
@RequestMapping("${classNameLower}")
public class ${className?substring(3)}Controller extends BaseController<${className?substring(3)}, ${className?substring(3)}Sql>{
	
	@Autowired
	private ${className?substring(3)}Service ${classNameLower}Service;

}
