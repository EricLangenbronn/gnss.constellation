package fr.gnss.constellation.ouranos.dao.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.dao.Sp3Dao;
import fr.gnss.constellation.ouranos.util.ConfigurationLoader;

public class Sp3DaoImpl implements Sp3Dao {

	/**
	 * Le logger de la classe.
	 */
	private static final Log LOGGER = LogFactory.getLog(Sp3DaoImpl.class);

	private static FilenameFilter filter = new FilenameFilter() {
		public boolean accept(File directory, String fileName) {
			return fileName.endsWith(".sp3") || fileName.endsWith(".sp3.Z");
		}
	};

	@Override
	public List<String> getListSp3FileName() throws TechnicalException, BusinessException {
		String pathSp3Dir = ConfigurationLoader.getProperty("repertoire.sp3");
		File fileSp3Dir = new File(pathSp3Dir);
		List<String> sp3FileNames = new ArrayList<>();

		for (File sp3File : fileSp3Dir.listFiles(filter)) {
			sp3FileNames.add(sp3File.getName());
		}

		return sp3FileNames;
	}

	@Override
	public List<File> getListSp3File() throws TechnicalException, BusinessException {

		String pathSp3Dir = ConfigurationLoader.getProperty("repertoire.sp3");
		File fileSp3Dir = new File(pathSp3Dir);
		List<File> sp3FileNames = new ArrayList<>();

		for (File sp3File : fileSp3Dir.listFiles(filter)) {
			sp3FileNames.add(sp3File);
		}

		return sp3FileNames;
	}

}
