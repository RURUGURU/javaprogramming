import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame implements MouseListener, Runnable {
	//����������Ŭ����
	private JLabel lb_title = new JLabel();
	//����Ÿ��Ʋǥ�ÿ��
	private JLabel lb_time = new JLabel();
	//�ð�ǥ�ÿ��
	private JLabel lb_check = new JLabel();
	//����ǥ�ÿ��
	private JButton bt_start = new JButton("START");
	//���ӽ��۹�ư
	private JButton bt_reset = new JButton("RESET");
	//���Ӹ��¹�ư
	private JButton bt_score = new JButton("Score");
	//���� ��ư
	
	SimpleDateFormat time_format;
	//�ð�������ȯ�ϱ���������
	static String show_time;
	//����ð����� �޾Ƶ��� ���ڿ�
	
	public long start_time, current_time;
	//���ӽ��۽ð�, ��ǻ�ͽð�, 
	public static long actual_time;
	//������������ð�(��������)

	boolean time_run = false;
	Thread th;
	//������ ��ü����
	
	ImagePanel sc;
	//����ȭ����ǥ�����г�Ŭ��������Ű
	
	MainFrame() {
		//ȭ�鿡�������������
		init();
		start();
		setTitle("1 to 50 Game");
		setSize(600, 600);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int) (screen.getWidth() / 2 - getWidth() / 2);
		int ypos = (int) (screen.getHeight() / 2 - getHeight() / 2);
		setLocation(xpos, ypos);
		setResizable(false);
		setVisible(true);
	}
	public void init() {
		//���Ӷ�, ��ư�װ���ȭ����гμ���

		Container con = this.getContentPane();
		con.setLayout(null);

		lb_title.setBounds(250, 10, 300, 30);
		lb_title.setFont(new Font("Default", Font.BOLD, 20));
		con.add(lb_title);
		//�����ӿ� ǥ���� ����

		lb_time.setBounds(400, 50, 150, 30);
		lb_time.setFont(new Font("Default", Font.BOLD, 20));
		con.add(lb_time);
		//�����ӿ�ǥ���ҽð���

		lb_check.setBounds(100, 30, 400, 100);
		lb_check.setFont(new Font("Default", Font.BOLD, 20));
		con.add(lb_check);
		//�����ӿ�ǥ���� �������ڶ�

		bt_start.setBounds(100, 520, 100, 30);
		bt_start.setFont(new Font("Default", Font.BOLD, 20));
		con.add(bt_start);
		//�����ӿ� ǥ���� ���۶�
		
		bt_score.setBounds(250, 520, 100, 30);
		bt_score.setFont(new Font("Default", Font.BOLD, 20));
		con.add(bt_score);
		//�����ӿ� ǥ���� ������

		bt_reset.setBounds(400, 520, 100, 30);
		bt_reset.setFont(new Font("Default", Font.BOLD, 20));
		con.add(bt_reset);
		//�����ӿ�ǥ���Ҹ��¹�ư

		sc = new ImagePanel();
		sc.setBounds(100, 100, 410, 410);
		con.add(sc);
		//����ȭ����г�
	}
	public void start() {
		//�����ӳ������ �⺻����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//�����������X��ưȰ��ȭ
		this.addMouseListener(this);
		//�����ӳ����콺����Ȱ��ȭ
		bt_start.addMouseListener(this);
		// start ��ư�� ���콺����Ȱ��ȭ
		bt_reset.addMouseListener(this);
		// reset ��ư�� ���콺����Ȱ��ȭ
		bt_score.addMouseListener(this);
		// score ��ư�� ���콺����Ȱ��ȭ
		
		th = new Thread(this);
		th.start();
		//���������

		time_format = new SimpleDateFormat("HH:mm:ss.SSS");
		//�ð����˼���
		lb_time.setText("00:00:00.000");
		lb_title.setText("1 to 50 Game");
		lb_check.setText("��������: 1");
		//���ؽ�Ʈ���뼳��

		sc.gameStart(false);
		//�����������·�
	}
	
	public void run() {
		while(true) {
			try {
				repaint();
				TimeCheck();
				Thread.sleep(15);
				//���ѽ����嵹����(�߰��� Ż���� ���� ���� �����)
			}
			catch (Exception e) {
			}

								
			//-------------------------�����Է�------------------------------------------
			if (sc.check==51.000 && sc.time_ck){
				//50���j���Ҷ�(51�� �ΰ�), System.out.println(show_time);
				try{
					FileWriter record = new FileWriter("score.txt",true);
					record.write("- �̸� : "+Game1to50.Name+"--�ð� : "+show_time+" - "+"\r\n");
					//�־��� ������ ���
					record.close();
					System.out.println("Clear time: "+show_time);
					//�ð��� MainFrame������ �޾ƿͼ� �����Է�
				
					
				} catch (IOException e){}
				sc.time_ck = false;
				//����ó��
			}
			//----------------------------------------------------------------------------
		}
	}
	public void TimeCheck(){
		if (time_run) {
			current_time = System.currentTimeMillis();
			actual_time = current_time - start_time;
			//���ӽ��� ��ư�� ������ ���� �ð�����
			//���� �ð������� ��������ð� ���
			sc.countDown((int) actual_time / 1000);
			//ī��Ʈ�ٿ�ǥ�ÿ�ð�������(�ð��� �⺻������ �и��ʱ� ������)
			
			if (!sc.game_start && sc.check <= 50) {
				//���ӽ��۵Ǹ� ���� ����ð�����
				show_time = time_format.format(actual_time - 32403000);
				lb_time.setText(show_time);
				String checks=Integer.toString(sc.check);
				//wrapper Ŭ������ ����Ͽ� ��ȯ
				lb_check.setText("�������� : "+checks);
				//���ڸ� �޾Ƽ� ���� ���ڸ� ǥ���Ѵ�.
			}
		}
		if ( sc.check > 51){
			sc.ClearTime(lb_time.getText());
			//����50���� Ŭ���̳����� �޼��� ����غ�
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == bt_start) {
			//���ӽ��۹�ư
			
			if (!time_run && !sc.game_start ) {
				start_time = System.currentTimeMillis();
				//���۹�ư�������ýð����ޱ�

				sc.rect_select.clear();
				time_run = true;
				sc.gameStart(true);
				//���ӹ׽ð����� �� ����
			}
		} else if (e.getSource() == bt_reset) {
			//�����ʱ�ȭ��ư
			start_time = 0;
			lb_time.setText("00:00:00.000");
			sc.rect_select.clear();
			sc.countDown(0);
			time_run = false;
			sc.gameStart(false);
			sc.check = 0;
			//�����ʱ�ȭ
		}else if (e.getSource() == bt_score) {
			//������ư
			Reader r=new Reader();
			//���� ��ü ����
			r.print();
		   //�̶����� ����� ���ھ  ���	
		}
		 
	}
	
	public void mousePressed(MouseEvent e) {}
	
	public void mouseReleased(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}
}
