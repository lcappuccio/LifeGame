package org.systemexception.lifegame.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.systemexception.lifegame.gui.GridGui;
import org.systemexception.lifegame.gui.MainGui;
import org.systemexception.lifegame.pojo.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * @author leona 24/02/2017 21:30
 */
public class FileUtilsTest {

	private static final MainGui mainGui = new MainGui();

	@BeforeClass
	public static void setUp() {
		mainGui.main(null);
	}

	@Test
	public void should_read_preset_file() throws URISyntaxException, IOException {
		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
		URI testFileUri = systemClassLoader.getResource("presets/acorn.life").toURI();
		File testFile = new File(testFileUri);
		GridGui gridGui = FileUtils.gridGuiFromFile(testFile);

		assertNotNull(gridGui);
		assertEquals(512, gridGui.getBoard().getBoardRows());
		assertEquals(344, gridGui.getBoard().getBoardCols());
		assertEquals(7, gridGui.getBoard().getCellAliveCount());
	}
}
