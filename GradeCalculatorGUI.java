import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

class Student{
	String name;
	int [] marks = new int[3];
	int total;
	double average;
	String grade;

	public Student(String name, int[] marks){
		this.name = name;
		this.marks = marks;
		this.total = marks[0]+marks[1]+marks[2];
		this.average = total /3.0;
		this.grade = calculateGrade();
	}

	private String calculateGrade(){
		if(average >= 90) return"A";
		else if(average>=75) return"B";
		else if(average>=50) return"C";
		else return "Fail";
	}

	public Object[] toRow(){
		return new Object[]{name,marks[0],marks[1],marks[2],total,String.format("%.2f",average),grade};
	}
}

public class GradeCalculatorGUI extends JFrame {
	private JTextField nameField, m1Field,m2Field,m3Field;
	private JButton addButton;
	private JTable table;
	private ArrayList<Student> students;
	private DefaultTableModel model;

	public GradeCalculatorGUI(){
		setTitle("Student Grade Calculator");
		setSize(700,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		students = new ArrayList<>();

		//Input Panel

		JPanel inputPanel = new JPanel(new GridLayout(2,4,10,10));
		nameField = new JTextField();
		m1Field = new JTextField();
		m2Field = new JTextField();
		m3Field = new JTextField();

		inputPanel.add(new JLabel("Name :"));
		inputPanel.add(new JLabel("Mark 1:"));
		inputPanel.add(new JLabel("Mark 2:"));
		inputPanel.add(new JLabel("Mark 3:"));

		inputPanel.add(nameField);
		inputPanel.add(m1Field);
		inputPanel.add(m2Field);
		inputPanel.add(m3Field);

		//Button
		addButton = new JButton("Add Student");
		addButton.addActionListener(e-> addStudent());

		//Table
		String[] cols = {"Name","M1","M2","M3","Total","Average","Grade"};
		model = new DefaultTableModel(cols,0);
		table = new JTable(model);

		//Layout
		setLayout(new BorderLayout());
		add(inputPanel,BorderLayout.NORTH);
		add(addButton,BorderLayout.CENTER);
		add(new JScrollPane(table),BorderLayout.SOUTH);
	}

	private void addStudent(){
		try{
			String name = nameField.getText().trim();
			int [] marks = {
				Integer.parseInt(m1Field.getText().trim()),
				Integer.parseInt(m2Field.getText().trim()),
				Integer.parseInt(m3Field.getText().trim())
			};

			Student s = new Student(name,marks);
			students.add(s);
			model.addRow(s.toRow());

			//Clear fields
			nameField.setText("");
			m1Field.setText("");
			m2Field.setText("");

		}

		catch (NumberFormatException ex){
			JOptionPane.showMessageDialog(this,"Please enter valid numbers for marks.","Input Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args){
		SwingUtilities.invokeLater(() -> new GradeCalculatorGUI().setVisible(true));
	}
}


































