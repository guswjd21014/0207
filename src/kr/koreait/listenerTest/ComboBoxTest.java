package kr.koreait.listenerTest;

import java.awt.Choice;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ComboBoxTest extends JFrame implements ActionListener{
	
	JLabel topLabel;					//  원도우 상단의 제목 레이블
	
	JPanel comboPanel;					//	콤보 상자와 보기 , 삭제 버튼이 올라갈 패널
	Choice comboBox;					//	콤보 박스
	JButton showButton,deleteButton;	//	보기 버튼 삭제 버튼
	JPanel addPanel;					//	텍스트 필드와 추가 버튼이 올가갈 패널
	JTextField addField;				//	콤보 박스에 추가할 데이터를 입력받을 텍스트 필드
	JButton addButton;					//	추가 버튼
	
	JLabel bottomLabel;					//	윈도우 하단에 작업 메세지를 표시할 레이블
	
	
	public ComboBoxTest() {
		setTitle("ComboBox");
		setBounds(1200, 100, 400, 400);
		setLayout(new GridLayout(4,1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		원도우 상단의 제목 레이블 세팅
		topLabel = new JLabel("콤보 상자 테스트");
		topLabel.setHorizontalAlignment(JLabel.CENTER);
		topLabel.setFont(new Font("D2Coding", Font.BOLD , 35));
		add(topLabel);
		
//		원도우 중단의 콤보 박스와 보기, 삭제 버튼 세팅
		comboBox = new Choice();			//	콤보 박스를 만든다.
		comboBox.add("=====과일을 골라주세요====");
		comboBox.add("귤");					//	Choice 클래스로 만든 콤보 박스에 아이템을 추가한다
		comboBox.add("사과");
		comboBox.add("바나나");
		comboBox.add("망고");
		comboBox.add("배");
		comboBox.add("수박");
		add(comboBox);
		showButton = new JButton("보기");
		deleteButton = new JButton("삭제");
		comboPanel = new JPanel();
		comboPanel.add(comboBox);
		comboPanel.add(showButton);
		comboPanel.add(deleteButton);
		add(comboPanel);
		
//		원도우 중단의 텍스트 필드와 추가 버튼 세팅
		addField = new JTextField(20);
		addButton = new JButton("추가");
		addPanel = new JPanel();
		addPanel.add(addField);
		addPanel.add(addButton);
		add(addPanel);
		
//		윈도우 하단의 레이블 세팅
		bottomLabel = new JLabel("이곳에 작업 결과가 표시됩니다. ");
		bottomLabel.setHorizontalAlignment(JLabel.CENTER);
		add(bottomLabel);
//		보기, 삭제, 추가 버튼에 ActionListener를 걸어준다.
		showButton.addActionListener(this);
		deleteButton.addActionListener(this);
		addButton.addActionListener(this);
		
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		
		ComboBoxTest window = new ComboBoxTest();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String item = "";
//		어떤 버튼에서 ActionListener가 실행되었나 판단해서 적당한 작업을 한다.
		switch (e.getActionCommand()) {
		case "보기":
//		getSelectedIndex() : 콤보 박스에서 몇 번째 index가 선택 되었나 얻어온다. => index는 0 시작된다.
//		getItem(index)     : Choice 클래스로 만든 콤보 박스의 index 번째 항목에 해당되는 아이템을 얻어온다.	
//		bottomLabel.setText("선택된 값 : " + comboBox.getItem(comboBox.getSelectedIndex()));
//		getSelectedItem()  : 콤보 박스에서 선택된 아이템을 얻어온다.
//		getItem(comboBox.getSelectedIndex())	,getSelectedItem() 	:같은 기능을 실행한다.
			bottomLabel.setText("선택된 값 : " + comboBox.getSelectedItem());
			break;
		case "삭제":
			 item = comboBox.getSelectedItem() + "를(을) 삭제하시겠습니까?";
			int result = JOptionPane.showConfirmDialog(comboBox, item, "삭제확인", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(result == 0) {
//				remove(position) : Choice 클래스로 만든 콤보 박스에서 position 번째 아이템을 제거한다.
				bottomLabel.setText(comboBox.getSelectedItem() + " 삭제 완료!!!");
				comboBox.remove(comboBox.getSelectedIndex());
			} else {
				bottomLabel.setText(comboBox.getSelectedItem() + " 삭제 취소!!!");
			}
			break;			

		case "추가":
			item = addField.getText().trim();
			if(item.length() > 0) {
				comboBox.add(item);
				bottomLabel.setText(item + " 추가 완료");
//				getItemCount() : 콤보 박스에 저장된 아이템의 개수를 얻어온다.
//				select(pos) : Choice 클래스로 만든 콤보 박스의 pos 번째 위치의 아이템을 콤보 박스에 표시한다.
				comboBox.select(comboBox.getItemCount()-1);
			}else {
				JOptionPane.showMessageDialog(addField, "콤보 박스에 추가할 내용을 입력하세요" , "입력확인", JOptionPane.ERROR_MESSAGE);
			}
			addField.setText("");
			addField.requestFocus();
			break;

		default:
			break;
		}
		
	}

}























