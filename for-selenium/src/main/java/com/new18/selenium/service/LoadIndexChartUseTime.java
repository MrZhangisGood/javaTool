package com.new18.selenium.service;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URL;

/**
 * 登录完成后 加载首页图表出来需要多长时间
 */
public class LoadIndexChartUseTime implements Runnable {

    //线程数量
    public int runThread = 3;
    //线程ID
    public int threadId = 0;

    //集结点  -  volatile 原子属性，多线程操作该变量时，不会出现数据错误
    public static volatile int loginRallyPoint = 0;

    protected static final Logger logger = Logger.getLogger(LoadIndexChartUseTime.class);

    public LoadIndexChartUseTime(int runThread, int threadId) {
        this.runThread = runThread;
        this.threadId = threadId;
    }

    @Override
    public void run() {
        try{
            ClassLoader classLoader = getClass().getClassLoader();
            URL url = classLoader.getResource("chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", url.getFile());

            WebDriver dr = new ChromeDriver();
            dr.get("http://data.new18.cn/for-webadmin/login.jsp");
            //dr.get("http://data.new18.cn/for-webadmin/");
            //dr.manage().timeouts().pageLoadTimeout(10, TimeUnit.MILLISECONDS);
            //dr.findElement(By.id("kw")).sendKeys("helloSelenium");
            //dr.findElement(By.className("s_btn")).click();
            WebElement nameElement = dr.findElement(By.id("u_username"));
            nameElement.clear();
            nameElement.sendKeys("zhanglinmin");
            WebElement pwdElement = dr.findElement(By.id("u_password"));
            pwdElement.clear();
            pwdElement.sendKeys("000000");
            //1. 设置可以登录的集结点数量
            loginRallyPoint++;
            while (loginRallyPoint < runThread){
                this.printLog("等待登录");
                Thread.sleep(50);
            }
            this.printLog("开始登录");
            dr.findElement(By.xpath("//*[@id=\"loginBtn\"]")).click();

            //2. 获取加载结束完成时间
            WebElement sucElement = null;
            WebElement frame = null;
            //进入iframe
            while (frame == null){
                try {
                    frame = dr.findElement(By.xpath("/html/body/div[1]/div[3]/iframe[1]"));
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                if(frame != null){
                    break;
                }else{
                    Thread.sleep(10);
                }
            }
            //开始时间
            long t1 = System.currentTimeMillis();
            dr.switchTo().frame(frame);  //dr.switchTo().defaultContent(); 挑出iframe
            sucElement = dr.findElement(By.xpath("//*[@id=\"QueryMask0_0_50_100\"]"));
            while (sucElement.getAttribute("class").indexOf("this_mask")>=0){
                Thread.sleep(10);
            }
            //完成时间
            long t2 = System.currentTimeMillis();
            //
            this.printLog("加载首页报表用时 : "+(t2-t1)+" 毫秒");

            Thread.sleep(5000);

            //关闭浏览器
            dr.quit();
            //打印日志
            this.printLog("退出完成");
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void printLog(String msg){
        logger.info("[线程ID: " + threadId + "]"+msg);
    }

}
