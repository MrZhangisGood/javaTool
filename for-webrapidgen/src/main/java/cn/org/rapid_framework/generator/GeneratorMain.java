package cn.org.rapid_framework.generator;

import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.util.CreateService;


/**
 * 
 * @author badqiu
 * @email badqiu(a)gmail.com
 */

public class GeneratorMain {
	/*
    //权限
	private static String[] tableNames = {"AUT_BTN","AUT_DATA","AUT_DATAG","AUT_DATAG_PER","AUT_MENU","AUT_MENU_BTN","AUT_MENU_TYPE","AUT_MENUG","AUT_MENUG_PER","AUT_ROLE","AUT_ROLE_PER","SYS_USER_PER"};
	//路径
    public static String serverPath = "I:/workspace.idea/crm_data/30_代码/BigDataPlatform/v2.0/开发环境/bigdata/for-server/src/main/java/com/new18/server";
    public static String mapperPath = "I:/workspace.idea/crm_data/30_代码/BigDataPlatform/v2.0/开发环境/bigdata/for-server/src/main/resources";
    public static String controlPath = "I:/workspace.idea/crm_data/30_代码/BigDataPlatform/v2.0/开发环境/bigdata/for-view/src/main/java/com/new18/view/aut";
	//包路径
    //generator.xml
    */
    
	/*
	//报表
    private static String[] tableNames = {"RPT_CDIMENSION","RPT_CMEMBER","RPT_CMETRIC","RPT_DIMENSION","RPT_METRIC","RPT_REPORT"};
    //路径
    public static String serverPath = "I:/workspace.idea/crm_data/30_代码/BigDataPlatform/v2.0/开发环境/bigdata/for-svrrpt/src/main/java/com/new18/svrrpt";
    public static String mapperPath = "I:/workspace.idea/crm_data/30_代码/BigDataPlatform/v2.0/开发环境/bigdata/for-svrrpt/src/main/resources";
    public static String controlPath = "I:/workspace.idea/crm_data/30_代码/BigDataPlatform/v2.0/开发环境/bigdata/for-view/src/main/java/com/new18/view/rpt";
    //包路径
    //generator.xml
	*/
	
	//报表
    private static String[] tableNames = {"RPC_CHART_CDIMENSION","RPC_CHART","RPC_CHART_CMEMBER","RPC_CHART_CMETRIC","RPC_CHART_DIMENSION","RPC_LAYOUT","RPC_CHART_METRIC","RPC_SUBJECT"};
    //路径
    public static String serverPath = "I:/workspace.idea/crm_data/30_代码/BigDataPlatform/v2.0/开发环境/bigdata/for-svrrpc/src/main/java/com/new18/svrrpc";
    public static String mapperPath = "I:/workspace.idea/crm_data/30_代码/BigDataPlatform/v2.0/开发环境/bigdata/for-svrrpc/src/main/resources";
    public static String controlPath = "I:/workspace.idea/crm_data/30_代码/BigDataPlatform/v2.0/开发环境/bigdata/for-view/src/main/java/com/new18/view/rpc";
    //包路径
    //generator.xml
    

    private static String templateSrc = "template";
    
	/**
	 * 请直接修改以下代码调用不同的方法以执行相关生成任务.
	 */
	public static void  main(String[] args) throws Exception {
		for(String tableName : tableNames){
	        GeneratorFacade g = new GeneratorFacade();
	        //打印数据库中的表名称
	        //g.printAllTableNames();
	        //删除生成器的输出目录
	        g.deleteOutRootDir();
	        //通过数据库表生成文件,template为模板的根目录
	        g.generateByTable(tableName, templateSrc);
	        //自动搜索数据库中的所有表并生成文件,template为模板的根目录
	        //g.generateByAllTable("template");
	        //g.generateByClass(Blog.class,"template_clazz");
	        //删除生成的文件
	        //g.deleteByTable("table_name", "template");
	        
	        //创建服务层
	        CreateService.create(tableName, templateSrc, GeneratorProperties.getRequiredProperty("outRoot"));
	        
	        System.out.println("+++CREATE TABLE("+tableName+") SUCCESS [END] ...............+++++++++++++++++++++++++++");
	        
	        //打开文件夹
	        //Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRoot"));
		}
        
	}
}
