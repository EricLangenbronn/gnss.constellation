package fr.gnss.constellation.ouranos.dao;

import java.io.File;
import java.util.List;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileNameFormat;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileReader;

public interface Sp3Dao {

	public List<String> getListSp3FileName() throws TechnicalException, BusinessException;

	public List<File> getListSp3File() throws TechnicalException, BusinessException;
}
