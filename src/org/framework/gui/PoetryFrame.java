package org.framework.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.GroupLayout.*;

public class PoetryFrame extends JFrame implements ActionListener {
	
	private Container mainContainer = new Container();
	private Container proseContainer = new Container();
	private Container genContainer = new Container();
	private Container writeContainer = new Container();
	
	private JPanel windows;
	private JPanel cards;
	private JFileChooser fc;
	private JTextArea prose;
	private JTextField fileName;
	
	public PoetryFrame() {
		frameInit();
		setTitle("Poetry Tool");
		setLocation(50, 50);
		setSize(500, 500);
		
		initMainWindow();
		initProseWindow();
		initGenWindow();
		initWriteWindow();
		
		windows = new JPanel(new CardLayout());
		windows.add(mainContainer, CANCEL);
		windows.add(proseContainer, PROSE);
		windows.add(genContainer, GEN);
		windows.add(writeContainer, WRITE);
		
		setContentPane(windows);
		CardLayout cl = (CardLayout)windows.getLayout();
		cl.show(windows, CANCEL);
	}
	
	private void initMainWindow() {
		// Set up main window
		mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
		
		// Add text
		mainContainer.add(new JSeparator(SwingConstants.VERTICAL));
		addAComponent(new JLabel("\"Poetry is painting that speaks\""), mainContainer);
		addAComponent(new JLabel("-Plutarch"), mainContainer);
		
		// Add major functional buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		
		JButton prose = new JButton(PROSE);
		JButton gen = new JButton(GEN);
		JButton write = new JButton(WRITE);
		JButton quit = new JButton(QUIT);

		addAComponent(prose, buttonPanel);
		buttonPanel.add(new JSeparator(SwingConstants.VERTICAL));
		addAComponent(gen, buttonPanel);
		buttonPanel.add(new JSeparator(SwingConstants.VERTICAL));
		addAComponent(write, buttonPanel);
		buttonPanel.add(new JSeparator(SwingConstants.VERTICAL));
		buttonPanel.add(Box.createVerticalGlue());
		addAComponent(quit, buttonPanel);
		
		prose.addActionListener(this);
		gen.addActionListener(this);
		write.addActionListener(this);
		quit.addActionListener(this);
		
		// Add button panel
		mainContainer.add(Box.createVerticalGlue());
		mainContainer.add(new JSeparator(SwingConstants.VERTICAL));
		addAComponent(buttonPanel, mainContainer);
		mainContainer.add(new JSeparator(SwingConstants.VERTICAL));
	}
	
	private void initProseWindow() {
		proseContainer.setLayout(new BoxLayout(proseContainer, BoxLayout.Y_AXIS));
		JLabel title = new JLabel(PROSE);
		Font f = title.getFont();
		title.setFont(new Font(f.getFontName(), f.getStyle(), 24));
		
		// Create input panel
		JPanel inputPanel = new JPanel();
		GroupLayout g = new GroupLayout(inputPanel);
		inputPanel.setLayout(g);
		JLabel input = new JLabel("Input");
		fc = new JFileChooser();
		prose = new JTextArea("Insert Prose Here");
		JLabel or = new JLabel("- or -");
		fileName = new JTextField("Select Input File");
		JButton open = new JButton(OPEN);
		open.addActionListener(this);
		
		SequentialGroup hGroup = g.createSequentialGroup();
		ParallelGroup h1 = g.createParallelGroup(GroupLayout.Alignment.LEADING);
		SequentialGroup h2 = g.createSequentialGroup();
		h1.addComponent(input);
		h1.addComponent(prose);
		h1.addComponent(or);
		h2.addComponent(fileName);
		h2.addComponent(open);
		h1.addGroup(h2);
		hGroup.addContainerGap();
		hGroup.addGroup(h1);
		hGroup.addContainerGap();
		g.setHorizontalGroup(hGroup);
		
		SequentialGroup vGroup = g.createSequentialGroup();
		ParallelGroup v1 = g.createParallelGroup(GroupLayout.Alignment.LEADING);
		vGroup.addComponent(input);
		vGroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
		vGroup.addComponent(prose, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE);
		vGroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
		vGroup.addComponent(or);
		vGroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
		v1.addComponent(fileName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
		v1.addComponent(open, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
		vGroup.addGroup(v1);
		g.setVerticalGroup(vGroup);
		
		// Add button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		JButton cancel = new JButton(CANCEL);
		cancel.addActionListener(this);
		
		buttonPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
		buttonPanel.add(new JButton(SEARCH));
		buttonPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
		buttonPanel.add(cancel);
		buttonPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
		
		proseContainer.add(new JSeparator(SwingConstants.VERTICAL));
		addAComponent(title, proseContainer);
		addAComponent(new JLabel("\"Always be a poet, even in prose.\""), proseContainer);
		addAComponent(new JLabel("-Charles Baudelaire"), proseContainer);
		proseContainer.add(new JSeparator(SwingConstants.VERTICAL));
		addAComponent(inputPanel, proseContainer);
		proseContainer.add(Box.createVerticalGlue());
		proseContainer.add(new JSeparator(SwingConstants.VERTICAL));
		addAComponent(buttonPanel, proseContainer);
		proseContainer.add(new JSeparator(SwingConstants.VERTICAL));
	}
	
	private void initGenWindow() {
		genContainer.setLayout(new BoxLayout(genContainer, BoxLayout.Y_AXIS));
		
		// Add title and quote
		genContainer.add(new JSeparator(SwingConstants.VERTICAL));
		JLabel title = new JLabel("Poetry Generator");
		Font f = title.getFont();
		title.setFont(new Font(f.getFontName(), f.getStyle(), 24));
		addAComponent(title, genContainer);
		addAComponent(new JLabel("\"You don't make a poem with words, but with ideas\""), genContainer);
		addAComponent(new JLabel("-Stephanie Mallarme"), genContainer);
		genContainer.add(new JSeparator(SwingConstants.VERTICAL));
		
		// Create option submenus
		cards = new JPanel(new CardLayout());
		JPanel haiku, lim, quat;
		haiku = new JPanel();
		lim = new JPanel();
		quat = new JPanel();
		
		cards.add(haiku, POEM_TYPES[0]);
		cards.add(lim, POEM_TYPES[1]);
		cards.add(quat, POEM_TYPES[2]);
		
		// Create Options menu
		JLabel corp = new JLabel("Corpus(Optional)");
		JLabel type = new JLabel("Poem Type");
		JTextField corpus = new JTextField("Select a text file");
		corpus.setPreferredSize(new Dimension(300, 20));
		JComboBox<String> poems = new JComboBox<String>(POEM_TYPES);
		poems.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				CardLayout cl = (CardLayout)cards.getLayout();
				cl.show(cards, (String)arg0.getItem());
			}			
		});
		JPanel optionsPanel = new JPanel();
		GroupLayout g = new GroupLayout(optionsPanel);
		optionsPanel.setLayout(g);
		
		// horizontal axis
		SequentialGroup hGroup = g.createSequentialGroup();
		ParallelGroup h1 = g.createParallelGroup(GroupLayout.Alignment.LEADING);
		ParallelGroup h2 = g.createParallelGroup(GroupLayout.Alignment.LEADING);
		
		h1.addComponent(corp);
		h1.addComponent(type);
		h2.addComponent(corpus);
		h2.addComponent(poems);
		hGroup.addContainerGap();
		hGroup.addGroup(h1);
		hGroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
		hGroup.addGroup(h2);
		hGroup.addContainerGap();
		
		g.setHorizontalGroup(hGroup);
		
		// vertical axis
		SequentialGroup vGroup = g.createSequentialGroup();
		ParallelGroup v1 = g.createParallelGroup(GroupLayout.Alignment.LEADING);
		ParallelGroup v2 = g.createParallelGroup(GroupLayout.Alignment.LEADING);
		
		v1.addComponent(corp);
		v1.addComponent(corpus);
		v2.addComponent(type);
		v2.addComponent(poems);
		vGroup.addContainerGap();
		vGroup.addGroup(v1);
		vGroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
		vGroup.addGroup(v2);
		vGroup.addContainerGap();
		
		g.setVerticalGroup(vGroup);
		
		genContainer.add(optionsPanel);
		genContainer.add(new JSeparator(SwingConstants.VERTICAL));
		addAComponent(cards, genContainer);
		genContainer.add(new JSeparator(SwingConstants.VERTICAL));
		
		// Add button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		JButton cancel = new JButton(CANCEL);
		cancel.addActionListener(this);
		
		buttonPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
		buttonPanel.add(new JButton(GENER));
		buttonPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
		buttonPanel.add(cancel);
		buttonPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
		addAComponent(buttonPanel, genContainer);
	}
	
	private void initWriteWindow() {
		writeContainer.setLayout(new BoxLayout(writeContainer, BoxLayout.Y_AXIS));
		writeContainer.add(new JLabel(WRITE));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		JButton cancel = new JButton(CANCEL);
		cancel.addActionListener(this);
		addAComponent(cancel, writeContainer);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals(PROSE)) {
			CardLayout cl = (CardLayout)windows.getLayout();
			cl.show(windows, PROSE);
		} else if(arg0.getActionCommand().equals(GEN)) {
			CardLayout cl = (CardLayout)windows.getLayout();
			cl.show(windows, GEN);
		} else if(arg0.getActionCommand().equals(WRITE)) {
			CardLayout cl = (CardLayout)windows.getLayout();
			cl.show(windows, WRITE);
		} else if(arg0.getActionCommand().equals(CANCEL)) {
			CardLayout cl = (CardLayout)windows.getLayout();
			cl.show(windows, CANCEL);
		} else if(arg0.getActionCommand().equals(SEARCH)) {
			// INSERT RECOGNIZING CODE HERE
		} else if(arg0.getActionCommand().equals(GENER)) {
			// INSERT GENERATING CODE HERE
		} else if(arg0.getActionCommand().equals(OPEN)) {
			int ret = fc.showOpenDialog(this);
			if(ret == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				fileName.setText(file.getName());
				//USE FILE AS NEEDED
			}
		} else if(arg0.getActionCommand().equals(QUIT)) {
			System.exit(0);
		} 
	}
	
	private void addAComponent(JComponent comp, Container container) {
		comp.setAlignmentX(CENTER_ALIGNMENT);
		container.add(comp);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PoetryFrame f = new PoetryFrame();
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setVisible(true);
			}
		});
	}

	// Constants
	private static final String PROSE = "Prose Search";
	private static final String GEN = "Poetry Generator";
	private static final String WRITE = "Writing Tool";
	private static final String QUIT = "Quit";
	private static final String CANCEL = "Cancel";
	private static final String SEARCH = "Search";
	private static final String GENER = "Generate";
	private static final String OPEN = "...";
	private static final String[] POEM_TYPES = {"Haiku", "Limerick", "Quatrain"};
}
