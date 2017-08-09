import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import objects.Card;
import objects.Deck;
import objects.Trial;

import com.objectplanet.chart.BarChart;
import com.objectplanet.chart.NonFlickerPanel;

public class CardDrawingSimulator extends JFrame implements ActionListener, MouseListener {
	public static final String FILENAME = "CardDrawLog.txt";
	public static final String FILENAME_CSV = "CardDrawLogCSV.csv";
	
	// GUI Components
	Font font = new Font("Helvetica", Font.PLAIN, 12);
	Font smfont = new Font("Helvetica", Font.PLAIN, 10);
	Font lgfont = new Font("Helvetica", Font.PLAIN, 14);
	JPanel drawOptions, wRepPane, woRepPane, info, visual1, visual2;
	JButton num1, num2, num3, num4, num5;
	JLabel drawOptionsLbl, controllerLbl, wRepLbl, woRepLbl, nTrialLbl;
	JButton drawBtn, nextTrialBtn, openAllBtn, generateBtn;
	JLabel wCard1, wCard2, wCard3, wCard4, wCard5;
	JLabel woCard1, woCard2, woCard3, woCard4, woCard5;
	ImageIcon back = new ImageIcon(this.getClass().getResource("/res/cards/back.png"));
	
	NonFlickerPanel p1, p2;
	
	JTextArea console;
	JScrollPane scroll;
	JLabel dLbl, dTotalLbl, dWIdealLbl, dWActualLbl, dWoIdealLbl, dWoActualLbl, dWRepLbl, dWoRepLbl;
	JTextField dTotalFld, dWIdealFld, dWActualFld, dWoIdealFld, dWoActualFld;
	JButton dComputeBtn;
	JLabel otherLbl, meanLbl, medianLbl, modeLbl, varLbl, stdLbl, oIdealLbl, aIdealLbl;
	JTextField iMeanFld, iMedianFld, iModeFld, iVarFld, iStdFld;
	JTextField aMeanFld, aMedianFld, aModeFld, aVarFld, aStdFld;
	JLabel visual1Lbl, visual2Lbl;
	
	// Program Components	
	Logger log;
	int numCards, numTrials, from, to;
	Scanner sc;
	Deck deck;
	int count, trialCount;
	ArrayList<Card> drawnWith, drawnWithout;
	ArrayList<Trial> trials;
	boolean canDraw, isWithout;
	
	public CardDrawingSimulator(Logger log){
		this.log = log;
		init();
	}
	
	public void init(){
		drawnWith = new ArrayList<Card>();
		drawnWithout = new ArrayList<Card>();
		trials = new ArrayList<Trial>();
		deck = new Deck();
		numCards = -1;
		numTrials = 0;
		initGUI();
	}
	
	public void start(){
		nTrialLbl.setText(numTrials + " Trial/s");
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == num1) {
			resetButtons();
			num1.setEnabled(false);
			showCards(1);
			numCards = 1;
		}
		
		if(e.getSource() == num2) {
			resetButtons();
			num2.setEnabled(false);
			showCards(2);
			numCards = 2;
		}
		
		if(e.getSource() == num3) {
			resetButtons();
			num3.setEnabled(false);
			showCards(3);
			numCards = 3;
		}
		
		if(e.getSource() == num4) {
			resetButtons();
			num4.setEnabled(false);
			showCards(4);
			numCards = 4;
		}
		
		if(e.getSource() == num5) {
			resetButtons();
			num5.setEnabled(false);
			showCards(5);
			numCards = 5;
		}
		
		if(e.getSource() == drawBtn) {
			if(numCards != -1) {
				drawnWith.clear();
				drawnWithout.clear();
				numTrials++;
				draw();
				nTrialLbl.setText(numTrials + " Trial/s");
				disableButtons();
				repaint();
			}
		}
		
		if(e.getSource() == nextTrialBtn) {
			resetCards();
			drawnWith.clear();
			drawnWithout.clear();
			numTrials++;
			draw();
			nTrialLbl.setText(numTrials + " Trial/s");
			disableButtons();
			repaint();
		}
		
		if(e.getSource() == generateBtn) {
			wRepPane.setVisible(false);
			woRepPane.setVisible(false);
			info.setVisible(true);
			visual1.setVisible(true);
			visual2.setVisible(true);
			
			
			// Edit chart details heree
			double[] values = new double[10];
			String[] labels = new String[values.length];
			for (int i = 0; i < values.length; i++) {
				values[i] = Math.round(Math.random()*100);
				labels[i] = "bar" + i;
			}
			BarChart chart = new BarChart();
			chart.setSampleCount(values.length);
			chart.setSampleValues(0, values);
			chart.setSampleLabels(labels);
			chart.setRelativeRange(1.0, 100);
			
			// turn on the floating value labels
			chart.setValueLabelsOn(true);
			chart.setValueLabelStyle(BarChart.FLOATING);
			chart.setBarLabelsOn(true);
			chart.setSampleLabelStyle(BarChart.FLOATING);
			
			// chart 2
			for (int i = 0; i < values.length; i++) {
				values[i] = Math.round(Math.random()*100);
				labels[i] = "bar" + i;
			}
			
			BarChart chart2 = new BarChart();
			chart2.setSampleCount(values.length);
			chart2.setSampleValues(0, values);
			chart2.setSampleLabels(labels);
			chart2.setRelativeRange(1.0, 100);
			
			// turn on the floating value labels
			chart2.setValueLabelsOn(true);
			chart2.setValueLabelStyle(BarChart.FLOATING);
			chart2.setBarLabelsOn(true);
			chart2.setSampleLabelStyle(BarChart.FLOATING);
			
			
			// ends here
			chart.setBounds(10, 30, 530, 300);
			visual1.add("Center", chart);
			
			chart2.setBounds(10, 30, 530, 300);
			visual2.add("Center", chart2);
			repaint();
			
			repaint();
		}
		
		if(e.getSource() == openAllBtn) {
			wCard1.setIcon(drawnWith.get(0).getIcon());
			wCard2.setIcon(drawnWith.get(1).getIcon());
			wCard3.setIcon(drawnWith.get(2).getIcon());
			wCard4.setIcon(drawnWith.get(3).getIcon());
			wCard5.setIcon(drawnWith.get(4).getIcon());
			woCard1.setIcon(drawnWithout.get(0).getIcon());
			woCard2.setIcon(drawnWithout.get(1).getIcon());
			woCard3.setIcon(drawnWithout.get(2).getIcon());
			woCard4.setIcon(drawnWithout.get(3).getIcon());
			woCard5.setIcon(drawnWithout.get(4).getIcon());
		}

	}
	
	public void draw() {
		Trial t = new Trial();
		
		// w/ Replacement
		console.append("Trial # " + numTrials + "\n");
		console.append("With Replacement\n");
		log.writeFile(FILENAME, "Trial # " + numTrials);
		log.writeFile(FILENAME, "With Replacement");
		ArrayList<Card> result = deck.draw(numCards, true);
		drawnWith = (ArrayList<Card>) result.clone();
		displayCards(drawnWith);
		
		// w/o replacement
		console.append("Without Replacement\n");
		log.writeFile(FILENAME, "Without replacement");
		result = deck.draw(numCards, false);
		drawnWithout = (ArrayList<Card>) result.clone();
		displayCards(drawnWithout);
		
		t.setDrawnWith(drawnWith);
		t.setDrawnWithout(drawnWithout);
		t.setSumWith(getSumCard(drawnWith));
		t.setSumWithout(getSumCard(drawnWithout));
		trials.add(t);
	}
	
	public void displayCards(ArrayList<Card> result) {
		for(int i = 0; i < result.size(); i++) {
			Card c = result.get(i);
			console.append("Card # " + (i+1) + " " + c.rank + " of " + c.suit + "\n");
			log.writeFile(FILENAME,
					"Card # " + (i+1) + " " + c.rank + " of " + c.suit + "\n");
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stubs		
		if(e.getSource() == wCard1) { wCard1.setIcon(drawnWith.get(0).getIcon()); }
		if(e.getSource() == wCard2) { wCard2.setIcon(drawnWith.get(1).getIcon()); }
		if(e.getSource() == wCard3) { wCard3.setIcon(drawnWith.get(2).getIcon()); }
		if(e.getSource() == wCard4) { wCard4.setIcon(drawnWith.get(3).getIcon()); }
		if(e.getSource() == wCard5) { wCard5.setIcon(drawnWith.get(4).getIcon()); }
		if(e.getSource() == woCard1) { woCard1.setIcon(drawnWithout.get(0).getIcon()); }
		if(e.getSource() == woCard2) { woCard2.setIcon(drawnWithout.get(1).getIcon()); }
		if(e.getSource() == woCard3) { woCard3.setIcon(drawnWithout.get(2).getIcon()); }
		if(e.getSource() == woCard4) { woCard4.setIcon(drawnWithout.get(3).getIcon()); }
		if(e.getSource() == woCard5) { woCard5.setIcon(drawnWithout.get(4).getIcon()); }
	}
	
	public void displayTrial(ArrayList<Card> result) {
		for(int i = 0; i < result.size(); i++) {
			System.out.println("Card "+(i+1)+": " + result.get(i).getRank() + " " + result.get(i).getSuit());
			log.writeFile(FILENAME, "Card "+(i+1)+": " + result.get(i).getRank() + " " + result.get(i).getSuit());
		}
	}
	
	public int getSumCard(ArrayList<Card> result) {
		int sum = 0;
		for(int i = 0; i < result.size(); i++) {
			sum += result.get(i).getRank();
		}
		return sum;
	}
	
	public void initGUI() {
		this.setTitle("Card Drawing Simulator");
		this.setLayout(null);
		this.setSize (1100,720);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		drawOptions = new JPanel();
		drawOptions.setBounds(10, 10, 100, 670);
		drawOptions.setBackground(Color.WHITE);
		drawOptions.setVisible(true);
		drawOptions.setLayout(null);
		add(drawOptions);
		initDraw();
		
		wRepPane = new JPanel();
		wRepPane.setBounds(120, 10, 960, 330);
		wRepPane.setBackground(Color.WHITE);
		wRepPane.setVisible(true);
		wRepPane.setLayout(null);
		add(wRepPane);
		initWRep();
//		wRepPane.setVisible(false);
		
		woRepPane = new JPanel();
		woRepPane.setBounds(120, 350, 960, 330);
		woRepPane.setBackground(Color.WHITE);
		woRepPane.setVisible(true);
		woRepPane.setLayout(null);
		add(woRepPane);
		initWoRep();
//		woRepPane.setVisible(false);
		
		info = new JPanel();
		info.setBounds(120, 10, 400, 670);
		info.setBackground(Color.WHITE);
		info.setVisible(true);
		info.setLayout(null);
		add(info);
		initInfo();
		info.setVisible(false);
		
		visual1 = new JPanel();
		visual1.setBounds(530, 10, 550, 330);
		visual1.setBackground(Color.WHITE);
		visual1.setVisible(true);
		visual1.setLayout(null);
		add(visual1);
		initVisual1();
		visual1.setVisible(false);
		
		visual2 = new JPanel();
		visual2.setBounds(530, 350, 550, 330);
		visual2.setBackground(Color.WHITE);
		visual2.setVisible(true);
		visual2.setLayout(null);
		add(visual2);
		initVisual2();
		visual2.setVisible(false);
		
		this.repaint();
	}
	
	public void initVisual1() {
		visual1Lbl = new JLabel("Visualization A", JLabel.CENTER);
		visual1Lbl.setBounds(0, 0, 550, 30);
		visual1Lbl.setFont(font);
		visual1Lbl.setBackground(Color.LIGHT_GRAY);
		visual1Lbl.setOpaque(true);
		visual1.add(visual1Lbl);
	}
	
	public void initVisual2() {
		visual2Lbl = new JLabel("Visualization B", JLabel.CENTER);
		visual2Lbl.setBounds(0, 0, 550, 30);
		visual2Lbl.setFont(font);
		visual2Lbl.setBackground(Color.LIGHT_GRAY);
		visual2Lbl.setOpaque(true);
		visual2.add(visual2Lbl);
	}
	
	public void initInfo() {
		console = new JTextArea();
	    console.setEditable(false);
	    console.setBounds(10, 10, 375, 200);
	    console.setVisible(true);
	    console.setLineWrap(true);
	    console.setWrapStyleWord(true);
	     
	    scroll = new JScrollPane(console);
	    scroll.setBounds(10, 10, 375, 200);
	    scroll.setBorder(BorderFactory.createLineBorder(Color.black));
	    info.add(scroll);
	    
	    dLbl = new JLabel("Compute for Desired Total", JLabel.CENTER);
	    dLbl.setBounds(0, 230, 400, 30);
	    dLbl.setFont(font);
	    dLbl.setBackground(Color.PINK);
	    dLbl.setOpaque(true);
	    info.add(dLbl);
	    
	    dTotalLbl = new JLabel("Desired Total");
	    dTotalLbl.setBounds(10, 270, 100, 30);
	    dTotalLbl.setFont(font);
	    info.add(dTotalLbl);
	    
	    dTotalFld = new JTextField();
	    dTotalFld.setBounds(10, 300, 100, 30);
	    dTotalFld.setFont(font);
	    info.add(dTotalFld);
	    
	    dComputeBtn = new JButton("Compute");
	    dComputeBtn.setBounds(10, 340, 100, 40);
	    dComputeBtn.setBackground(Color.WHITE);
	    dComputeBtn.setFont(font);
	    dComputeBtn.addActionListener(this);
	    info.add(dComputeBtn);
	    
	    dWRepLbl = new JLabel("W/ Replacement", JLabel.CENTER);
	    dWRepLbl.setBounds(130, 270, 120, 30);
	    dWRepLbl.setFont(font);
	    dWRepLbl.setBackground(Color.LIGHT_GRAY);
	    dWRepLbl.setOpaque(true);
	    info.add(dWRepLbl);
	    
	    dWIdealLbl = new JLabel("Ideal Probability");
	    dWIdealLbl.setBounds(130, 300, 100, 30);
	    dWIdealLbl.setFont(font);
	    info.add(dWIdealLbl);
	    
	    dWIdealFld = new JTextField();
	    dWIdealFld.setBounds(130, 330, 120, 30);
	    dWIdealFld.setFont(font);
	    dWIdealFld.setEnabled(false);
	    info.add(dWIdealFld);
	    
	    dWActualLbl = new JLabel("Actual Probability");
	    dWActualLbl.setBounds(130, 360, 100, 30);
	    dWActualLbl.setFont(font);
	    info.add(dWActualLbl);
	    
	    dWActualFld = new JTextField();
	    dWActualFld.setBounds(130, 390, 120, 30);
	    dWActualFld.setFont(font);
	    dWActualFld.setEnabled(false);
	    info.add(dWActualFld);
	    
	    dWoRepLbl = new JLabel("W/o Replacement", JLabel.CENTER);
	    dWoRepLbl.setBounds(260, 270, 120, 30);
	    dWoRepLbl.setFont(font);
	    dWoRepLbl.setBackground(Color.LIGHT_GRAY);
	    dWoRepLbl.setOpaque(true);
	    info.add(dWoRepLbl);
	    
	    dWoIdealLbl = new JLabel("Ideal Probability");
	    dWoIdealLbl.setBounds(260, 300, 100, 30);
	    dWoIdealLbl.setFont(font);
	    info.add(dWoIdealLbl);
	    
	    dWoIdealFld = new JTextField();
	    dWoIdealFld.setBounds(260, 330, 120, 30);
	    dWoIdealFld.setFont(font);
	    dWoIdealFld.setEnabled(false);
	    info.add(dWoIdealFld);
	    
	    dWoActualLbl = new JLabel("Actual Probability");
	    dWoActualLbl.setBounds(260, 360, 100, 30);
	    dWoActualLbl.setFont(font);
	    info.add(dWoActualLbl);
	    
	    dWoActualFld = new JTextField();
	    dWoActualFld.setBounds(260, 390, 120, 30);
	    dWoActualFld.setFont(font);
	    dWoActualFld.setEnabled(false);
	    info.add(dWoActualFld);
	    
	    otherLbl = new JLabel("Other Statistical Information", JLabel.CENTER);
	    otherLbl.setBounds(0, 430, 400, 30);
	    otherLbl.setFont(font);
	    otherLbl.setBackground(Color.PINK);
	    otherLbl.setOpaque(true);
	    info.add(otherLbl);
	    
	    meanLbl = new JLabel("Mean", JLabel.CENTER);
	    meanLbl.setBounds(10, 500, 110, 25);
	    meanLbl.setFont(font);
	    meanLbl.setBackground(Color.LIGHT_GRAY);
	    meanLbl.setOpaque(true);
	    info.add(meanLbl);
	    
	    medianLbl = new JLabel("Median", JLabel.CENTER);
	    medianLbl.setBounds(10, 535, 110, 25);
	    medianLbl.setFont(font);
	    medianLbl.setBackground(Color.LIGHT_GRAY);
	    medianLbl.setOpaque(true);
	    info.add(medianLbl);
	    
	    modeLbl = new JLabel("Mode", JLabel.CENTER);
	    modeLbl.setBounds(10, 570, 110, 25);
	    modeLbl.setFont(font);
	    modeLbl.setBackground(Color.LIGHT_GRAY);
	    modeLbl.setOpaque(true);
	    info.add(modeLbl);
	    
	    varLbl = new JLabel("Variance", JLabel.CENTER);
	    varLbl.setBounds(10, 605, 110, 25);
	    varLbl.setFont(font);
	    varLbl.setBackground(Color.LIGHT_GRAY);
	    varLbl.setOpaque(true);
	    info.add(varLbl);
	    
	    stdLbl = new JLabel("Standard Deviation", JLabel.CENTER);
	    stdLbl.setBounds(10, 640, 110, 25);
	    stdLbl.setFont(smfont);
	    stdLbl.setBackground(Color.LIGHT_GRAY);
	    stdLbl.setOpaque(true);
	    info.add(stdLbl);
	    
	    oIdealLbl = new JLabel("Ideal", JLabel.CENTER);
	    oIdealLbl.setBounds(130, 470, 120, 25);
	    oIdealLbl.setFont(font);
	    oIdealLbl.setBackground(Color.LIGHT_GRAY);
	    oIdealLbl.setOpaque(true);
	    info.add(oIdealLbl);
	    
	    iMeanFld = new JTextField();
	    iMeanFld.setBounds(130, 500, 120, 25);
	    iMeanFld.setFont(font);
	    iMeanFld.setEnabled(false);
	    info.add(iMeanFld);
	    
	    iMedianFld = new JTextField();
	    iMedianFld.setBounds(130, 535, 120, 25);
	    iMedianFld.setFont(font);
	    iMedianFld.setEnabled(false);
	    info.add(iMedianFld);
	    
	    iModeFld = new JTextField();
	    iModeFld.setBounds(130, 570, 120, 25);
	    iModeFld.setFont(font);
	    iModeFld.setEnabled(false);
	    info.add(iModeFld);
	    
	    iVarFld = new JTextField();
	    iVarFld.setBounds(130, 605, 120, 25);
	    iVarFld.setFont(font);
	    iVarFld.setEnabled(false);
	    info.add(iVarFld);
	    
	    iStdFld = new JTextField();
	    iStdFld.setBounds(130, 640, 120, 25);
	    iStdFld.setFont(font);
	    iStdFld.setEnabled(false);
	    info.add(iStdFld);
	    
	    aIdealLbl = new JLabel("Actual", JLabel.CENTER);
	    aIdealLbl.setBounds(260, 470, 120, 25);
	    aIdealLbl.setFont(font);
	    aIdealLbl.setBackground(Color.LIGHT_GRAY);
	    aIdealLbl.setOpaque(true);
	    info.add(aIdealLbl);
	    
	    aMeanFld = new JTextField();
	    aMeanFld.setBounds(260, 500, 120, 25);
	    aMeanFld.setFont(font);
	    aMeanFld.setEnabled(false);
	    info.add(aMeanFld);
	    
	    aMedianFld = new JTextField();
	    aMedianFld.setBounds(260, 535, 120, 25);
	    aMedianFld.setFont(font);
	    aMedianFld.setEnabled(false);
	    info.add(aMedianFld);
	    
	    aModeFld = new JTextField();
	    aModeFld.setBounds(260, 570, 120, 25);
	    aModeFld.setFont(font);
	    aModeFld.setEnabled(false);
	    info.add(aModeFld);
	    
	    aVarFld = new JTextField();
	    aVarFld.setBounds(260, 605, 120, 25);
	    aVarFld.setFont(font);
	    aVarFld.setEnabled(false);
	    info.add(aVarFld);
	    
	    aStdFld = new JTextField();
	    aStdFld.setBounds(260, 640, 120, 25);
	    aStdFld.setFont(font);
	    aStdFld.setEnabled(false);
	    info.add(aStdFld);
	}
	
	public void initWRep() {
		wRepLbl = new JLabel("With Replacement Drawn Cards", JLabel.CENTER);
		wRepLbl.setBounds(0, 0, 960, 30);
		wRepLbl.setFont(font);
		wRepLbl.setBackground(Color.PINK);
		wRepLbl.setOpaque(true);
		wRepPane.add(wRepLbl);
		
		wCard1 = new JLabel();
		wCard1.setHorizontalAlignment(JLabel.CENTER);
		wCard1.setBounds(10, 40, 180, 280);
		wCard1.setVisible(true);
		wCard1.setIcon(new ImageIcon(this.getClass().getResource("/res/cards/back.png")));
		wCard1.addMouseListener(this);
		wRepPane.add(wCard1);
		
		wCard2 = new JLabel();
		wCard2.setHorizontalAlignment(JLabel.CENTER);
		wCard2.setBounds(200, 40, 180, 280);
		wCard2.setVisible(true);
		wCard2.setIcon(new ImageIcon(this.getClass().getResource("/res/cards/back.png")));
		wCard2.addMouseListener(this);
		wRepPane.add(wCard2);
		
		wCard3 = new JLabel();
		wCard3.setHorizontalAlignment(JLabel.CENTER);
		wCard3.setBounds(390, 40, 180, 280);
		wCard3.setVisible(true);
		wCard3.setIcon(new ImageIcon(this.getClass().getResource("/res/cards/back.png")));
		wCard3.addMouseListener(this);
		wRepPane.add(wCard3);
		
		wCard4 = new JLabel();
		wCard4.setHorizontalAlignment(JLabel.CENTER);
		wCard4.setBounds(580, 40, 180, 280);
		wCard4.setVisible(true);
		wCard4.setIcon(new ImageIcon(this.getClass().getResource("/res/cards/back.png")));
		wCard4.addMouseListener(this);
		wRepPane.add(wCard4);
		
		wCard5 = new JLabel();
		wCard5.setHorizontalAlignment(JLabel.CENTER);
		wCard5.setBounds(770, 40, 180, 280);
		wCard5.setVisible(true);
		wCard5.setIcon(new ImageIcon(this.getClass().getResource("/res/cards/back.png")));
		wCard5.addMouseListener(this);
		wRepPane.add(wCard5);
	}
	
	public void initWoRep() {
		woRepLbl = new JLabel("Without Replacement Drawn Cards", JLabel.CENTER);
		woRepLbl.setBounds(0, 0, 960, 30);
		woRepLbl.setFont(font);
		woRepLbl.setBackground(Color.PINK);
		woRepLbl.setOpaque(true);
		woRepPane.add(woRepLbl);
		
		woCard1 = new JLabel();
		woCard1.setHorizontalAlignment(JLabel.CENTER);
		woCard1.setBounds(10, 40, 180, 280);
		woCard1.setVisible(true);
		woCard1.setIcon(new ImageIcon(this.getClass().getResource("/res/cards/back.png")));
		woCard1.addMouseListener(this);
		woRepPane.add(woCard1);
		
		woCard2 = new JLabel();
		woCard2.setHorizontalAlignment(JLabel.CENTER);
		woCard2.setBounds(200, 40, 180, 280);
		woCard2.setVisible(true);
		woCard2.setIcon(new ImageIcon(this.getClass().getResource("/res/cards/back.png")));
		woCard2.addMouseListener(this);
		woRepPane.add(woCard2);
		
		woCard3 = new JLabel();
		woCard3.setHorizontalAlignment(JLabel.CENTER);
		woCard3.setBounds(390, 40, 180, 280);
		woCard3.setVisible(true);
		woCard3.setIcon(new ImageIcon(this.getClass().getResource("/res/cards/back.png")));
		woCard3.addMouseListener(this);
		woRepPane.add(woCard3);
		
		woCard4 = new JLabel();
		woCard4.setHorizontalAlignment(JLabel.CENTER);
		woCard4.setBounds(580, 40, 180, 280);
		woCard4.setVisible(true);
		woCard4.setIcon(new ImageIcon(this.getClass().getResource("/res/cards/back.png")));
		woCard4.addMouseListener(this);
		woRepPane.add(woCard4);
		
		woCard5 = new JLabel();
		woCard5.setHorizontalAlignment(JLabel.CENTER);
		woCard5.setBounds(770, 40, 180, 280);
		woCard5.setVisible(true);
		woCard5.setIcon(new ImageIcon(this.getClass().getResource("/res/cards/back.png")));
		woCard5.addMouseListener(this);
		woRepPane.add(woCard5);
	}
	
	public void initDraw() {
		drawOptionsLbl = new JLabel("Draw # of Cards", JLabel.CENTER);
		drawOptionsLbl.setBounds(0, 10, 100, 30);
		drawOptionsLbl.setFont(font);
		drawOptionsLbl.setBackground(Color.LIGHT_GRAY);
		drawOptionsLbl.setOpaque(true);
		drawOptions.add(drawOptionsLbl);
		
		num1 = new JButton("1 Card");
		num1.setBounds(10, 50, 80, 50);
		num1.setBackground(Color.WHITE);
		num1.setFont(font);
		num1.addActionListener(this);
		drawOptions.add(num1);
		
		num2 = new JButton("2 Cards");
		num2.setBounds(10, 120, 80, 50);
		num2.setBackground(Color.WHITE);
		num2.setFont(font);
		num2.addActionListener(this);
		drawOptions.add(num2);
		
		num3 = new JButton("3 Cards");
		num3.setBounds(10, 190, 80, 50);
		num3.setBackground(Color.WHITE);
		num3.setFont(font);
		num3.addActionListener(this);
		drawOptions.add(num3);
		
		num4 = new JButton("4 Cards");
		num4.setBounds(10, 260, 80, 50);
		num4.setBackground(Color.WHITE);
		num4.setFont(font);
		num4.addActionListener(this);
		drawOptions.add(num4);
		
		num5 = new JButton("5 Cards");
		num5.setBounds(10, 330, 80, 50);
		num5.setBackground(Color.WHITE);
		num5.setFont(font);
		num5.addActionListener(this);
		drawOptions.add(num5);
		
		controllerLbl = new JLabel("Control", JLabel.CENTER);
		controllerLbl.setBounds(0, 390, 100, 30);
		controllerLbl.setFont(font);
		controllerLbl.setBackground(Color.LIGHT_GRAY);
		controllerLbl.setOpaque(true);
		drawOptions.add(controllerLbl);
		
		drawBtn = new JButton("Draw!");
		drawBtn.setBounds(10, 430, 80, 40);
		drawBtn.setBackground(Color.WHITE);
		drawBtn.setFont(font);
		drawBtn.addActionListener(this);
		drawOptions.add(drawBtn);
		
		nextTrialBtn = new JButton("Next Trial");
		nextTrialBtn.setBounds(10, 480, 80, 40);
		nextTrialBtn.setBackground(Color.WHITE);
		nextTrialBtn.setFont(smfont);
		nextTrialBtn.addActionListener(this);
		drawOptions.add(nextTrialBtn);
		
		openAllBtn = new JButton("Open All");
		openAllBtn.setBounds(10, 530, 80, 40);
		openAllBtn.setBackground(Color.WHITE);
		openAllBtn.setFont(font);
		openAllBtn.addActionListener(this);
		drawOptions.add(openAllBtn);
		
		generateBtn = new JButton("Generate");
		generateBtn.setBounds(10, 580, 80, 40);
		generateBtn.setBackground(Color.WHITE);
		generateBtn.setFont(smfont);
		generateBtn.addActionListener(this);
		drawOptions.add(generateBtn);
		
		nTrialLbl = new JLabel("0 Trial/s", JLabel.CENTER);
		nTrialLbl.setBounds(10, 630, 80, 30);
		nTrialLbl.setFont(lgfont);
		nTrialLbl.setBackground(Color.BLACK);
		nTrialLbl.setForeground(Color.WHITE);
		nTrialLbl.setOpaque(true);
		drawOptions.add(nTrialLbl);
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void disableButtons() {
		num1.setEnabled(false);
		num2.setEnabled(false);
		num3.setEnabled(false);
		num4.setEnabled(false);
		num5.setEnabled(false);
		drawBtn.setEnabled(false);
	}
	
	public void resetButtons() {
		num1.setEnabled(true);
		num2.setEnabled(true);
		num3.setEnabled(true);
		num4.setEnabled(true);
		num5.setEnabled(true);
		drawBtn.setEnabled(true);
	}
	
	public void showCards(int n) {
		switch(n) {
		case 1:
			hideCards();
			wCard1.setVisible(true);
			woCard1.setVisible(true);
			break;
		case 2:
			hideCards();
			wCard1.setVisible(true);
			wCard2.setVisible(true);
			woCard1.setVisible(true);
			woCard2.setVisible(true);
			break;
		case 3:
			hideCards();
			wCard1.setVisible(true);
			wCard2.setVisible(true);
			wCard3.setVisible(true);
			woCard1.setVisible(true);
			woCard2.setVisible(true);
			woCard3.setVisible(true);
			break;
		case 4:
			hideCards();
			wCard1.setVisible(true);
			wCard2.setVisible(true);
			wCard3.setVisible(true);
			wCard4.setVisible(true);
			woCard1.setVisible(true);
			woCard2.setVisible(true);
			woCard3.setVisible(true);
			woCard4.setVisible(true);
			break;
		case 5:
			hideCards();
			wCard1.setVisible(true);
			wCard2.setVisible(true);
			wCard3.setVisible(true);
			wCard4.setVisible(true);
			wCard5.setVisible(true);
			woCard1.setVisible(true);
			woCard2.setVisible(true);
			woCard3.setVisible(true);
			woCard4.setVisible(true);
			woCard5.setVisible(true);
			break;
		}
	}
	
	public void hideCards() {
		wCard1.setVisible(false);
		wCard2.setVisible(false);
		wCard3.setVisible(false);
		wCard4.setVisible(false);
		wCard5.setVisible(false);
		woCard1.setVisible(false);
		woCard2.setVisible(false);
		woCard3.setVisible(false);
		woCard4.setVisible(false);
		woCard5.setVisible(false);
	}
	
	public void resetCards() {
		wCard1.setIcon(back);
		wCard2.setIcon(back);
		wCard3.setIcon(back);
		wCard4.setIcon(back);
		wCard5.setIcon(back);
		woCard1.setIcon(back);
		woCard2.setIcon(back);
		woCard3.setIcon(back);
		woCard4.setIcon(back);
		woCard5.setIcon(back);
	}
}
