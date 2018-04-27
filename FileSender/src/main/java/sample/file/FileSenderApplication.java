/**
 *
 */
package sample.file;

import java.util.logging.Logger;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import sample.file.domain.FileSender;

/**
 * Send a file through HTTP as chuncked octet stream.
 *
 */
public class FileSenderApplication {

	private static final Logger LOG = Logger.getLogger(FileSenderApplication.class.getName());

	private static Options options = null;

	/**
	 * Run file sender app.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		System.exit(doWork(args));

	}

	protected static final int doWork(final String[] args) {
		init(args);

		int exitCode = 0;
		final CommandLineParser parser = new DefaultParser();
		try {
			final CommandLine cmd = parser.parse(options, args);
			if (cmd.hasOption("a") && cmd.hasOption("f")) {
				FileSender fs = new FileSender();
				fs.doWork(cmd.getOptionValue("f"), cmd.getOptionValue("a"));
			} else {
				exitCode = 1;
				usage();
			}
		} catch (Exception e) {
			exitCode = 2;
			LOG.info("exception executing: " + e.toString());
			usage();
		}
		return exitCode;
	}

	private static void usage() {
		StringBuffer sb = new StringBuffer("Utility to send a named file through HTTP to a given adress\nusage:\n");
		for (final Option o : options.getOptions()) {
			sb.append("    ").append(o.toString()).append("\n");
		}
		LOG.info(sb.toString());
	}

	private static void init(final String args[]) {
		options = new Options();
		options.addOption("a", true, "specify target adress");
		options.addOption("f", true, "specify path to input file");
	}
}
