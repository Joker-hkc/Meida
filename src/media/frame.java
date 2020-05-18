package media;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.String;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
/**
 * @des:PICTURE_BROWSER_JAVA
 * @author:JOKER,AWEI
 * @date:2018年11月23日 晚上20:31
 * @modify content:
 * @modifier:
 * @modify date:
 * @version:1.0
 */
public class frame extends JFrame implements MouseWheelListener{
	private static final long serialVersionUID = 8102L;//标识号
	BufferedImage bi = null;
	BufferedImage bib = null;
	static int chang,kuang;
	public int gar=0;//灰度开关
	public int w3;
	public int h3;
	public int cs=1;//保存图片的次数
	public int rotate1=0;//旋转开关
	Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int)screensize.getWidth();
	int height = (int)screensize.getHeight();
	public frame(){
//创建对象
		Container container = getContentPane();
		setTitle("图片查看器-Java");
		JPanel panel =new JPanel();
		JButton b1=new JButton(new ImageIcon("E:\\Java\\java_space\\javase\\demo-media\\src\\tb\\dr.png"));
		JButton b2=new JButton(new ImageIcon("E:\\Java\\java_space\\javase\\demo-media\\src\\tb\\ssz.png"));
		JButton b3=new JButton(new ImageIcon("E:\\Java\\java_space\\javase\\demo-media\\src\\tb\\nsz.png"));
		JButton b6=new JButton(new ImageIcon("E:\\Java\\java_space\\javase\\demo-media\\src\\tb\\bc.png"));
		JButton b4=new JButton(new ImageIcon("E:\\Java\\java_space\\javase\\demo-media\\src\\tb\\hd.png"));
		JButton b5 = new JButton(new ImageIcon("E:\\Java\\java_space\\javase\\demo-media\\src\\tb\\cx2.png"));
		JSlider jSlider =new JSlider();
		JSlider jSlider1 =new JSlider();
		JSlider jSlider2 =new JSlider();
		JLabel jLabe1=new JLabel("亮度:",JLabel.LEFT);
		JLabel jLabe2=new JLabel("饱和度:",JLabel.LEFT);
		JLabel jLabe3=new JLabel("对比度:",JLabel.LEFT);
//将对象添加到面板
		container.add(panel);
		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		panel.add(b4);
		panel.add(b5);
		panel.add(b6);
		panel.add(jSlider);
		panel.add(jSlider1);
		panel.add(jSlider2);
		panel.add(jLabe1);
		panel.add(jLabe2);
		panel.add(jLabe3);
//设置对象属性
		setLayout(null); // 使该窗体取消布局管理器设置
		setBounds((width-960)/2,(height-720)/2 ,1080,720); // 绝对定位窗体的位置与大小
		container.setBackground(Color.white);
		panel.setLayout(null);
		panel.setBounds(0, 0,width,height);
		panel.setBackground(getForeground());
		b1.setBounds(960,10,80,30);
		b2.setBounds(960,50,80,30);
		b3.setBounds(960,90,80,30);
		b6.setBounds(960,130,80,30);
		b4.setBounds(960,170,80,30);
		b5.setBounds(966,582,98,98);
		b1.setToolTipText("打开");
		b2.setToolTipText("正转");
		b3.setToolTipText("反转");
		b4.setToolTipText("灰度");
		b5.setToolTipText("还原");
		b6.setToolTipText("保存");
		jSlider.setBounds(920, 220, 150, 40);
		jSlider1.setBounds(920, 260, 150, 40);
		jSlider2.setBounds(920, 300, 150, 40);
		jLabe1.setBounds(878, 220, 84, 40);
		jLabe2.setBounds(878, 260, 84, 40);
		jLabe3.setBounds(878, 300, 84, 40);
		jLabe1.setOpaque(true);
		jLabe1.setBackground(null);
		jLabe2.setOpaque(true);
		jLabe2.setBackground(null);
		jLabe3.setOpaque(true);
		jLabe3.setBackground(null);
		jSlider.setBackground(null);
		jSlider.setMaximum(30);
		jSlider.setValue(10);
		jSlider.setMinimum(0);
		jSlider1.setBackground(null);
		jSlider1.setMaximum(30);
		jSlider1.setValue(10);
		jSlider1.setMinimum(0);
		jSlider2.setBackground(null);
		jSlider2.setMaximum(30);
		jSlider2.setValue(10);
		jSlider2.setMinimum(0);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//添加监听事件
		this.addMouseWheelListener(this);
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser("E:\\Java\\java_space\\javase\\demo-media\\src\\test_picture");	// 创建文件选择对话框
				FileFilter filter = new FileNameExtensionFilter(
						"图像文件（*.JPG）", "JPG");// 创建文件过滤器
				FileFilter filter1 = new FileNameExtensionFilter(
						"图像文件（*.JPEG）",  "JPEG");
				FileFilter filter2 = new FileNameExtensionFilter(
						"图像文件（*.png）", "png");
				fileChooser.setFileFilter(filter2);
				fileChooser.setFileFilter(filter1);
				fileChooser.setFileFilter(filter);
				fileChooser.showOpenDialog(getContentPane());// 显示文件选择对话框
				try
				{
					bi = ImageIO.read (new File (fileChooser.getSelectedFile ().getAbsolutePath ()));
					bib = ImageIO.read (new File (fileChooser.getSelectedFile ().getAbsolutePath ()));//图片备份
					yasuo();
					repaint ();
				}
				catch (IOException e1)
				{
					e1.printStackTrace ();
				}

			}
		});
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				rotate1++;
				repaint();
			}
		});
		b3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				rotate1--;
				repaint();
			}
		});
		b4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				gary();
				gar=1;
			}
		});
		b5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				yasuo();
				rotate1=0;
				gar=0;
				jSlider.setValue(10);
				jSlider1.setValue(10);
				jSlider2.setValue(10);
				for (int x = 0; x < bi.getWidth(); x++) {
					for (int y = 0; y < bi.getHeight(); y++) {
						int pixel=bi.getRGB(x, y);
						bib.setRGB(x,y,pixel);
						repaint();
					}
				}
			}});
		b6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc=new JFileChooser();
				fc.setSelectedFile(new File("xtp.png"));
				FileSystemView fsv = FileSystemView.getFileSystemView();
				fc.setCurrentDirectory(fsv.getHomeDirectory());
				FileNameExtensionFilter filter=new FileNameExtensionFilter("图像文件（JPG）","JPG");
				FileNameExtensionFilter filter1=new FileNameExtensionFilter("图像文件（png）","png");
				fc.setFileFilter(filter);
				fc.setFileFilter(filter1);
				fc.setMultiSelectionEnabled(false);//不允许多选
				fc.showSaveDialog(null);
				try {
					File outFile = new File(fc.getCurrentDirectory().getPath()+"/xtp"+cs+".jpg");
					File outFile1 = new File(fc.getCurrentDirectory().getPath()+"/xtp"+cs+".png");
					ImageIO.write(bib, "jpg", outFile);
					ImageIO.write(bib, "png", outFile1);
					cs++;
				} catch (IOException e1) {
					System.err.println("请先打开一个图片");
					e1.printStackTrace();
				}finally{
				}
			}
		});
		jSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				float val = jSlider.getValue();// 滑块组件的取值
				light(val);
			}
		});
		jSlider1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				float val = jSlider1.getValue();// 滑块组件的取值
				Saturation(val);
			}
		});
		jSlider2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				float val = jSlider2.getValue();// 滑块组件的取值
				Contrast(val);
			}
		});
	}
	//自定义函数
	public void Contrast(float Contrast) {//对比度
		int pixel;
		for (int x = 0; x < bi.getWidth(); x++) {
			for (int y = 0; y < bi.getHeight(); y++) {
				pixel=bi.getRGB(x, y);
				Color color = new Color(pixel);
				Color color1 = new Color(128,128,128);
				int red=(int)lerp(color1.getRed(),color.getRed(),Contrast/10);if(red>255) red=255; if(red<0) red=0;
				int green=(int)lerp(color1.getGreen(),color.getGreen(),Contrast/10);if(green>255) green=255; if(green<0) green=0;
				int blue=(int)lerp(color1.getBlue(),color.getBlue(),Contrast/10);if(blue>255) blue=255; if(blue<0) blue=0;
				color = new Color(red,green,blue);
				pixel=color.getRGB();
				bib.setRGB(x,y,pixel);

			}
		}
		repaint();
	}
	public void Saturation(double Saturation) {//饱和度
		int pixel;
		for (int x = 0; x < bi.getWidth(); x++) {
			for (int y = 0; y < bi.getHeight(); y++) {
				pixel=bi.getRGB(x, y);
				Color color = new Color(pixel);
				int lights=(int)(color.getRed()*0.2125+color.getGreen()*0.7154+color.getBlue()*0.0721);
				Color color1 = new Color(lights,lights,lights);
				int red=(int)lerp(color1.getRed(),color.getRed(),Saturation/10);if(red>255) red=255; if(red<0) red=0;
				int green=(int)lerp(color1.getGreen(),color.getGreen(),Saturation/10);if(green>255) green=255; if(green<0) green=0;
				int blue=(int)lerp(color1.getBlue(),color.getBlue(),Saturation/10);if(blue>255) blue=255; if(blue<0) blue=0;
				color = new Color(red,green,blue);
				pixel=color.getRGB();
				bib.setRGB(x,y,pixel);

			}
		}
		repaint();
	}
	public double lerp(int a,int b,double t) {//插值
		return a+(b-a)*t;
	}
	public void light(float v)	{//亮度
		int pixel;
		for (int x = 0; x < bi.getWidth(); x++) {
			for (int y = 0; y < bi.getHeight(); y++) {
				pixel=bi.getRGB(x, y);
				Color color = new Color(pixel);
				int red=(int)(color.getRed()*v/10);if(red>255) red=255; if(red<0) red=0;
				int green=(int)(color.getGreen()*v/10);if(green>255) green=255; if(green<0) green=0;
				int blue=(int)(color.getBlue()*v/10);if(blue>255) blue=255; if(blue<0) blue=0;
				color = new Color(red,green,blue);
				pixel=color.getRGB();
				bib.setRGB(x,y,pixel);
			}
		}
		if(gar==1)
		{gary();}
		repaint();
	}
	public void gary(){//灰度
		int pixel;
		BufferedImage gray=new BufferedImage(bi.getWidth(),bi.getHeight(),bi.getType());
		for (int x = 0; x < bi.getWidth(); x++) {
			for (int y = 0; y < bi.getHeight(); y++) {
				pixel=bib.getRGB(x, y);
				int r = (pixel >> 16) & 0xff;
				int g = (pixel >> 8) & 0xff;
				int b = pixel & 0xff;
				int gray1 = (int) (0.3 * r + 0.59 * g + 0.11 * b);;
				int newPixel = colorToRGB(255, gray1, gray1, gray1);
				gray.setRGB(x, y, newPixel);
			}
		}
		bib=gray;

		repaint();
	}
	private static int colorToRGB(int alpha, int red, int green, int blue) {
		int newPixel = 0;
		newPixel += alpha;
		newPixel = newPixel << 8;
		newPixel += red;
		newPixel = newPixel << 8;
		newPixel += green;
		newPixel = newPixel << 8;
		newPixel += blue;
		return newPixel;

	}
	public void yasuo()//压缩图片的显示大小
	{
		int Height1=bi.getHeight();
		int width1=bi.getWidth();
		if( width1 >960 || Height1>720)
		{if(width1<960) {
			double h2=Height1-720;
			h3=(int)720*1000/1193;
			double w2=width1*(1-h2/Height1);
			w3=(int)w2*1000/1189;
		}else {
			double w2=width1-960;
			w3=(int)960*1000/1189;
			double h2=Height1*(1-w2/width1);
			h3=(int)h2*1000/1193;
		}
		}
		else{
			h3=Height1;
			w3=width1;
		}
		chang=w3;
		kuang=h3;
	}
	public void paint ( Graphics g )//画图
	{
		super.paint (g);
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(Math.toRadians(rotate1*90),960/2,720/2);
		g2.drawImage (bib, (960-w3)/2,(720-h3)/2,w3,h3,null);
		g2.dispose ();
	}
	public void mouseWheelMoved(MouseWheelEvent e) {   //放大缩小
		if(e.getWheelRotation()==1){
			chang+=10;
			kuang+=10;
			w3=chang;
			h3=kuang;
			repaint();
		}
		if(e.getWheelRotation()==-1){
			chang-=10;
			kuang-=10;
			w3=chang;
			h3=kuang;
			repaint();
		}
	}
	public static void main(String args[]) {
		new frame();
	}

}