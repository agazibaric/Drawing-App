package hr.fer.zemris.java.hw16.jvdraw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import hr.fer.zemris.java.hw16.jvdraw.components.JColorArea;
import hr.fer.zemris.java.hw16.jvdraw.components.JDrawingCanvas;
import hr.fer.zemris.java.hw16.jvdraw.components.StatusBar;
import hr.fer.zemris.java.hw16.jvdraw.geomobjects.Circle;
import hr.fer.zemris.java.hw16.jvdraw.geomobjects.FilledCircle;
import hr.fer.zemris.java.hw16.jvdraw.geomobjects.Line;
import hr.fer.zemris.java.hw16.jvdraw.models.DocumentModel;
import hr.fer.zemris.java.hw16.jvdraw.models.GeometricalObjectListModel;
import hr.fer.zemris.java.hw16.jvdraw.tools.CircleTool;
import hr.fer.zemris.java.hw16.jvdraw.tools.FilledCircleTool;
import hr.fer.zemris.java.hw16.jvdraw.tools.LineTool;
import hr.fer.zemris.java.hw16.jvdraw.visitors.GeometricalObjectBBCalculator;
import hr.fer.zemris.java.hw16.jvdraw.visitors.GeometricalObjectPainter;

/**
 * Program that allows user to draw geometrical objects on canvas.
 * Supported geometrical objects are: line, circle and filled circle.
 * It offers user to save drawn image as '.jvd' file and to open existing '.jvd' file.
 * It offers user to export drawn image as png, gif or jpg format.
 * 
 * @author Ante Gazibaric
 * @version 1.0
 *
 */
public class JVDraw extends JFrame {
	
	/**
	 * Current drawing tool.
	 */
	private Tool currentTool;
	/**
	 * Drawing model object.
	 */
	private DrawingModel model;
	/**
	 * Canvas used for painting all the objects.
	 */
	private JDrawingCanvas canvas;
	/**
	 * Foreground color component.
	 */
	private JColorArea fgColorArea;
	/**
	 * Background color component.
	 */
	private JColorArea bgColorArea;
	/**
	 * Tool bar.
	 */
	private JToolBar toolBar;
	/**
	 * Button group that contains all the drawing tools.
	 */
	private ButtonGroup toolsGroup;
	/**
	 * Main panel that contains canvas.
	 */
	private JPanel mainPanel;
	/**
	 * List that shows drawn objects.
	 */
	private JList<GeometricalObject> list;
	/**
	 * Path of file that is drawn.
	 */
	private Path filePath;
	/**
	 * Button used for switching to line drawing tool.
	 */
	private JToggleButton lineBtn;
	/**
	 * Button used for switching to circle drawing tool.
	 */
	private JToggleButton circleBtn;
	/**
	 * Button used for switching to filled circle drawing tool.
	 */
	private JToggleButton filledCircleBtn;
	/**
	 * Array of all supported drawing tools.
	 */
	private Tool[] tools = new Tool[NUM_OF_TOOLS];
	/**
	 * Supported extension for exporting the drawn file.
	 */
	private static final String[] extensions = new String[] { "png", "gif", "jpg" };
	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Size of main frame.
	 */
	private static final Dimension SIZE = new Dimension(800, 600);
	/**
	 * Location of main frame.
	 */
	private static final Point LOCATION = new Point(10, 10);
	/**
	 * Number of supported tools.
	 */
	private static final int NUM_OF_TOOLS = 3;
	/**
	 * Index of line tool in tools array.
	 */
	private static final int LINE_INDEX = 0;
	/**
	 * Index of circle tool in tools array.
	 */
	private static final int CIRCLE_INDEX = 1;
	/**
	 * Index of filled circle tool in tools array.
	 */
	private static final int FILLED_CIRCLE_INDEX = 2;
	/**
	 * File extension for saving drawn file.
	 */
	private static final String FILE_EXTENSION = ".jvd";
	
	/**
	 * Main method. Accepts no arguments.
	 * 
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new JVDraw().setVisible(true);
		});
	}
	
	/**
	 * Constructor that creates new {@link JVDraw} object.
	 */
	public JVDraw() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(SIZE);
		setLocation(LOCATION);
		initGUI();
		addListeners();
	}
	
	/**
	 * Method adds listeners for {@link JVDraw}.
	 */
	private void addListeners() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				performExit();
			}
		});
	}
	
	/**
	 * Method initializes {@link JVDraw} object.
	 */
	private void initGUI() {
		
		Container cp = this.getContentPane();
		cp.setLayout(new BorderLayout());
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		cp.add(mainPanel, BorderLayout.CENTER);

		model = new DocumentModel();
		
		// Color choosers
		fgColorArea = new JColorArea(Color.BLACK, this);
		bgColorArea = new JColorArea(Color.BLACK, this);
		StatusBar statusBar = new StatusBar(fgColorArea, bgColorArea);
		mainPanel.add(statusBar, BorderLayout.PAGE_END);
		
		initToolBar();
		initTools();
		initCanvas();
		initList();
		initMenuBar();
		initActions();
		
	}
	
	/**
	 * Method initializes tool bar.
	 */
	private void initToolBar() {
		toolBar = new JToolBar();
		toolBar.setFloatable(true);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(fgColorArea);
		toolBar.add(bgColorArea);
		toolBar.addSeparator();
		this.getContentPane().add(toolBar, BorderLayout.PAGE_START);	
		initToolButtons();
	}
	
	/**
	 * Method initializes menu bar.
	 */
	private void initMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(openAction);
		fileMenu.add(saveAction);
		fileMenu.add(saveAsAction);
		fileMenu.add(exportAction);
		fileMenu.add(exitAction);
		menuBar.add(fileMenu);
		
		this.setJMenuBar(menuBar);
	}
	
	/**
	 * Method initializes actions.
	 */
	private void initActions() {
		
		saveAction.putValue(Action.NAME, "Save");
		saveAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control S"));
		saveAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
		saveAction.putValue(Action.SHORT_DESCRIPTION, "Save file as .jvd");
		
		saveAsAction.putValue(Action.NAME, "Save as");
		saveAsAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control shift S"));
		saveAsAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_A);
		saveAsAction.putValue(Action.SHORT_DESCRIPTION, "Save file as .jvd on new path");
		
		openAction.putValue(Action.NAME, "Open");
		openAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control O"));
		openAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_O);
		openAction.putValue(Action.SHORT_DESCRIPTION, "Open existing .jvd file");
		
		exportAction.putValue(Action.NAME, "Export");
		exportAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control E"));
		exportAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
		exportAction.putValue(Action.SHORT_DESCRIPTION, "Export file as image");
		
		exitAction.putValue(Action.NAME, "Exit");
		exitAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control W"));
		exitAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_W);
		exitAction.putValue(Action.SHORT_DESCRIPTION, "Exit program");
		
	}
	
	/**
	 * Method initializes {@link #list}.
	 */
	private void initList() {
		list = new JList<>(new GeometricalObjectListModel(model));
		list.setPreferredSize(new Dimension(220, 0));
		
		// Listener for editing objects in the list
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() != 2)
					return;
				
				GeometricalObject clicked = list.getSelectedValue();
				GeometricalObjectEditor editor = clicked.createGeometricalObjectEditor();
				if (JOptionPane.showConfirmDialog(JVDraw.this, editor, "Change geometrical object",
						JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					try {
						editor.checkEditing();
						editor.acceptEditing();
					} catch (JVDrawException ex) {
						JOptionPane.showMessageDialog(JVDraw.this, ex.getMessage());
					}
				}

			}
		});
		
		// Listener for moving or deleting object in list by typing specified keys
		list.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int index = list.getSelectedIndex();
				if (index == -1)
					return;
				
				GeometricalObject object = model.getObject(index);
			
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_DELETE) {
					model.remove(object);
					list.setSelectedIndex(index - 1 > 0 ? index - 1 : 0);
				} else if (keyCode == KeyEvent.VK_PLUS) {
					model.changeOrder(object, -1);
					list.setSelectedIndex(index - 1);
				} else if (keyCode == KeyEvent.VK_MINUS) {
					model.changeOrder(object, 1);
					list.setSelectedIndex(index + 1);
				}
			}
		});
		
		mainPanel.add(list, BorderLayout.EAST);
	}
	
	/**
	 * Method initializes {@link #canvas}.
	 */
	private void initCanvas() {
		canvas = new JDrawingCanvas(model, currentTool);
		canvas.setFocusable(true);
		mainPanel.add(canvas, BorderLayout.CENTER);
		
		canvas.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				currentTool.mousePressed(e);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				currentTool.mouseClicked(e);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				currentTool.mouseReleased(e);
			}
		
		});
		
		canvas.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				currentTool.mouseDragged(e);
				canvas.repaint();
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				currentTool.mouseMoved(e);
				canvas.repaint();
			}
		});
	}
	
	/**
	 * Method initializes buttons used for switching between drawing tools.
	 */
	private void initToolButtons() {
		toolsGroup = new ButtonGroup();
		lineBtn = new JToggleButton("LINE");
		circleBtn = new JToggleButton("CIRCLE");
		filledCircleBtn = new JToggleButton("FILLED CIRCLE");
		lineBtn.setSelected(true);
		toolsGroup.add(lineBtn);
		toolsGroup.add(circleBtn);
		toolsGroup.add(filledCircleBtn);
		toolBar.add(lineBtn);
		toolBar.add(circleBtn);
		toolBar.add(filledCircleBtn);

		lineBtn.addActionListener(l -> {
			currentTool = tools[LINE_INDEX];
			canvas.setCurrentTool(currentTool);
		});
		
		circleBtn.addActionListener(l -> {
			currentTool = tools[CIRCLE_INDEX];
			canvas.setCurrentTool(currentTool);
		});
		
		filledCircleBtn.addActionListener(l -> {
			currentTool = tools[FILLED_CIRCLE_INDEX];
			canvas.setCurrentTool(currentTool);
		});
	}
	
	/**
	 * Method initializes tools array with supported drawing tools.
	 */
	private void initTools() {
		tools[LINE_INDEX] = new LineTool(model, fgColorArea);
		tools[CIRCLE_INDEX] = new CircleTool(model, fgColorArea);
		tools[FILLED_CIRCLE_INDEX] = new FilledCircleTool(model, bgColorArea, fgColorArea);
		currentTool = tools[LINE_INDEX];
	}
	
	/**
	 * Action saves current file as '.jvd".
	 * If file doesn't have defined path, it offers user to choose it.
	 */
	private Action saveAction = new AbstractAction() {
		
		/**
		 * Serial ID.
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			performSaveAction(false);
		}
	};

	/**
	 * Action saves current file as '.jvd'.
	 * It always offers user to choose new path of the file.
	 */
	private Action saveAsAction = new AbstractAction() {

		/**
		 * Serial ID.
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			performSaveAction(true);
		}
	};

	/**
	 * Action offers user to open existing '.jvd' file.
	 */
	private Action openAction = new AbstractAction() {

		/**
		 * Serial ID.
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Open file");
			if (chooser.showOpenDialog(JVDraw.this) != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(
						JVDraw.this, "File not opened", "Information", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			Path path = chooser.getSelectedFile().toPath();
			if (!isValidPath(path)) {
				JOptionPane.showMessageDialog(
						JVDraw.this, "Invalid file", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			filePath = path;
			clearModel();
			if (!inputFile(filePath)) {
				JOptionPane.showMessageDialog(
						JVDraw.this, "Error occurred while reading from file", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			canvas.setModified(false);
		}
	};

	/**
	 * Action exports drawn file as image.
	 * Supported image formats are: 'png', 'gif' and 'jpg'.
	 */
	private Action exportAction = new AbstractAction() {

		/**
		 * Serial ID.
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel extensionPanel = new JPanel();
			extensionPanel.add(new JLabel("Choose file extension"));
			int result = JOptionPane.showOptionDialog(
					JVDraw.this, extensionPanel, "Export file", 
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, 
					null, extensions, null);
			
			String extension;
			switch (result) {
			case JOptionPane.YES_OPTION:
				extension = "png";
				break;
			case JOptionPane.NO_OPTION:
				extension = "gif";
				break;
			case JOptionPane.CANCEL_OPTION:
				extension = "jpg";
				break;
			default:
				JOptionPane.showMessageDialog(
						JVDraw.this, "File is not exported", "Information", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Export file");
			if (chooser.showSaveDialog(JVDraw.this) != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(
						JVDraw.this, "File is not exported", "Information", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			GeometricalObjectBBCalculator bbcalc = new GeometricalObjectBBCalculator();
			for (int i = 0, n = model.getSize(); i < n; i++) {
				model.getObject(i).accept(bbcalc);
			}

			Rectangle box = bbcalc.getBoundingBox();
			BufferedImage image = new BufferedImage(box.width, box.height, BufferedImage.TYPE_3BYTE_BGR);
			Graphics2D g2d = image.createGraphics();
						
			g2d.translate(-box.x, -box.y);
			g2d.setColor(Color.WHITE);
			g2d.fillRect(box.x, box.y, box.width, box.height);
			GeometricalObjectPainter painter = new GeometricalObjectPainter(g2d);
			for (int i = 0, n = model.getSize(); i < n; i++) {
				model.getObject(i).accept(painter);
			}
			g2d.dispose();
			
			String fileName = chooser.getSelectedFile().getAbsolutePath();
			if (!fileName.endsWith(extension)) {
				fileName += "." + extension;
			}
			File file = new File(fileName);
			try {
				ImageIO.write(image, extension, file);
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(
						JVDraw.this, "Error occurred during export!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			JOptionPane.showMessageDialog(
					JVDraw.this, "File is exported!", "Information", JOptionPane.INFORMATION_MESSAGE);
		}
	};

	/**
	 * Method exits application.
	 * If user haven't saved drawn file, it offers him to save it first.
	 */
	private Action exitAction = new AbstractAction() {

		/**
		 * Serial ID.
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			performExit();
		}
	};
	
	/**
	 * Method exits application by disposing main frame.
	 * Method checks if file is modified and offers user to save it before exiting.
	 * 
	 */
	private void performExit() {
		if (canvas.isModified()) {
			int result = JOptionPane.showConfirmDialog(JVDraw.this, 
					"File is modified!\nDo you want to save it before exit?",
					"Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			
			if (result == JOptionPane.CANCEL_OPTION)
				return;
			
			if (result == JOptionPane.YES_OPTION) {
				performSaveAction(false);
			}
		}
		dispose();
	}
	
	/**
	 * Method saves file as '.jvd' file.
	 * If file path is not defined or given flag {@code toChoosePath} is {@code true}
	 * it offers user to choose new path of the file where it will be saved.
	 * 
	 * @param toChoosePath flag that shows will the new path of the file be chosen.
	 */
	private void performSaveAction(boolean toChoosePath) {
		if (filePath == null || toChoosePath) {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Save document");
			
			if (chooser.showSaveDialog(JVDraw.this) != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(JVDraw.this, "File is not saved", "Warning",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			String pathString = chooser.getSelectedFile().toString();
			if (!pathString.endsWith(FILE_EXTENSION)) {
				pathString = pathString.concat(FILE_EXTENSION);
			}
			filePath = Paths.get(pathString);
		}
		
		if (!saveFile(filePath))
			return;
		
		canvas.setModified(false);
		JOptionPane.showMessageDialog(JVDraw.this, "File saved", "Information",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Method writes content of the file to the given {@code path}.
	 * 
	 * @param path path of the file
	 * @return     {@code true} if file is successfully saved,<br>
	 * 			   {@code false} otherwise
	 */
	private boolean saveFile(Path path) {
		String fileContent = getFileContent();
		byte[] data = fileContent.getBytes();
		try {
			Files.write(path, data);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(JVDraw.this, "Error occured during saving", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	/**
	 * Method returns file content as string.
	 * 
	 * @return string representation of the drawn picture
	 */
	private String getFileContent() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0, n = model.getSize(); i < n; i++) {
			sb.append(model.getObject(i).asText()).append("\n");
		}
		return sb.toString();
	}
	
	/**
	 * File reads file from given {@code filePath}.
	 * 
	 * @param filePath path of the 'jvd' file
	 * @return         {@code true} if file was read successfully,
	 * 				   {@code false} otherwise
	 */
	private boolean inputFile(Path filePath) {
		try {
			List<String> lines = Files.readAllLines(filePath);
			for (String line : lines) {
				String[] parts = line.split("\\s");
				loadFromArray(parts);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Method clears all model's objects.
	 */
	private void clearModel() {
		while (model.getSize() > 0) {
			GeometricalObject obj = model.getObject(0);
			model.remove(obj);
		}
	}
	
	/**
	 * Method loads geometrical object from given array {@code parts}.
	 * 
	 * @param parts array that contains informations about geometrical object
	 */
	private void loadFromArray(String[] parts) {
		String object = parts[0];
		switch (object) {
		case "LINE":
			loadLine(parts);
			return;
		case "CIRCLE":
			loadCircle(parts);
			return;
		case "FCIRCLE":
			loadFilledCircle(parts);
			return;
		default:
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Method loads {@link Line} from given array {@code parts}.
	 * 
	 * @param parts array that contains information for {@code Line} object.
	 */
	private void loadLine(String[] parts) {
		int x1 = Integer.parseInt(parts[1]);
		int y1 = Integer.parseInt(parts[2]);
		int x2 = Integer.parseInt(parts[3]);
		int y2 = Integer.parseInt(parts[4]);
		int r = Integer.parseInt(parts[5]);
		int g = Integer.parseInt(parts[6]);
		int b = Integer.parseInt(parts[7]);
		model.add(new Line(x1, y1, x2, y2, new Color(r, g, b)));
	}
	
	/**
	 * Method loads {@link Circle} from given array {@code parts}.
	 * 
	 * @param parts array that contains information for {@code Circle} object.
	 */
	private void loadCircle(String[] parts) {
		int x = Integer.parseInt(parts[1]);
		int y = Integer.parseInt(parts[2]);
		int radius = Integer.parseInt(parts[3]);
		int r = Integer.parseInt(parts[4]);
		int g = Integer.parseInt(parts[5]);
		int b = Integer.parseInt(parts[6]);
		model.add(new Circle(x, y, radius, new Color(r, g, b)));
	}
	
	/**
	 * Method loads {@link FilledCircle} from given array {@code parts}.
	 * 
	 * @param parts array that contains information for {@code FilledCircle} object.
	 */
	private void loadFilledCircle(String[] parts) {
		int x = Integer.parseInt(parts[1]);
		int y = Integer.parseInt(parts[2]);
		int radius = Integer.parseInt(parts[3]);
		int rFg = Integer.parseInt(parts[4]);
		int gFg = Integer.parseInt(parts[5]);
		int bFg = Integer.parseInt(parts[6]);
		int rBg = Integer.parseInt(parts[7]);
		int gBg = Integer.parseInt(parts[8]);
		int bBg = Integer.parseInt(parts[9]);
		model.add(new FilledCircle(x, y, radius, new Color(rBg, gBg, bBg), new Color(rFg, gFg, bFg)));
	}
	
	/**
	 * Method checks if given {@code path} is valid 'jvd' path.
	 * 
	 * @param path path that is checked
	 * @return     {@code true} if path is valid,<br>
	 *        	   {@code false} otherwise
	 */
	private boolean isValidPath(Path path) {
		if (!Files.exists(path) || !Files.isRegularFile(path)) 
			return false;
		
		return path.toString().endsWith(FILE_EXTENSION);
	}

}
