package LOGIN_UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.BoundedRangeModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LOGIN extends JFrame {
	
	/*설정 값들 : 창크기나, 폰트 크기등 
	
	프레임크기 600,500 / 중앙 패널 400,350
	
	
	*/
	//사용 컴포넌트들
	private JTextField ID;
	private JPasswordField PW;
	private JButton Login_bt, Join_bt;
	private ImageIcon LOGO_img, login_img, join_img;
	private JLabel LOGO, ID_l, PW_l;
	private Font font = new Font("맑은 고딕", Font.ROMAN_BASELINE + Font.PLAIN, 18);
	private JPanel c = (JPanel) getContentPane(),
			login_pane;
	private Graphics2D Line;
	
	private FocusListener fe = new Focus_event();
	private KeyListener Ke = new Focus_event();
	private ActionListener Ae = new Action_event();
	
	public static void main(String[] args) {
		// TODO 로그인 창
		new LOGIN();
		

	}
	
	
	//GUI 설정
	public LOGIN() {
		
		//배경 패널 설정
		c.setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(600, 500);
		setTitle("레시피북 프로그램");
		
		c.setBorder(new EmptyBorder(5, 5, 5, 5));
		c.setBackground(new Color(0xFFFBF5));
		
		//중앙 패널 설정
		login_pane = new Login_pane();
		login_pane.setBackground(new Color(0xF7EFE5));
		login_pane.setLayout(null);
		
		//중앙 패널이 화면 중앙 오도록 설정
		c.setBorder(BorderFactory.createEmptyBorder(50, 75, 50, 75));
		c.add(login_pane, BorderLayout.CENTER);
				
		//로고 이미지 설정
		LOGO_img = new ImageIcon("./image/LOGO.png");
		LOGO = new JLabel(LOGO_img);
		LOGO.setBounds(50, 50, 350, 100);
		LOGO.setVerticalTextPosition(JLabel.CENTER);
		LOGO.setHorizontalTextPosition(JLabel.CENTER);
		login_pane.add(LOGO);
		
		//ID PW 문구 출력
		ID_l = new JLabel(" ID: ");
		ID_l.setFont(font);
		ID_l.setBounds(LOGO.getX()+50, LOGO.getY()+LOGO.getHeight()+20, 50, 30);
		ID_l.setVerticalTextPosition(JLabel.CENTER);
		ID_l.setHorizontalTextPosition(JLabel.CENTER);
		login_pane.add(ID_l);
		
		PW_l = new JLabel("PW:");
		PW_l.setFont(font);
		PW_l.setBounds(ID_l.getX(), ID_l.getY()+ID_l.getHeight()+10, ID_l.getWidth(), ID_l.getHeight());
		PW_l.setVerticalTextPosition(JLabel.CENTER);
		PW_l.setHorizontalTextPosition(JLabel.CENTER);
		login_pane.add(PW_l);
		
		//ID, PW 텍스트 필드 설정
		ID = new JTextField("ID");
		ID.setBounds(ID_l.getX()+40, ID_l.getY(), 200, ID_l.getHeight());
		ID.setFont(font);
		ID.setBackground(new Color(0xF7EFE5));
		ID.setHorizontalAlignment(JTextField.CENTER);
		ID.setOpaque(false);
		ID.setBorder(null);
		login_pane.add(ID);
		
		PW = new JPasswordField("PW");
		PW.setBounds(PW_l.getX()+40, PW_l.getY(), ID.getWidth(), PW_l.getHeight());
		PW.setFont(font);
		PW.setHorizontalAlignment(JTextField.CENTER);
		PW.setBackground(new Color(0xF7EFE5));
		PW.setOpaque(false);
		PW.setBorder(null);
		PW.setEchoChar((char) 0);
		PW.addFocusListener(fe);
		PW.addKeyListener(Ke);
		login_pane.add(PW);
				
		//로그인, 회원가입 버튼
		join_img = new ImageIcon("image/join.png");
		Join_bt = new JButton(join_img);
		Join_bt.setBounds(PW_l.getX()+30, PW_l.getY()+PW.getHeight()+20, 100, 30);
		Join_bt.setOpaque(false);
		Join_bt.setBorder(null);
		Join_bt.setBackground(null);
		Join_bt.setContentAreaFilled(false);
		login_pane.add(Join_bt);
		
		login_img = new ImageIcon("image/login.png");
		Login_bt = new JButton(login_img);
		Login_bt.setBounds(Join_bt.getX()+Join_bt.getWidth()+10, Join_bt.getY(), Join_bt.getWidth(), Join_bt.getHeight());
		Login_bt.setOpaque(false);
		Login_bt.setBorder(null);
		Login_bt.setBackground(null);
		Login_bt.setContentAreaFilled(false);
		login_pane.add(Login_bt);
		
		
		c.setFocusable(true);
		c.requestFocus();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	//ID, PW 입력란 밑에 줄긋기 위한 선언
	class Login_pane extends JPanel{
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(new Color(0x7743DB));
			g.drawLine(ID.getX(), ID.getY()+ID.getHeight(), ID.getX()+ID.getWidth(), ID.getY()+ID.getHeight());
			
			g.drawLine(PW.getX(), PW.getY()+PW.getHeight(), PW.getX()+PW.getWidth(), PW.getY()+PW.getHeight());
			
		}
	}

	class Focus_event implements FocusListener, KeyListener {

		@Override
		public void focusGained(FocusEvent e) {
			if(e.getSource() == ID) {
				
			}
			
			if(e.getSource() == PW) {
				PW.setText("");
				PW.setEchoChar('*');	
			}			
		}

		@Override
		public void focusLost(FocusEvent e) {
				if(e.getSource() == ID) {
				
			}
			
			if(e.getSource() == PW && PW.getText().equals("")) {
				PW.setText("PW");
				PW.setEchoChar((char)0);
			}	
		}
		
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			
			int Key_code = e.getKeyCode();
			
			if(Key_code == KeyEvent.VK_ENTER) {
				Login_bt.action(null, e);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {}
		
		
		
	}
	
	
	class Action_event implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource() == Login_bt) {
				
			}
			if(e.getSource() == Join_bt) {
				
			}
			
			
		}
		
	}
	

}
