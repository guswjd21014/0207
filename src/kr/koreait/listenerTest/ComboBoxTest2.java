package kr.koreait.listenerTest;

import java.awt.Choice;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ComboBoxTest2 extends JFrame implements ActionListener {
	
	JLabel topLabel;			// 윈도우 상단의 제목 레이블
	
	JPanel comboPanel;			// 콤보 박스와 보기, 삭제 버튼이 올라갈 패널
//	Choice comboBox;			// 콤보 박스(java.awt 패키지)
	JComboBox<String> comboBox;	// 콤보 박스(javax.swing 패키지)
	JButton showButton;			// 보기 버튼
	JButton deleteButton;		// 삭제 버튼
	
	JPanel addPanel;			// 텍스트 필드와 추가 버튼이 올라갈 패널
	JTextField addField;		// 콤보 박스에 추가할 데이터를 입력받을 텍스트 필드
	JButton addButton;			// 추가 버튼
	
	JLabel bottomLabel;			// 윈도우 하단에 작업 메시지를 표시할 레이블
	
	public ComboBoxTest2() {
		setTitle("ComboBox");
		setBounds(1200, 100, 400, 400);
		setLayout(new GridLayout(4, 1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		윈도우 상단의 제목 레이블 세팅
		topLabel = new JLabel("콤보 상자 테스트");
		topLabel.setHorizontalAlignment(JLabel.CENTER);
		topLabel.setFont(new Font("D2Coding", Font.BOLD, 35));
		add(topLabel);
		
//		윈도우 중단의 콤보 박스와 보기, 삭제 버튼 세팅
//		comboBox = new Choice();		// 콤보 박스를 만든다.
//		comboBox.add("========== 뭐먹을래 ==========");
//		comboBox.add("바나나");			// Choice 클래스로 만든 콤보 박스에 아이템을 추가한다.
//		comboBox.add("파인애플");
//		comboBox.add("망고");
//		comboBox.add("두리안");
//		comboBox.add("베리베리스트로베리");
		
//		1. 	JComboBox 클래스로 콤보 박스를 만들려면 콤보 박수에 표시할 목록을 저장한 배열을 만들고 배열을 JComboBox 클래스 생성자의 인수로
//		넘겨주면 된다.
		
//		String[] fruit = {"==== 뭐먹을래 ======", "바나나","파인애플","망고","두리안","베리베리스트로베리"};
//		comboBox = new JComboBox<String>(fruit);
	
//		2. 기본 생성자로 JComboBox 클라스 객체를 만들고 setModel() 메소드로 DefaultComboBoxModel 클래스 객체에 콤보 박스에 표시할 목록이 저장된
//		배열을 인수로 넘겨서 만든다.
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[]  {"==== 뭐먹을래 ======", "바나나","파인애플","망고",
				"두리안","베리베리스트로베리"}));
		
		
		
		
		
		showButton = new JButton("보기");
		deleteButton = new JButton("삭제");
		comboPanel = new JPanel();
		comboPanel.add(comboBox);
		comboPanel.add(showButton);
		comboPanel.add(deleteButton);
		add(comboPanel);
		
//		윈도우 중단의 텍스트 필드와 추가 버튼 세팅
		addField = new JTextField(20);
		addButton = new JButton("추가");
		addPanel = new JPanel();
		addPanel.add(addField);
		addPanel.add(addButton);
		add(addPanel);
		
//		윈도우 하단의 레이블 세팅
		bottomLabel = new JLabel("이곳에 작업 결과가 표시됩니다.");
		bottomLabel.setHorizontalAlignment(JLabel.CENTER);
		add(bottomLabel);
		 
//		보기, 삭제, 추가 버튼에 ActionListener를 걸어준다.
		showButton.addActionListener(this);
		deleteButton.addActionListener(this);
		addButton.addActionListener(this);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		
		ComboBoxTest2 window = new ComboBoxTest2();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
//		어떤 버튼에서 ActionListener가 실행되었나 판단해서 적당한 작업을 한다.
		String item = "";
		switch(e.getActionCommand()) {
			case "보기":
//				getSelectedIndex() : 콤보 박스에서 몇 번째 index가 선택되었나 얻어온다. => index는 0부터 시작된다.
//				getItemAt(index) : JComboBox클래스로 만든 콤보 박스의 index 번째 항목에 해당되는 아이템을 얻어온다.
				bottomLabel.setText("선택된 값 : " + comboBox.getItemAt(comboBox.getSelectedIndex()));
//				getSelectedItem() : 콤보 박스에서 선택된 아이템을 얻어온다.
//				comboBox.getSelectedItem()와 comboBox.getItem(comboBox.getSelectedIndex())는 같은 기능이 실행된다.
				bottomLabel.setText("선택된 값 : " + comboBox.getSelectedItem());
				
				break;
			case "삭제":
				item = comboBox.getSelectedItem() + "를(을) 삭제하시겠습니까?";
				int result = JOptionPane.showConfirmDialog(comboBox, item, "삭제확인", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(result == 0) {
//					removeItemAt(anIndex) : JComboBox 클래스로 만든 콤보 박스에서 position 번째 아이템을 제거한다.
					bottomLabel.setText(comboBox.getSelectedItem() + " 삭제 완료!!!");
					comboBox.removeItemAt(comboBox.getSelectedIndex());
				} else {
					bottomLabel.setText(comboBox.getSelectedItem() + " 삭제 취소!!!");
				}
				break;
			case "추가":
				item = addField.getText().trim();
				if(item.length() > 0) {
//					addItem() : JComboBox클래스로 만든 콤보 박스에 데이터를 추가한다.
					comboBox.addItem(item);
					bottomLabel.setText(item + " 추가 완료!!!");
//					getItemCount() : 콤보 박스에 저장된 아이템의 개수를 얻어온다.
//					select(pos) : Choice 클래스로 만든 콤보 박스의 pos 번째 위치의 아이템을 콤보 박스에 표시한다.
					comboBox.setSelectedIndex(comboBox.getItemCount() - 1);
				} else {
					JOptionPane.showMessageDialog(addField, "콤보 박스에 추가할 내용을 입력하세요", "입력확인", JOptionPane.ERROR_MESSAGE);
				}
				addField.setText("");
				addField.requestFocus();
				break;
		}
		
	}

}























