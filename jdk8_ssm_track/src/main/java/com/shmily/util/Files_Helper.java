package com.shmily.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.*;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2017/1/10.
 */
public class Files_Helper {
    private static Logger log = LoggerFactory.getLogger(Files_Helper.class);
    /**
     * 私有化构造函数，不允许当前类被实例
     */
    private Files_Helper(){
        throw new Error("this class Cannot be instance");
    }

    /**
     *
     * @param req
     * @param multipartFile (spring获取文件)
     * @param filePath 上传文件路径（file_manager/Upload）
     * @return
     */
    public static String fileUpload_transferTo_spring(HttpServletRequest req, MultipartFile multipartFile,String filePath){
        if(null != multipartFile){
            //get file suffix
            String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            //返回绝对路径用于保存
            String absolutePath = getAndSetAbsolutePath(req,filePath,suffix);
            //相对路径用于传递给前端
            String relativePath = getRelativePath(filePath,suffix);
            try{
                //save File
                multipartFile.transferTo(new File(absolutePath));
            }catch (IOException e){
                log.error("上传文件抛出异常，异常信息为："+e);
                return null;
            }
            return relativePath;
        }else
            return null;
    }

    /**
     * user stream type save files
     * @param req
     * @param multipartFile
     * @param filePath
     * @return
     */
    public static String fileUpload_stream(HttpServletRequest req,MultipartFile multipartFile,String filePath){
        //get file suffix
        if(null != multipartFile){
            String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            String relativePath = getRelativePath(filePath,suffix);
            String absolutePath = getAndSetAbsolutePath(req,filePath,suffix);
            try{
                InputStream is = multipartFile.getInputStream();
                //输出到指定路径
                FileOutputStream fos = new FileOutputStream(absolutePath);
                byte[] buffer = new byte[4096];//create buffer(缓冲区)
                long fileSize = multipartFile.getSize();
                if(fileSize <= buffer.length){
                    buffer = new byte[(int)fileSize];
                }
                int line = 0;
                while ((line = is.read(buffer)) > 0 ){
                    fos.write(buffer,0,line);
                }
            }catch (IOException e){
                log.error("获取输入输出流失败");
                return null;
            }
            return relativePath;
        }else
            return null;
    }

    /**
     * file download
     * @param req
     * @param resp
     * @param filPath
     */
    public static void FilesDownload_stream(HttpServletRequest req, HttpServletResponse resp,String filPath){
        String realPath = getServerPath(req,filPath);
        File file = new File(realPath);
        String fileName = file.getName();
        InputStream inputStream;
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(file);
            inputStream = new BufferedInputStream(fis);
            long fileSize = fis.getChannel().size();//通过nio的channel获取文件大小（相比available更精确）
            byte[] buffer = new byte[(int)fileSize];
            inputStream.read(buffer);
            inputStream.close();
            resp.reset();
            //去掉文件名称中的中文，先去掉空格部分，再以utf-8编码，用于在下载时显示给客户端的下载文件名称
            resp.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.replaceAll(" ", "").getBytes("utf-8"), "iso8859-1"));
            resp.addHeader("Content-Length"," "+file.length());
            OutputStream os = resp.getOutputStream();
            os.write(buffer);
            os.flush();
            os.close();
            resp.setContentType("application/octet-stream");
        }catch(Exception e){
            log.error("【FilesDownload_stream】异常,"+e);
        }
    }


    //返回当前参数中路径的绝对路径（真实路径）
    public static String getServerPath(HttpServletRequest request, String filePath) {
        return request.getSession().getServletContext().getRealPath(filePath);
    }

    //return a dir that named date of today ; example:20170112
    public static String getDataPath(){
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    /**
     * 判断文件的有效性
     * @param savePath 保存路径
     * isDirectory判断目录是否存在   exists 判断文件是否存在
     */
    public static void checkDir(String savePath){
        File dir = new File(savePath);
        if(!dir.exists() || !dir.isDirectory()){
            dir.mkdirs();//只能创建一级目录，且当前创建目录的上一级目录必须存在
        }
    }

    /**
     * 返回一个UUId命名的文件名称
     * @param suffix 后缀
     * @return
     */
    public static String getUUIDName(String suffix){
        return UUID.randomUUID().toString()+suffix;
    }

    /**
     * 上传文件的绝对路径
     * //return server absolute path（real path） the style is  “server absolute path/DataPath/UUIDName”filePath example "/files/Upload"
     * File.separator:在Windows下的路径分隔符和Linux下的路径分隔符是不一样的，当直接使用绝对路径时，
     * 跨平台会暴出“No such file or diretory”的异常。考虑跨平台时使用
     * @param req
     * @param filePath
     * @param suffix
     * @return
     */
    public static String getAndSetAbsolutePath(HttpServletRequest req,String filePath,String suffix){
        String savePath = getServerPath(req,filePath)+File.separator+getDataPath()+File.separator;
        //实际情况下都是上传到某一个磁盘，防止上传到项目导致项目文件一致增加
        //String savePath = "E:/Eclipse/AA/";
        checkDir(savePath);//check if the path has exist if not create it
        return savePath+getUUIDName(suffix);
    }

    //get the relative path of files style is “/filePath/DataPath/UUIDName”filePath example "/files/Upload"

    /**
     * 上传文件的(获取相对路径)
     * get the relative path of files style is “/filePath/DataPath/UUIDName”filePath example "/files/Upload"
     * @param filePath
     * @param suffix
     * @return
     */
    public static String getRelativePath(String filePath, String suffix) {
        return filePath + File.separator + getDataPath() + File.separator + getUUIDName(suffix);//example:/files/Upload/20160912/
    }
}
