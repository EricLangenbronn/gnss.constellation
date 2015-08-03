package fr.gnss.constellation.librairy.almanach.sp3;

import java.io.File;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;

import fr.gnss.constellation.librairy.almanach.parser.sp3.Sp3Parser;

public class Sp3FileReader {

	private File fl;
	private Sp3Parser parser;

	public Sp3FileReader(File p_file) throws Exception {
		fl = new File(p_file, "r");
		parser = new Sp3Parser(fl);
	}

	public Sp3FileReader(String p_fileName) throws Exception {
		fl = new File(p_fileName, "r");
		parser = new Sp3Parser(fl);
	}

	public double[][] getPositions(LocalDateTime p_epochRecord) {
		double[][] res = new double[0][0];
		return res;
	}

	public double[] getPosition(String p_vehicleId, LocalDateTime p_epochRecord) {
		double[] res = new double[0];
		return res;
	}

}
