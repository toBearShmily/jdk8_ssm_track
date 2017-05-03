package com.shmily.util.imageUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class NewImageUtils {


	/**
	 * 
	 * @Title: getFileImage
	 * @Description: 读取源文件 转换为java.awt.Image类型的数据
	 * @param file 源文件
	 * @return Image
	 */
	public static Image getFileImage(File file){
		Image image = null;
		if(Common.getFileExtension(file.getName()).equals("bmp")){
			FileInputStream in = null;
			try {
				in = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			image = BMPLoader.read(in);
		}else{
			try {
				image = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return image;
	}



	/**
	 * 
	 * @Title: getMyBufferedImage
	 * @Description: 构建一个预定义图像类型的BufferedImage
	 * @param file 需要被叠加的图片文件
	 * @param scale 缩放等级 等于1 不进行缩放
	 * @return BufferedImage
	 */
	private static BufferedImage getMyBufferedImage(File file, float scale){

		Image image = getFileImage(file);// 得到Image对象
		BufferedImage buffImg = null;
		try {
			buffImg = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//宽跟高
		int width = (int) (image.getWidth(null));
		int height = (int) (image.getHeight(null));
		// 构建一个预定义图像类型的BufferedImage
		buffImg = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		// 创建Graphics2D对象，用在BufferedImage对象上绘图
		Graphics2D g2d = buffImg.createGraphics();

		//设置背景透明-wxb
		/*System.out.println("---------- 增加下面的代码使得背景透明 -----------------");
		buffImg = g2d.getDeviceConfiguration().createCompatibleImage(width,height, Transparency.TRANSLUCENT);
		g2d.dispose();
		g2d = buffImg.createGraphics();
		System.out.println("---------- 背景透明代码结束 -----------------");*/

		// 设置图形上下文的当前颜色为透明色
		Color transparent = new Color(0, 0, 0, 0);
		g2d.setColor(transparent);
		// 填充指定的矩形区域
		g2d.fillRect(0, 0, width, height);
		
		g2d.drawImage(image, 0, 0, width, height, null);
		if(scale != 1.0f){
			//缩放图片
			BufferedImage filteredBufImage =new BufferedImage((int) (width*scale), (int) (height*scale),BufferedImage.TYPE_INT_RGB); //过滤后的图像
			AffineTransform transform = new AffineTransform(); //仿射变换对象
			transform.setToScale(scale, scale); //设置仿射变换的比例因子	
			AffineTransformOp imageOp = new AffineTransformOp(transform, null);//创建仿射变换操作对象			
			imageOp.filter(buffImg, filteredBufImage);//过滤图像，目标图像在filteredBufImage
			buffImg = filteredBufImage;
		}
		return buffImg;
	}

	/**
	 * 算法选择
	 * @return RenderingHints的一个对象
	 */
	private static RenderingHints getMyRenderingHints(){
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,// 抗锯齿提示键。
				RenderingHints.VALUE_ANTIALIAS_ON);// 抗锯齿提示值——使用抗锯齿模式完成呈现。
		rh.put(RenderingHints.KEY_TEXT_ANTIALIASING ,// 文本抗锯齿提示键。
				RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB);//要求针对 LCD 显示器优化文本显示
		rh.put(RenderingHints.KEY_ALPHA_INTERPOLATION,// Alpha 插值提示值
				RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY );// Alpha 插值提示值——选择偏重于精确度和视觉质量的 alpha 混合算法
		rh.put(RenderingHints.KEY_RENDERING,// 呈现提示键。
				RenderingHints.VALUE_RENDER_QUALITY);// 呈现提示值——选择偏重输出质量的呈现算法
		rh.put(RenderingHints.KEY_STROKE_CONTROL ,//笔划规范化控制提示键。
				RenderingHints.VALUE_STROKE_NORMALIZE);//几何形状应当规范化，以提高均匀性或直线间隔和整体美观。
		rh.put(RenderingHints.KEY_COLOR_RENDERING  ,//颜色呈现提示键。
				RenderingHints.VALUE_COLOR_RENDER_QUALITY );// 用最高的精确度和视觉质量执行颜色转换计算。
		return rh;
	}

	/**
	 * 
	 * @Title: printWatemark
	 * @Description: 添加水印
	 * @param g2d 由源文件生成的Graphics
	 * @param img 需要叠加的水印Image
	 * @param x 以右下角为原点 水印放置的X坐标
	 * @param y 以右下角为原点 水印放置的Y坐标
	 * @param width 水印图片的宽度
	 * @param height 水印图片的高度
	 * @param alpha 水印的透明度 选择值从0.0~1.0: 完全透明~完全不透明
	 * @return void
	 */
	private static void printWatemark(Graphics2D g2d,Image img,int x,int y,int width, int height,float alpha){
		//在图形和图像中实现混合和透明效果
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
				alpha));
		g2d.drawImage(img, x, y, width, height, null);
	}


	/**
	 * 
	 * @Title: watermark
	 * @Description: 生成水印并返回java.awt.image.BufferedImage
	 * @param file 源文件(图片)
	 * @param waterFile 水印文件(图片)
	 * @param x 距离右下角的X偏移量
	 * @param y 距离右下角的Y偏移量
	 * @param alpha 透明度, 选择值从0.0~1.0: 完全透明~完全不透明
	 * @return BufferedImage
	 */
	public static BufferedImage watermark(File file, File waterFile,int x,int y, float alpha)throws IOException {
		BufferedImage buffImg = getMyBufferedImage(file, 1.0f);
		BufferedImage waterImg = null;
		//try {
			waterImg = ImageIO.read(waterFile);
		/*} catch (IOException e) {
			e.printStackTrace();
		}*/
		// 创建Graphics2D对象，用在BufferedImage对象上绘图
		Graphics2D g2d = buffImg.createGraphics();
		g2d.setRenderingHints(getMyRenderingHints());
		
		int sourceImgWidth  = buffImg.getWidth();
		int sourceImgHeight = buffImg.getHeight();

		int waterImgWidth = waterImg.getWidth();
		int waterImgHeight = waterImg .getHeight();


		printWatemark(g2d,waterImg,sourceImgWidth-waterImgWidth-x,sourceImgHeight-waterImgHeight-y,waterImgWidth,waterImgHeight,alpha);
		g2d.dispose();// 释放图形上下文使用的系统资源
		return buffImg;
	}

	/**
	 * 输出水印图片
	 * @param buffImg 图像加水印之后的BufferedImage对象
	 * @param savePath 图像加水印之后的保存路径
	 */
	private void generateWaterFile(BufferedImage buffImg, String savePath)throws IOException {
		int temp = savePath.lastIndexOf(".") + 1;
		//try {
			ImageIO.write(buffImg, savePath.substring(temp), new File(savePath));
		/*} catch (IOException e1) {
			e1.printStackTrace();
		}*/
	}

	public static void getSignFile(String backageImageUrl, String signtrueUrl,int x,int y) throws IOException{
		NewImageUtils newImageUtils = new NewImageUtils();
		// 对图像加水印
		BufferedImage buffImg = NewImageUtils.watermark(new File(backageImageUrl), new File(signtrueUrl),x,y,1.0f);
		// 输出水印图片
		newImageUtils.generateWaterFile(buffImg, backageImageUrl);
	}
	public static void main(String[] args)throws IOException {
		String sourceFilePath = "C://Users//Administrator//Desktop//file.jpg";
		String waterFilePath = "C://Users//Administrator//Desktop//change.png";
		String saveFilePath = "C://Users//Administrator//Desktop//newFile.png";
		NewImageUtils newImageUtils = new NewImageUtils();
		// 对图像加水印
		BufferedImage buffImg = NewImageUtils.watermark(new File(sourceFilePath), new File(waterFilePath),50,10,1.0f);
		// 输出水印图片
		newImageUtils.generateWaterFile(buffImg, saveFilePath);
	}
}
