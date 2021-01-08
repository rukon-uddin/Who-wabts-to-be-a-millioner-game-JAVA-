import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class KBCmain implements ActionListener {
//###################### MAP #########################################################
	public static HashMap<Integer, String> hmQuestion = new HashMap<Integer, String>();
	public static HashMap<String, Integer> hmAnswers = new HashMap<String, Integer>();
	public static HashMap<Integer, String> hmAnswers2 = new HashMap<Integer, String>();
//###################### MAP #########################################################

//###################### Components declaration #########################################################
	private static String correct_answer = "";
	private JFrame jf = new JFrame();
	private static JFrame jf2 = new JFrame();
	private JPanel jp = new JPanel(new BorderLayout());
	private JPanel jp2 = new JPanel();
	private JTextField jtf;
	private static JTextArea jta, jta2;
	private JButton Bs;
	private static JLabel jl, jl2, jl3, timer;
	private static JLabel s1, s2, s3;
	private JButton a1, a2, a3, a4;
	private static int counter = 0;
//###################### Components declaration #########################################################

	public KBCmain() {
		jl = new JLabel("Who Wants To Be A Millinior!");
		jl.setBounds(50, 0, 800, 50);
		jl.setFont(new Font("serif", Font.PLAIN, 60));
		jl.setForeground(new Color(0, 0, 0));

		jl2 = new JLabel("Enter Your Name: ");
		jl2.setBounds(60, 80, 400, 50);
		jl2.setFont(new Font("serif", Font.PLAIN, 40));
		jl2.setForeground(new Color(9, 30, 44));

		jtf = new JTextField();
		jtf.setBounds(360, 90, 400, 40);

		Bs = new JButton("Start");
		Bs.setBounds(300, 170, 250, 50);
		Bs.setFont(new Font("serif", Font.BOLD, 30));

		jf.setSize(900, 600);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		jf.setLayout(null);
		jf.add(jl);
		jf.add(jl2);
		jf.add(jtf);
		jf.add(Bs);

		Bs.addActionListener(this);

		wait(8000);

		int Aarr[] = new int[50];
		int Qarr[] = new int[50];
		int i;
		for (i = 0; i <= 40; i++) {
			Aarr[i] = 0;
			Qarr[i] = 0;
		}
		for (i = 1; i <= 3; i++) {
			int questionNumber = generateRandomInt(28) + 1;
			while (!checkForRepeat(Qarr, questionNumber)) {
				questionNumber = generateRandomInt(28) + 1;
			}
			Qarr[i] = questionNumber;
			String question = hmQuestion.get(questionNumber);

			s1 = new JLabel(i + ": " + question);
			s1.setBounds(50, 30, 900, 80);
			s1.setFont(new Font("serif", Font.PLAIN, 30));
			s1.setForeground(new Color(0, 0, 0));

			char ch = 'A';
			String ans = hmAnswers2.get(questionNumber);
			correct_answer = ans;
			int ran = generateRandomInt(4) + 1;
			if (ran == 1)
				a1 = new JButton(ans);
			else if (ran == 2)
				a2 = new JButton(ans);
			else if (ran == 3)
				a3 = new JButton(ans);
			else if (ran == 4)
				a4 = new JButton(ans);
			Aarr[ran] = questionNumber;
			for (int j = 1; j <= 4; j++) {
				if (j == ran) {
					continue;
				} else {
					int rans = generateRandomInt(28) + 1;
					while (!checkForRepeat(Aarr, rans)) {
						rans = generateRandomInt(28) + 1;
					}
					Aarr[j] = rans;
					if (j == 1)
						a1 = new JButton(hmAnswers2.get(rans));
					if (j == 2)
						a2 = new JButton(hmAnswers2.get(rans));
					if (j == 3)
						a3 = new JButton(hmAnswers2.get(rans));
					if (j == 4)
						a4 = new JButton(hmAnswers2.get(rans));
					// System.out.println(ch++ +": "+hmAnswers2.get(rans));
				}

			}

			a1.setBounds(70, 100, 170, 60);
			a2.setBounds(280, 100, 170, 60);
			a3.setBounds(70, 180, 170, 60);
			a4.setBounds(280, 180, 170, 60);

			jf2.setSize(900, 600);
			jf2.setResizable(false);
			jf2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jf2.setVisible(true);
			jf2.setLayout(null);

			jf2.add(s1);
			jf2.add(a1);
			jf2.add(a2);
			jf2.add(a3);
			jf2.add(a4);
			a1.addActionListener(this);
			a2.addActionListener(this);
			a3.addActionListener(this);
			a4.addActionListener(this);

			// ######## TIMER #############
			for (int t = 0; t <= 5; t++) {
				timer = new JLabel(t + "");
				timer.setBounds(600, 100, 900, 80);
				timer.setFont(new Font("serif", Font.PLAIN, 30));
				timer.setForeground(new Color(0, 0, 0));
				jf2.setSize(900, 600);
				jf2.setResizable(false);
				jf2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jf2.setVisible(true);
				jf2.setLayout(null);
				jf2.add(timer);
				wait(1000);
				java.awt.Container parent = timer.getParent();
				parent.remove(timer);
				parent.validate();
				parent.repaint();
			}
			jf2.getContentPane().removeAll();
			jf2.repaint();
		}
		if (counter == 3) {

			jta2 = new JTextArea();
			jta2.setBounds(190, 150, 500, 250);
			jta2.setText("               Congrats!!!\n You have won 1 million dollar");
			jta2.setFont(new Font("serif", Font.BOLD, 20));
			jta2.setEditable(false);
			jf2.setSize(900, 600);
			jf2.setResizable(false);
			jf2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jf2.setVisible(true);
			jf2.setLayout(null);
			jf2.add(jta2);

		} else {
			jta2 = new JTextArea();
			jta2.setBounds(190, 150, 500, 250);
			jta2.setText("You have answered " + counter + " correct answer\n try again next time");
			jta2.setFont(new Font("serif", Font.BOLD, 20));
			jta2.setEditable(false);
			jf2.setSize(900, 600);
			jf2.setResizable(false);
			jf2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jf2.setVisible(true);
			jf2.setLayout(null);
			jf2.add(jta2);
		}

	}

	public static void main(String[] args) throws IOException {
		mapQuestion();
		mapAnswers();
		new KBCmain();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() != null && e.getActionCommand().equals("Start")) {
			String name = jtf.getText();
			jta = new JTextArea();
			jta.setBounds(190, 250, 500, 250);
			jta.setText("Hello " + name
					+ " This is your chance to beacome a Millinior\nThere are 10 questions in this game all you have to do is\nanswer all of them and become a Millinior over night\nthankyou.\n         "
					+ "                             Lets Start!");
			jta.setFont(new Font("serif", Font.BOLD, 20));
			jf.setSize(900, 600);
			jf.setResizable(false);
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jf.setVisible(true);
			jf.setLayout(null);
			jf.add(jta);
		} else {
			if (e.getActionCommand().equals(correct_answer)) {
				counter++;
				s2 = new JLabel("You Are Correct!!");
				s2.setBounds(110, 260, 900, 80);
				s2.setFont(new Font("serif", Font.PLAIN, 40));
				s2.setForeground(new Color(60, 20, 10));
				jf2.setSize(900, 600);
				jf2.setResizable(false);
				jf2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jf2.setVisible(true);
				jf2.setLayout(null);
				jf2.add(s2);
			} else {
				s2 = new JLabel("You Are are Wrong!!");
				s2.setBounds(110, 260, 900, 80);
				s2.setFont(new Font("serif", Font.PLAIN, 40));
				s2.setForeground(Color.RED);
				jf2.setSize(900, 600);
				jf2.setResizable(false);
				jf2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jf2.setVisible(true);
				jf2.setLayout(null);
				jf2.add(s2);
			}
		}
	}

	public static boolean checkForRepeat(int arr[], int n) {
		int flg = 0;
		for (int i = 1; i <= 28; i++) {
			if (arr[i] == n) {
				flg = 1;
				break;
			}
		}
		if (flg == 1) {
			return false;
		} else
			return true;
	}

	public static int generateRandomInt(int upperRange) {
		Random random = new Random();
		return random.nextInt(upperRange);
	}

	public static void mapQuestion() throws IOException {
		File f = new File("Question.txt");
		FileReader fr = new FileReader(f);
		String str = "";
		int i;
		int j = 1;
		while ((i = fr.read()) != -1) {
			if ((char) i == '\n') {
				hmQuestion.put(j, str);
				str = "";
				j++;
			} else {
				str += (char) i;
			}
		}
		hmQuestion.put(j, str);
		fr.close();
	}

	public static void mapAnswers() throws IOException {
		File f = new File("Answer.txt");
		FileReader fr = new FileReader(f);
		String str = "";
		int i;
		int j = 1;
		while ((i = fr.read()) != -1) {
			if ((char) i == '\n') {
				hmAnswers.put(str, j);
				hmAnswers2.put(j, str);
				str = "";
				j++;
			} else {
				str += (char) i;
			}
		}
		hmAnswers.put(str, j);
		hmAnswers2.put(j, str);
		fr.close();
	}

	public static void wait(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}
