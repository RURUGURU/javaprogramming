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
	//메인프레임클래스
	private JLabel lb_title = new JLabel();
	//게임타이틀표시용라벨
	private JLabel lb_time = new JLabel();
	//시간표시용라벨
	private JLabel lb_check = new JLabel();
	//숫자표시용라벨
	private JButton bt_start = new JButton("START");
	//게임시작버튼
	private JButton bt_reset = new JButton("RESET");
	//게임리셋버튼
	private JButton bt_score = new JButton("Score");
	//점수 버튼
	
	SimpleDateFormat time_format;
	//시간값을변환하기위한포맷
	static String show_time;
	//진행시간값을 받아들일 문자열
	
	public long start_time, current_time;
	//게임시작시간, 컴퓨터시간, 
	public static long actual_time;
	//실제게임진행시간(정적변수)

	boolean time_run = false;
	Thread th;
	//스레드 객체생성
	
	ImagePanel sc;
	//게임화면을표시할패널클래스접근키
	
	MainFrame() {
		//화면에띄울프레임정의
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
		//게임라벨, 버튼및게임화면용패널설정

		Container con = this.getContentPane();
		con.setLayout(null);

		lb_title.setBounds(250, 10, 300, 30);
		lb_title.setFont(new Font("Default", Font.BOLD, 20));
		con.add(lb_title);
		//프레임에 표시할 제목

		lb_time.setBounds(400, 50, 150, 30);
		lb_time.setFont(new Font("Default", Font.BOLD, 20));
		con.add(lb_time);
		//프레임에표시할시간라벨

		lb_check.setBounds(100, 30, 400, 100);
		lb_check.setFont(new Font("Default", Font.BOLD, 20));
		con.add(lb_check);
		//프레임에표시할 다음숫자라벨

		bt_start.setBounds(100, 520, 100, 30);
		bt_start.setFont(new Font("Default", Font.BOLD, 20));
		con.add(bt_start);
		//프레임에 표시할 시작라벨
		
		bt_score.setBounds(250, 520, 100, 30);
		bt_score.setFont(new Font("Default", Font.BOLD, 20));
		con.add(bt_score);
		//프레임에 표시할 점수라벨

		bt_reset.setBounds(400, 520, 100, 30);
		bt_reset.setFont(new Font("Default", Font.BOLD, 20));
		con.add(bt_reset);
		//프레임에표시할리셋버튼

		sc = new ImagePanel();
		sc.setBounds(100, 100, 410, 410);
		con.add(sc);
		//게임화면용패널
	}
	public void start() {
		//프레임내실행시 기본내용
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//프레임종료용X버튼활성화
		this.addMouseListener(this);
		//프레임내마우스동작활성화
		bt_start.addMouseListener(this);
		// start 버튼에 마우스동작활성화
		bt_reset.addMouseListener(this);
		// reset 버튼에 마우스동작활성화
		bt_score.addMouseListener(this);
		// score 버튼에 마우스동작활성화
		
		th = new Thread(this);
		th.start();
		//스레드시작

		time_format = new SimpleDateFormat("HH:mm:ss.SSS");
		//시간포맷설정
		lb_time.setText("00:00:00.000");
		lb_title.setText("1 to 50 Game");
		lb_check.setText("다음숫자: 1");
		//라벨텍스트내용설정

		sc.gameStart(false);
		//게임은대기상태로
	}
	
	public void run() {
		while(true) {
			try {
				repaint();
				TimeCheck();
				Thread.sleep(15);
				//무한스레드돌리기(중간에 탈출을 시켜 값을 출력함)
			}
			catch (Exception e) {
			}

								
			//-------------------------파일입력------------------------------------------
			if (sc.check==51.000 && sc.time_ck){
				//50을릌릭할때(51이 인가), System.out.println(show_time);
				try{
					FileWriter record = new FileWriter("score.txt",true);
					record.write("- 이름 : "+Game1to50.Name+"--시간 : "+show_time+" - "+"\r\n");
					//주어진 형식을 기록
					record.close();
					System.out.println("Clear time: "+show_time);
					//시간을 MainFrame내에서 받아와서 파일입력
				
					
				} catch (IOException e){}
				sc.time_ck = false;
				//예외처리
			}
			//----------------------------------------------------------------------------
		}
	}
	public void TimeCheck(){
		if (time_run) {
			current_time = System.currentTimeMillis();
			actual_time = current_time - start_time;
			//게임시작 버튼을 눌렀을 때의 시간값과
			//실제 시간값으로 게임진행시간 계산
			sc.countDown((int) actual_time / 1000);
			//카운트다운표시용시간값전송(시간의 기본단위는 밀리초기 때문에)
			
			if (!sc.game_start && sc.check <= 50) {
				//게임시작되면 게임 진행시간갱신
				show_time = time_format.format(actual_time - 32403000);
				lb_time.setText(show_time);
				String checks=Integer.toString(sc.check);
				//wrapper 클래스를 사용하여 변환
				lb_check.setText("다음숫자 : "+checks);
				//숫자를 받아서 다음 숫자를 표시한다.
			}
		}
		if ( sc.check > 51){
			sc.ClearTime(lb_time.getText());
			//숫자50까지 클릭이끝나면 메세지 띄울준비
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == bt_start) {
			//게임시작버튼
			
			if (!time_run && !sc.game_start ) {
				start_time = System.currentTimeMillis();
				//시작버튼눌렀을시시간값받기

				sc.rect_select.clear();
				time_run = true;
				sc.gameStart(true);
				//게임및시간세팅 후 시작
			}
		} else if (e.getSource() == bt_reset) {
			//게임초기화버튼
			start_time = 0;
			lb_time.setText("00:00:00.000");
			sc.rect_select.clear();
			sc.countDown(0);
			time_run = false;
			sc.gameStart(false);
			sc.check = 0;
			//게임초기화
		}else if (e.getSource() == bt_score) {
			//점수버튼
			Reader r=new Reader();
			//리더 객체 생성
			r.print();
		   //이때까지 저장된 스코어값  출력	
		}
		 
	}
	
	public void mousePressed(MouseEvent e) {}
	
	public void mouseReleased(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}
}
