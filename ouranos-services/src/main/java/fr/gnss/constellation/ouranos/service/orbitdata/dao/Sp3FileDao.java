package fr.gnss.constellation.ouranos.service.orbitdata.dao;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;

public class Sp3FileDao implements ISp3FileDao {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(Sp3FileDao.class);

	private static FilenameFilter filter = new FilenameFilter() {
		public boolean accept(File directory, String fileName) {
			return fileName.endsWith(".sp3") || fileName.endsWith(".sp3.Z");
		}
	};

	@Override
	public List<String> getListSp3FileName(String pathSp3Dir) throws TechnicalException, BusinessException {
		File fileSp3Dir = new File(pathSp3Dir);
		// TODO tester l'existance du fichier fileSp3Dir
		List<String> sp3FileNames = Arrays.asList(fileSp3Dir.list(filter));

		return sp3FileNames;
	}

	@Override
	public List<File> getListSp3File(String pathSp3Dir) throws TechnicalException, BusinessException {

		File fileSp3Dir = new File(pathSp3Dir);
		if (fileSp3Dir.exists()) {
			List<File> sp3FileNames = new ArrayList<>();

			// TODO tester l'existance du fichier fileSp3Dir
			if (fileSp3Dir.listFiles(filter) == null || fileSp3Dir.listFiles(filter).length == 0) {
				// on fait rien
			} else {
				for (File sp3File : fileSp3Dir.listFiles(filter)) {
					LOGGER.info("Chargement fichier : " + sp3File.getName());
					sp3FileNames.add(sp3File);
				}
			}

			return sp3FileNames;
		} else {
			String message = "Impossible d'accéder au répertoire contenantles fichiers Sp3. [pathSp3Dir=" + pathSp3Dir
					+ "]";
			LOGGER.error(message);
			throw new TechnicalException(message);
		}

	}

	@Override
	public File getFile(String repertoireSp3, Sp3FileName sp3FileName) throws BusinessException {
		Path pathFile = Paths.get(repertoireSp3, sp3FileName.getFileName(false));
		File sp3File = pathFile.toFile();

		if (sp3File.exists()) {
			// c'est ok
		} else {
			sp3File = null;
		}

		return sp3File;
	}
}
