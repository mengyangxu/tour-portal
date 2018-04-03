package com.xmy.portal.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;  
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;
import java.util.logging.Logger;

/** 
 * JAVA FTPClient 工具类 
 * 
 * commons-net-1.4.1.jar PFTClinet jar包 
 * 
 * @author : hpp 
 */  
public class FtpClientUtils{  
  
    /** 
     * Description: 向FTP服务器上传文件 
     * @Version1.0 
     * @param url FTP服务器hostname 
     * @param port FTP服务器端口 
     * @param username FTP登录账号 
     * @param password FTP登录密码 
     * @param path FTP服务器保存目录 
     * @param filename 上传到FTP服务器上的文件名 
     * @param input 输入流 
     * @return 成功返回true，否则返回false 
     */  
    public static boolean uploadFile(  
            String url,//FTP服务器hostname  
            int port,//FTP服务器端口  
            String username, // FTP登录账号  
            String password, //FTP登录密码  
            String path, //FTP服务器保存目录  
            String filename, //上传到FTP服务器上的文件名  
            InputStream input // 输入流  
    ) {  
        boolean success = false;  
        FTPClient ftp = new FTPClient();  
        try {  
            int reply;  
            ftp.connect(url, port);//连接FTP服务器  
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
            ftp.login(username, password);//登录  
            reply = ftp.getReplyCode();  
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);  
            if (!FTPReply.isPositiveCompletion(reply)) {  
                ftp.disconnect();  
                System.out.println("FTP服务器 拒绝连接");  
                return success;  
            }  
            ftp.changeWorkingDirectory(path);  
            ftp.storeFile(filename, input);  
  
            input.close();  
            ftp.logout();  
            success = true;  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (Exception e) {
            e.printStackTrace();
        }finally
        {
            if (ftp.isConnected()) {  
                try {  
                    ftp.disconnect();  
                } catch (IOException ioe) {  
                }  
            }  
        }  
        return success;  
    }  
  
    /** 
     * 删除文件 
     * @param fileName 要删除的文件地址 
     * @return true/false 
     * @throws IOException 
     */  
    public static boolean delete(String fileName, FTPClient ftpClient) throws IOException {  
        return ftpClient.deleteFile(fileName);  
    }  
  
  
    /** 
     * 下载文件到指定目录 
     * @param ftpFile 文件服务器上的文件地址 
     * @param dstFile 输出文件的路径和名称 
     * @throws Exception 
     */  
    public static void downLoad(String ftpFile, String dstFile, FTPClient ftpClient) throws Exception {  
        if (StringUtils.isBlank(ftpFile)) {  
            throw new RuntimeException("ftpFile为空");  
        }  
        if (StringUtils.isBlank(dstFile)) {  
            throw new RuntimeException("dstFile为空");  
        }  
        File file = new File(dstFile);  
        FileOutputStream fos = new FileOutputStream(file);  
        ftpClient.retrieveFile(ftpFile, fos);  
        fos.flush();  
        fos.close();  
    }  
  
    /** 
     * 从文件服务器获取文件流 
     * @param ftpFile 文件服务器上的文件地址 
     * @return {@link InputStream} 
     * @throws IOException 
     */  
    public static InputStream retrieveFileStream(String ftpFile, FTPClient ftpClient) throws IOException {  
        if (StringUtils.isBlank(ftpFile)) {  
            throw new RuntimeException("ftpFile为空");  
        }  
        return ftpClient.retrieveFileStream(ftpFile);  
    }  
  
    public static void main (String[] args){
        /*try {
            FileInputStream in=new FileInputStream(new File("G:\\img/2.jpg"));
            boolean flag = uploadFile("118.25.102.29", 21, "root", "celfc020639.", "/usr/local/tomcat8-upload/webapps/ROOT/img/", "333.jpg", in);
            //boolean flag = uploadFile("118.25.102.29", 21, "ubuntu", "celfc020639.", "/home/ubuntu/img/", "333.jpg", in);
            System.out.println(flag);
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        }  */

        try {
            URL url = new URL("http://localhost:8080/img/3.jpg");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            InputStream is = new FileInputStream(new File("G:\\img/5.jpg"));
            byte[] bytes = new byte[10];
            int length = 0;
            while (-1 != (length = is.read(bytes))) {
                os.write(bytes, 0, length);
                os.flush();
            }
            is.close();
            os.close();
        }catch (IOException e){
            e.printStackTrace();
        } finally {

        }

       /* URL url= new URL("http://localhost:8080/img/5.jpg");
        URLConnection conn = url.openConnection();
        InputStream is = conn.getInputStream();
        OutputStream os = new FileOutputStream(new File("G:\\img/5.jpg"));
        byte[] bytes = new byte[10];
        int length = 0;
        while(-1!=(length = is.read(bytes))){
            os.write(bytes,0,length);
            os.flush();
        }
        is.close();
        os.close();
*/

        /*try {
            Socket s = new Socket("localhost", 8080);//连接到服务端的端口
            OutputStream out = s.getOutputStream();
            upload("G:\\img/5.jpg","localhost:8080",out);
        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }

    private static void upload(String filePath, String host, OutputStream out)
            throws IOException{
        FileInputStream fis = new FileInputStream(filePath);
        //模拟http put请求上传文件
        String head = "PUT " + filePath + " HTTP/1.1\r\n" +
                "Host: " + host + "\r\n" +
                "Connection: keep-alive\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml,*/*\r\n" +
                "Accept-Encoding: gzip, deflate\r\n" +
                "Accept-Language: zh-CN\r\n" +
                "Content-Length: " + fis.available() + "\r\n" +
                "\r\n";
        out.write(head.getBytes());
        byte[] buf = new byte[1024];
        int len = -1;
        while((len = fis.read(buf)) != -1){
            out.write(buf, 0, len);
        }
        fis.close();
    }

    public static String uploadFile(MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException{
        if(file!=null){
            //获取上传文件的原始名称
            String originalFilename = file.getOriginalFilename();
            String newFileName ="";
            String pic_path;
            // 上传图片
            if ( originalFilename != null && originalFilename.length() > 0) {
                //获取Tomcat服务器所在的路径
                String tomcat_path = System.getProperty( "user.dir" );
                System.out.println(tomcat_path);
                //获取Tomcat服务器所在路径的最后一个文件目录
                String bin_path = tomcat_path.substring(tomcat_path.lastIndexOf("\\")+1,tomcat_path.length());
                System.out.println(bin_path);
                //若最后一个文件目录为bin目录，则服务器为手动启动
                if(("bin").equals(bin_path)){//手动启动Tomcat时获取路径为：D:\Software\Tomcat-8.5\bin
                    //获取保存上传图片的文件路径
                    pic_path = tomcat_path.substring(0,System.getProperty( "user.dir" ).lastIndexOf("\\")) +"\\webapps"+"\\pic_file\\";
                }else{//服务中自启动Tomcat时获取路径为：D:\Software\Tomcat-8.5
                    pic_path = tomcat_path+"\\webapps"+"\\pic_file\\";
                }
                // 新的图片名称
                newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
                // 新图片
                File newFile = new File(pic_path + newFileName);
                // 将内存中的数据写入磁盘
                file.transferTo(newFile);
            }
            return newFileName;
        }else{
            return null;
        }
    }
}  