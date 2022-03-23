package lab10;

import java.io.File;

public class AbstractSeleniumTest {
	static {
		System.setProperty("webdriver.gecko.driver", findFile("geckodriver"));
	}

	static private String findFile(String filename) {
		String paths[] = { "", "bin/", "target/classes" };
		for (String path : paths) {
			if (new File(path + filename).exists())
				return path + filename;
		}
		return "";
	}
}
