package com.maocw.servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.maocw.QRcode.BuildQRcode;

/**
 * Servlet implementation class CodeServlet
 */
public class CodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Long time = new Date().getTime();
	String message = time.toString();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	  @Override  
	    protected void service(HttpServletRequest requset, HttpServletResponse response)  
	            throws ServletException, IOException {  
	     
	//        encoder.encoderQRCoder(content, response);  
			// 定义图像buffer
			BufferedImage buffImg = new BufferedImage(400, 400,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = buffImg.createGraphics();
			
		     MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		     Map hints = new HashMap();
		     hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		     try {
				BitMatrix bitMatrix = multiFormatWriter.encode(message, BarcodeFormat.QR_CODE, 200, 200);
			    BuildQRcode.writeToStream(bitMatrix, "jpg", response.getOutputStream());
		     } catch (WriterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }
			// 将图像输出到Servlet输出流中。
			ServletOutputStream sos = response.getOutputStream();
			ImageIO.write(buffImg, "jpeg", sos);
			sos.close();
	    }  

}
