package com.new18.selenium.service;

import com.new18.core.util.PropsUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URL;

/**
 * 改变图表条件后需要多长时间
 */
public class ChangeChartConUseTime implements Runnable {

    //登录地址
    public static String WEB_LOGINADDR = null;
    //登录用户名
    public static String WEB_USERNAME = null;
    //登录密码
    public static String WEB_PASSWORD = null;

    //线程数量
    public int runThread = 0;
    //线程ID
    public int threadId = 0;

    //集结点参数
    public static int rallyPoint = 0;
    //用于还原集结点的参数
    public static int restoreNum = 0;

    protected static final Logger logger = Logger.getLogger(ChangeChartConUseTime.class);

    static {
        WEB_LOGINADDR = PropsUtil.getProp("server.web.loginaddr");
        WEB_USERNAME = PropsUtil.getProp("server.web.username");
        WEB_PASSWORD = PropsUtil.getProp("server.web.password");
    }

    public ChangeChartConUseTime(int runThread, int threadId) {
        this.runThread = runThread;
        this.threadId = threadId;
    }

    @Override
    public void run() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", url.getFile());
        WebDriver dr = new ChromeDriver();
        dr.get(WEB_LOGINADDR);
        try{
            //定义公用变量
            long t1 = 0;
            long t2 = 0;

            WebElement nameElement = dr.findElement(By.id("u_username"));
            nameElement.clear();
            nameElement.sendKeys(WEB_USERNAME);
            WebElement pwdElement = dr.findElement(By.id("u_password"));
            pwdElement.clear();
            pwdElement.sendKeys(WEB_PASSWORD);
            //1. 集结点自增
            this.rallyPointAdd();
            this.printInfoLog("等待登录");
            this.rallyPointWait();
            this.printInfoLog("开始登录");
            dr.findElement(By.xpath("//*[@id=\"loginBtn\"]")).click();

            //2. 获取加载结束完成时间
            this.printInfoLog("Find iframe");
            WebElement frame = this.findElementByBlock(By.xpath("/html/body/div[1]/div[3]/iframe[1]"), dr);
            //开始时间
            t1 = System.currentTimeMillis();
            //进入iframe
            dr.switchTo().frame(frame);  //dr.switchTo().defaultContent(); 挑出iframe
            //查找加载完成后需要判断的div
            WebElement sucElement = this.findElementByBlock(By.xpath("//*[@id=\"QueryMask0_0_50_100\"]"), dr);
            while (sucElement.getAttribute("class").indexOf("this_mask")>=0){
                Thread.sleep(10);
            }
            //完成时间
            t2 = System.currentTimeMillis();
            this.printInfoLog("加载首页报表用时 : " + (t2 - t1) + " 毫秒");

            //点击 展开图表工具栏
            dr.findElement(By.xpath("//*[@id=\"ChangeBtn0_0_50_100\"]/li[1]/span")).click();

            this.rallyPointAdd();
            this.printInfoLog("等待进入重新查询图表的");
            this.rallyPointWait();
            this.printInfoLog("等待完成，即将开始改变条件");
            Thread.sleep(5000);
            this.printInfoLog("开始改变图表条件");
            dr.findElement(By.xpath("//*[@id=\"ChangeBtn0_0_50_100\"]/li[2]/span")).click();
            dr.findElement(By.xpath("//*[@id=\"beforeDate\"]")).sendKeys("" + (365 + threadId));
            WebElement charDivt = dr.findElement(By.xpath("//*[@id=\"Filter0_0_50_100\"]/div/div/div[1]/div[2]/div[1]/i"));
            this.clickWebElement(charDivt, dr);

            Thread.sleep(2000);
            dr.findElement(By.xpath("//*[@id=\"ChangeBtn0_0_50_100\"]/li[3]/span")).click();

            //开始时间
            t1 = System.currentTimeMillis();
            //等待10毫秒，打开遮罩
            Boolean openMask = false;
            Thread.sleep(3);
            while (sucElement.getAttribute("class").indexOf("this_mask")>=0){
                openMask = true;
                Thread.sleep(10);
            }
            //完成时间
            t2 = System.currentTimeMillis();
            this.printInfoLog("改变图表条件报表重新加载用时 : " + (t2 - t1) + " 毫秒"+" (openMask:"+openMask+")");

        }catch (Exception e) {
            logger.error(e.getMessage(), e);
        }finally {
            try{
                //等待一分钟退出
                Thread.sleep(10000);
            }catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            //关闭浏览器
            dr.quit();
            //打印日志
            this.printInfoLog("退出完成");
        }
    }

    /**
     * 集结点 自增
     */
    private synchronized void rallyPointAdd() {
        rallyPoint++;
    }
    /**
     * 集结点 等待
     */
    private void rallyPointWait() {
        while (rallyPoint < runThread){
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        //还原集结点数据（以便下次使用）
        this.rallyPointRestore();
    }
    /**
     * 还原集结点 自增
     */
    private synchronized int restoreNumAdd() {
        return ++restoreNum;
    }
    /**
     * 集结点 还原集结点数据（以便下次使用）
     */
    private void rallyPointRestore() {
        if (this.restoreNumAdd() == runThread){
            rallyPoint = 0;
            restoreNum = 0;
        }
    }

    private WebElement findElementByBlock(By by, WebDriver dr) {
        WebElement frame = null;
        while (frame == null){
            try {
                frame = dr.findElement(by);
            } catch (Exception e) {
                printErrorLog("页面iframe找不到");
            }
            if(frame != null){
                break;
            }else{
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return frame;
    }

    /**
     * 元素点击
     * @return
     */
    private void clickWebElement(WebElement weElement, WebDriver wdDriver) {
        // Scroll the browser to the element's Y position
        ((JavascriptExecutor) wdDriver).executeScript("window.scrollTo(0," + weElement.getLocation().y + ")");
        // Click the element
        int iAttempts = 0;
        while (iAttempts < 5) {
            try {
                weElement.click();
                break;
            } catch (Exception e) {
                printErrorLog("元素 : "+weElement+" 点击失败");
            }
            iAttempts++;
        }
    }

    private void printInfoLog(String msg){
        logger.info("[线程ID: " + threadId + "]"+msg);
    }

    private void printErrorLog(String msg){
        logger.error("[线程ID: " + threadId + "]"+msg);
    }

}
