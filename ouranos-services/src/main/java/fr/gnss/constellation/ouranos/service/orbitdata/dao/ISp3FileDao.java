package fr.gnss.constellation.ouranos.service.orbitdata.dao;

import java.io.File;
import java.util.List;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;

public interface ISp3FileDao {

	public List<String> getListSp3FileName(String pathSp3Dir) throws TechnicalException, BusinessException;

	public List<File> getListSp3File(String pathSp3Dir) throws TechnicalException, BusinessException;

	public File getFile(String sp3Repertoire, Sp3FileName sp3FileName);
}
