package org.framework.gui;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.design.fileutils.LargeFileReader;
import org.design.primitives.Poem;
import org.framework.poemtypes.CoupletPoem;
import org.framework.poemtypes.HaikuPoem;
import org.framework.poemtypes.LimerickPoem;
import org.framework.recognizers.PoetryRecognizer;

/*
 * ActionListeners need to de-coupled
 * Arvind needs to remove the hackish code done for deliverable 3, 
 * and incorporate better checking (i.e re-write the logic of the Actionlisteners)
 */

public class PoetryFrame extends JFrame implements ActionListener, MouseListener {
	
	private Container mainContainer = new Container();
	private Container proseContainer = new Container();
	private Container genContainer = new Container();
	private Container writeContainer = new Container();
	
	private JPanel windows;
	private JPanel cards;
	private JFileChooser fc;
	private JTextArea prose;
	private JTextField fileName;
	private JScrollPane jScrollPane;
	private JTextField corpus;
	private JTextArea text;
	private JTextArea suggest;
	
	
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
		prose = new JTextArea(PLACEHOLDER);
		prose.setRows(20);
		prose.setColumns(5);
		
		jScrollPane = new JScrollPane(prose);
				
		JLabel or = new JLabel("- or -");
		fileName = new JTextField("Select Input File");
		fileName.setEditable(false);
		
		fileName.addMouseListener(this);
		prose.addMouseListener(this);
		
		JButton open = new JButton(OPEN);
		open.addActionListener(this);
		
		SequentialGroup hGroup = g.createSequentialGroup();
		ParallelGroup h1 = g.createParallelGroup(GroupLayout.Alignment.LEADING);
		SequentialGroup h2 = g.createSequentialGroup();
		h1.addComponent(input);
		h1.addComponent(jScrollPane);
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
		vGroup.addComponent(jScrollPane, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE);
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
		JButton searchButton = new JButton(SEARCH);
		searchButton.addActionListener(this);
		
		buttonPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
		buttonPanel.add(searchButton);
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
		JPanel haiku, lim, quat, coup;
		haiku = new JPanel();
		lim = new JPanel();
		quat = new JPanel();
		coup = new JPanel();
		
		// Define quatrain options (for demo - not final)
		GroupLayout g_quat = new GroupLayout(quat);
		quat.setLayout(g_quat);
		JLabel scheme = new JLabel("Rhyme Scheme");
		JComboBox<String> schemeBox = new JComboBox<String>(RHYME_SCHEMES);
		schemeBox.setPreferredSize(new Dimension(100, 25));
		JLabel meter = new JLabel("Meter");
		JComboBox<String> meters = new JComboBox<String>(new String[]{"Iambic"});
		meters.setPreferredSize(new Dimension(100, 25));
		JCheckBox allit = new JCheckBox("Alliteration");
		JCheckBox asson = new JCheckBox("Assonance");
		
		SequentialGroup hGroup_quat = g_quat.createSequentialGroup();
		ParallelGroup h1_quat = g_quat.createParallelGroup(GroupLayout.Alignment.LEADING);
		ParallelGroup h2_quat = g_quat.createParallelGroup(GroupLayout.Alignment.LEADING);
		
		h1_quat.addComponent(scheme);
		h1_quat.addComponent(meter);
		h1_quat.addComponent(allit);
		h1_quat.addComponent(asson);
		h2_quat.addComponent(schemeBox, 0, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE);
		h2_quat.addComponent(meters, 0, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE);
		hGroup_quat.addContainerGap();
		hGroup_quat.addGroup(h1_quat);
		hGroup_quat.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
		hGroup_quat.addGroup(h2_quat);
		hGroup_quat.addContainerGap();
		g_quat.setHorizontalGroup(hGroup_quat);
		
		SequentialGroup vGroup_quat = g_quat.createSequentialGroup();
		ParallelGroup v1_quat = g_quat.createParallelGroup(GroupLayout.Alignment.LEADING);
		ParallelGroup v2_quat = g_quat.createParallelGroup(GroupLayout.Alignment.LEADING);
		v1_quat.addComponent(scheme, 0, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE);
		v1_quat.addComponent(schemeBox, 0, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE);
		v2_quat.addComponent(meter, 0, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE);
		v2_quat.addComponent(meters, 0, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE);
		vGroup_quat.addContainerGap();
		vGroup_quat.addGroup(v1_quat);
		vGroup_quat.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
		vGroup_quat.addGroup(v2_quat);
		vGroup_quat.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
		vGroup_quat.addComponent(allit);
		vGroup_quat.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
		vGroup_quat.addComponent(asson);		
		g_quat.setVerticalGroup(vGroup_quat);
		
		cards.add(coup, POEM_TYPES[0]);
		cards.add(haiku, POEM_TYPES[1]);
		cards.add(lim, POEM_TYPES[2]);
		cards.add(quat, POEM_TYPES[3]);
		
		// Create Options menu
		JLabel corp = new JLabel("Corpus(Optional)");
		JLabel type = new JLabel("Poem Type");
		corpus = new JTextField("Select a text file");
		corpus.addMouseListener(this);
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
		JButton generateButton = new JButton(GENER);
		generateButton.addActionListener(this);
		
		buttonPanel.add(generateButton);
		buttonPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
		buttonPanel.add(cancel);
		buttonPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
		addAComponent(buttonPanel, genContainer);
	}
	
	private void initWriteWindow() {
		writeContainer.setLayout(new BoxLayout(writeContainer, BoxLayout.Y_AXIS));
		
		// Create title and quote
		JLabel title = new JLabel(WRITE);
		Font f = title.getFont();
		title.setFont(new Font(f.getFontName(), f.getStyle(), 24));
		
		
		// Create input panel
		JPanel input = new JPanel();
		GroupLayout g = new GroupLayout(input);
		input.setLayout(g);
		
		// Setup components
		JLabel rhyme = new JLabel("Rhyme Scheme");
		JComboBox<String> scheme = new JComboBox<String>(RHYME_SCHEMES);
		text = new JTextArea();
		JScrollPane sp_text = new JScrollPane(text);
		sp_text.setMinimumSize(new Dimension(200, 300));
		
		JLabel suggestedWords = new JLabel("Suggested Words");
		suggest = new JTextArea();
		JScrollPane sp_sug = new JScrollPane(suggest);
		
		
		// Add to group layout
		SequentialGroup hGroup = g.createSequentialGroup();
		SequentialGroup h1 = g.createSequentialGroup().addComponent(rhyme).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(scheme);
		ParallelGroup h2 = g.createParallelGroup().addGroup(h1).addComponent(sp_text);
		ParallelGroup h3 = g.createParallelGroup().addComponent(suggestedWords).addComponent(sp_sug);
		hGroup.addContainerGap();
		hGroup.addGroup(h2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(h3);
		hGroup.addContainerGap();
		g.setHorizontalGroup(hGroup);
		
		SequentialGroup vGroup = g.createSequentialGroup();
		ParallelGroup v1 = g.createParallelGroup().addComponent(rhyme, 0, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE);
		v1.addComponent(scheme, 0, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE);
		SequentialGroup v2 = g.createSequentialGroup().addComponent(suggestedWords).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(sp_sug);
		ParallelGroup v3 = g.createParallelGroup().addComponent(sp_text).addGroup(v2);
		vGroup.addGroup(v1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(v3);
		g.setVerticalGroup(vGroup);
		
		// Cancel button
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		JButton cancel = new JButton(CANCEL);
		cancel.addActionListener(this);
		
		// Add elements to container
		writeContainer.add(Box.createVerticalGlue());
		addAComponent(title, writeContainer);
		addAComponent(new JLabel("\"Poetry is a deal of joy and pain and wonder, with a dash of the dictionary.\""), writeContainer);
		addAComponent(new JLabel("-Khalil Gibran"), writeContainer);
		writeContainer.add(Box.createVerticalGlue());
		writeContainer.add(input);
		writeContainer.add(Box.createVerticalGlue());
		addAComponent(cancel, writeContainer);
		writeContainer.add(Box.createVerticalGlue());
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(arg0.getActionCommand());
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
		} else if(arg0.getActionCommand().equals(OPEN)) {
			int ret = fc.showOpenDialog(this);
			if(ret == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				fileName.setText(file.getName());
				prose.setText("");
				//USE FILE AS NEEDED
				java.util.List<String> lines;
				try 
				{
					lines = LargeFileReader.readFile(file.getAbsolutePath());
					for(String line: lines)
					{
						prose.append(line+"\n");
					}
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace(); //should never happen
				}
			}
		} else if(arg0.getActionCommand().equals(SEARCH)) {
			recognize();
		} else if(arg0.getActionCommand().equals(GENER)) {
			// GENERATION CODE GOES HERE
		} else if(arg0.getActionCommand().equals(QUIT)) {
			System.exit(0);
		} 
	}
	
	private void addAComponent(JComponent comp, Container container) {
		comp.setAlignmentX(CENTER_ALIGNMENT);
		container.add(comp);
	}
	
	public static void main(String[] args) {
		/* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PoetryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PoetryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PoetryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PoetryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
	private static final String[] POEM_TYPES = {"Couplet", "Haiku", "Limerick", "Quatrain"};
	private static final String JTEXTFIELD = "JTextField";
	private static final String PLACEHOLDER = "<!---- Insert Prose Here ---- !>";
//	private static final String JTEXTAREA = "JTextArea";
	public static final String[] RHYME_SCHEMES = new String[]{"AABB", "ABAB", "ABCB"};

	/*
	 * Recognize the type of poem
	 */
	private void recognize()
	{
		
		//System.out.println("Recognition logic");
		/*if(prose.getText().length() == 0)
		{
			return;
		}
		// Recognize the different types of poems
		java.util.List<String> lines = Arrays.asList(prose.getText().split("\n"));	//each line of the poem is on a new line
		Poem poem = new HaikuPoem();
		poem.setLines(lines);
		if(PoetryRecognizer.isValidPoetry(poem))
		{
			JOptionPane.showMessageDialog(this, "HAIKU POEM");
			return;
		}
		
		poem = new LimerickPoem();
		poem.setLines(lines);
		if(PoetryRecognizer.isValidPoetry(poem))
		{
			JOptionPane.showMessageDialog(this, "LIMERICK POEM");
			return;
		}
		
		poem = new CoupletPoem();
		poem.setLines(lines);
		if(PoetryRecognizer.isValidPoetry(poem))
		{
			JOptionPane.showMessageDialog(this, "COUPLET POEM");
			return;
		}
		
		JOptionPane.showMessageDialog(this, "Not a Haiku/Couplet/Limerick");
		*/
		try {
			String inputFile = "";
			if((fc == null || fc.getSelectedFile() == null) && prose.getText().equals(PLACEHOLDER)) 
			{
				JOptionPane.showMessageDialog(this, "Choose a file", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
			else
			{
				inputFile = "resources/temp";
				File file = new File(inputFile);
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(prose.getText());
				bw.close();
			}

			
			List<Poem> couplet_poem_list = PoetryRecognizer.identifyPoemsFromCorpus(inputFile, new CoupletPoem());
			List<Poem> haiku_poem_list = PoetryRecognizer.identifyPoemsFromCorpus(inputFile, new HaikuPoem());
			List<Poem> limerick_poem_list = PoetryRecognizer.identifyPoemsFromCorpus(inputFile, new LimerickPoem());
			
			if(couplet_poem_list.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "No Couplet poems found");
			}
			if(haiku_poem_list.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "No Haiku poems found");
			}
			if(limerick_poem_list.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "No Limerick poems found");
			}
			if(!(limerick_poem_list.isEmpty() && haiku_poem_list.isEmpty() && 
					couplet_poem_list.isEmpty()))
			{
				String str = "Number of Couplet Poems found are: "+ couplet_poem_list.size();
				str += "\n" + "Number of Haiku Poems found are: "+ haiku_poem_list.size();
				str += "\n" + "Number of Limerick Poems found are: "+ limerick_poem_list.size();
				JOptionPane.showMessageDialog(this, str);
				
				java.util.List<Poem> poem_list = new ArrayList<Poem>();
				
				poem_list.addAll(couplet_poem_list);
				poem_list.addAll(haiku_poem_list);
				poem_list.addAll(limerick_poem_list);
				
				File file = new File("resources/Poems_Recognized");
				
				if (!file.exists()) 
				{
					file.createNewFile();
				}
				
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
								
				for(Poem p: poem_list)
				{
					System.out.println(p);
					bw.write(p.toString());
				}
				bw.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent().getClass().getSimpleName().equals(JTEXTFIELD))
		{
			int ret = fc.showOpenDialog(this);
			if(ret == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				fileName.setText(file.getName());
				prose.setText("");
				//USE FILE AS NEEDED
				java.util.List<String> lines;
				try 
				{
					lines = LargeFileReader.readFile(file.getAbsolutePath());
					for(String line: lines)
					{
						prose.append(line+"\n");
					}					
				} 
				catch (IOException ex) 
				{
					// TODO Auto-generated catch block
					ex.printStackTrace(); //should never happen
				}
			}
		}
		
		else
		{
			if(prose.getText().equals(PLACEHOLDER))
			{
				prose.setText("");
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
