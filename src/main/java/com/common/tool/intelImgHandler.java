package com.common.tool;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class intelImgHandler {
	
	private static final Log log = LogFactory.getLog(intelImgHandler.class);
	
	/** 
     * 链接超时 
     */  
    private static final int TIME_OUT = 5000;
    
    private static final String USER_AGENT = "Mozilla/5.0 Firefox/26.0";
    private static final int TIMEOUT_SECONDS = 120;
    private static final int POOL_SIZE = 120;
    
    public static void go3(String url) throws Exception {  
        Connection conn= Jsoup.connect(url);  
        Document doc = conn.get();  
        Elements links = doc.select("div.piclist img[src]");  
        for(int i=0;i<links.size();i++){  
            Element element = links.get(i);  
            final String imgUrl = element.attr("src");  
            log.info(imgUrl);  
            Thread.sleep(500);  
            new Thread(new Runnable() {  
                public void run() {  
                    try {  
                        save(imgUrl);  
                    } catch (Exception e) {  
                        // TODO Auto-generated catch block  
                        e.printStackTrace();  
                    }  
                }  
            }).start();  
        }  
    }  
      
    public static void go2(String url) throws Exception {  
        Connection conn= Jsoup.connect(url);  
        Document doc = conn.get();  
        Elements links = doc.select("div.cc a[href]");  
        for(int i=0;i<links.size();i++){  
            Element element = links.get(i);  
            final String dirUrl = "http://www.3lian.com"+element.attr("href");  
            log.info(dirUrl);  
            Thread.sleep(500);  
            new Thread(new Runnable() {  
                public void run() {  
                    try {  
                        Connection conn= Jsoup.connect(dirUrl);  
                        Document doc = conn.get();  
                        Elements images = doc.select("div.mb_jjnr img[src]");  
                        for(int j=0;j<images.size();j++){  
                            Element img = images.get(j);  
                            String imgUrl = img.attr("src");  
                            log.info(imgUrl);  
                            save(imgUrl);  
                        }  
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                }  
            }).start();  
        }  
    }  
      public static void main(String[] args) {
		String url="http://china.nba.com/teamindex/";
		System.out.println(System.getProperty("user.dir"));
		try {
			intelImgHandler.go(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    /** 
     * 图标
     * @param url 
     * @throws Exception 
     */  
    public static void go(String url) throws Exception {  
        // JSOP创建链接  
        Connection conn = Jsoup.connect(url).ignoreContentType(true);
        log.info("图片地址："+url);
        // 请求返回整个文档对象  
        File file=new File("E:\\nba\\team.txt");
        String html=txt2String(file);
        Document doc =Jsoup.parse(html); 
        //Document doc = conn.get();
        doc.setBaseUri("http://china.nba.com");
        System.out.println(doc.html());
        // 选择所有class=zoom 的img标签对象  
        Elements imgs = doc.select("img[class=team-img]");  
        // 循环每个img标签  
        for (int i = 0; i < imgs.size(); i++) {  
            Element img = imgs.get(i);  
            // 取得图片的下载地址  
            String picURL = doc.baseUri() +"/"+ img.attr("src");  
            log.info(picURL);  
            // 保存图片  
            save(picURL);  
        }  
    }  
      
    //<img src="static/image/common/none.gif" file="data/attachment/forum/201105/08/174412nz3jq4z90s33s2t0.jpg" width="770" class="zoom" onclick="zoom(this, this.src)" id="aimg_180565" onmouseover="showMenu({'ctrlid':this.id,'pos':'12'})" alt="img_src_29620.jpg" title="img_src_29620.jpg" />  
    //doc.select("img[class=zoom]")  
    /** 
     * 保存图片 
     * @param url 
     * @param i 
     * @throws Exception 
     */  
    static void save(String url) throws Exception {  
        String fileName = url.substring(url.lastIndexOf("/"));  
        //String filePath = SystemContext.getProperty("download.img.url") + "/" + fileName;
        System.out.println(System.getProperty("user.dir"));
        String filePath = System.getProperty("user.dir")+"/src/main/webapp/image/nba/" + fileName;
        BufferedOutputStream out = null;  
        byte[] bit = getByte(url);  
        if (bit.length > 0) {  
            try {  
                out = new BufferedOutputStream(new FileOutputStream(filePath));  
                out.write(bit);  
                out.flush();  
                log.info("Create File success! [" + filePath + "]");  
            } finally {  
                if (out != null)  
                    out.close();  
            }  
        }  
    }  
      
    /** 
     * 获取图片字节流 
     * @param uri 
     * @return 
     * @throws Exception 
     */  
    static byte[] getByte(String uri) throws Exception {  
       // HttpClient client = new DefaultHttpClient();
    	


    	RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT_SECONDS * 1000)
    	        .setConnectTimeout(TIMEOUT_SECONDS * 1000).build();
    	CloseableHttpClient client = HttpClientBuilder.create().setUserAgent(USER_AGENT).setMaxConnTotal(POOL_SIZE).setMaxConnPerRoute(POOL_SIZE).setDefaultRequestConfig(defaultRequestConfig).build();
    	
    	
    	
    	HttpGet httpget = new HttpGet(uri);
        httpget.setHeader("Referer", "http://china.nba.com");

        System.out.println("executing request " + httpget.getURI());
        
        try {  
        	CloseableHttpResponse response = client.execute(httpget); 
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {  
                    return EntityUtils.toByteArray(entity);  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            client.close();
        }  
        return new byte[0];  
    }
    
	static String txt2String(File file) {
		String result = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				result = result + "\n" + s;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
