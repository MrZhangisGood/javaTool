package cn.org.rapid_framework.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.org.rapid_framework.generator.GeneratorMain;

/** 
 * @author zhanglm@joyplus.com.cn 
 * @date 2015年2月4日 上午10:07:16   
 * @version 1.0
 */
public class CreateService {
    
    /*private static String modelPath = ".service";
    private static String className_ = "${className}";
    private static String varName_ = "${varName}";
    private static String createDate_ = "${createDate}";
    private static String controllerPath_ = "S{controllerPath}";
    private static String conditionPath_ = "S{conditionPath}";
    private static String entityPath_ = "S{entityPath}";
    private static String servicePath_ = "S{servicePath}";
    private static String daoPath_ = "S{daoPath}";*/

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    
    public static void create(String tableName, String templateSrc, String outPath) throws Exception{
        String[] tables = tableName.split("_");
        
        String className = "";
        String varName = "";
        int fromIndex = 1;
        for (int i = fromIndex; i < tables.length; i++) {
            String lower = tables[i].toLowerCase();
            String first = lower.substring(0, 1);
            className += first.toUpperCase()+lower.substring(1);
            if(i == fromIndex){
                varName = lower;
            }else {
                varName += first.toUpperCase()+lower.substring(1);
            }
        }
        
        //生成controller , service , dao 层
        /*File filePath = new File(templateSrc + modelPath);
        File[] files = filePath.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                File file = files[i];
                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuffer content = new StringBuffer();
                String temp = "";
                while ((temp = reader.readLine()) != null) {
                    temp = temp.replace(className_, className);
                    temp = temp.replace(varName_, varName);
                    temp = temp.replace(createDate_, createDate);
                    temp = temp.replace(controllerPath_, GeneratorMain.controllerPath);
                    temp = temp.replace(conditionPath_, GeneratorMain.conditionPath);
                    temp = temp.replace(entityPath_, GeneratorMain.entityPath);
                    temp = temp.replace(servicePath_, GeneratorMain.servicePath);
                    temp = temp.replace(daoPath_, GeneratorMain.daoPath);
                    content.append(temp + "\r\n");
                }
                reader.close();
                String sourcePath = null;
                String medolPath = file.getName().replace(className_, "").replace(".java", "").toLowerCase();
                if(medolPath.equals("controller")){
                    sourcePath = GeneratorMain.controlPath + "/" + medolPath;
                }else{
                    sourcePath = GeneratorMain.serverPath + "/" + medolPath;
                }
                writeToFile(sourcePath, content.toString(), file.getName().replace(className_, className), false);
            }
        }*/
        
        //复制到entity xxxSql xxxByField Mapper层
        File resultEntityPath = new File(outPath + "/java_src");
        File[] efiles = resultEntityPath.listFiles();
        for(File file : efiles){
            //复制到controller 层
            if(file.getName().contains("Controller")){
                copyFile(file, new File(GeneratorMain.controlPath + "/controller/" + className+"Controller.java"), false);
        	}
            //复制到service 层
            else if(file.getName().contains("Service")){
                copyFile(file, new File(GeneratorMain.serverPath + "/service/" + className+"Service.java"), false);
        	}
            //复制到dao 层
            else if(file.getName().contains("Dao")){
                copyFile(file, new File(GeneratorMain.serverPath + "/dao/" + className+"Dao.java"), false);
        	}
            //复制到entity 层
            else if(file.getName().contains("Entity")){
                copyFile(file, new File(GeneratorMain.serverPath + "/entity/" + className+".java"), true);
        	}
            //复制到xxxByField 层
        	else if(file.getName().contains("Set")){
                copyFile(file, new File(GeneratorMain.serverPath + "/condition/" + className+"Set.java"), true);
        	}
            //复制到xxxSql 层
        	else if(file.getName().contains("Sql")){
                copyFile(file, new File(GeneratorMain.serverPath + "/condition/" + className+"Sql.java"), true);
        	}
            //复制到Mabase 层(覆盖操作)
        	else if(file.getName().contains("Mabase")){
        		StringBuffer sb = new StringBuffer();
        		//替换_{ -> {
        		BufferedReader reader = new BufferedReader(new FileReader(file));
        		String temp;
        		while ((temp = reader.readLine()) != null) {
        			sb.append(temp.replace("_{", "{").toString()+"\r\n");
        		}
        		reader.close();

        		//复制到项目中
        		writeToFile(GeneratorMain.mapperPath, sb.toString(), className+"Mabase.xml", true);
        	}
            //复制到Mapper 层 (不覆盖操作)
        	else if(file.getName().contains("Mapper")){
        		StringBuffer sb = new StringBuffer();
        		BufferedReader reader = new BufferedReader(new FileReader(file));
        		String temp;
        		while ((temp = reader.readLine()) != null) {
        			sb.append(temp+"\r\n");
        		}
        		reader.close();

        		//复制到项目中
        		writeToFile(GeneratorMain.mapperPath, sb.toString(), className+"Mapper.xml", false);
        	}
        }

    }
    

    /**
     * 写入文件, 存在的情况下不写入
     * @author zhanglinmin@new18.cn
     */
    public static void writeToFile(String outPath, String content, String fileName, boolean cover) throws IOException {
        File file = new File(outPath + "/" + fileName);
        if(cover || !file.exists()){
            FileWriter fWriter = new FileWriter(file);
            fWriter.write(content);
            fWriter.flush();
            fWriter.close();
        }
    }
    

    // 复制文件
    public static void copyFile(File sourceFile, File targetFile, boolean cover) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            if(cover || !targetFile.exists()){
                // 新建文件输出流并对它进行缓冲
                outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

                // 缓冲数组
                byte[] b = new byte[1024 * 5];
                int len;
                while ((len = inBuff.read(b)) != -1) {
                    outBuff.write(b, 0, len);
                }
                // 刷新此缓冲的输出流
                outBuff.flush();
            }
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }

}
