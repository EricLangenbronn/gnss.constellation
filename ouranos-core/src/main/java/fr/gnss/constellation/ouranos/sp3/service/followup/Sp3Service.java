package fr.gnss.constellation.ouranos.sp3.service.followup;


import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.sp3.infrastructure.ISp3InputStreamDao;
import fr.gnss.constellation.ouranos.sp3.persitence.ISp3FileDao;
import fr.gnss.constellation.ouranos.sp3.service.AbstractSp3Service;
import fr.gnss.constellation.ouranos.sp3.service.AuthorizedNewSp3Download;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Sp3Service extends AbstractSp3Service {

  // -------------------- Services --------------------

  private final ISp3FileDao sp3FileRepository;
  private final ISp3InputStreamDao sp3InputStreamRepository;
  private final AuthorizedNewSp3Download authorizedNewSp3Download;

  // ------------------------ Constructeur(s) ------------------------

  public Sp3Service(ISp3FileDao sp3FileRepository, ISp3InputStreamDao sp3InputStreamRepository
      , AuthorizedNewSp3Download authorizedNewSp3Download) {
    super(sp3FileRepository);
    this.sp3FileRepository = sp3FileRepository;
    this.sp3InputStreamRepository = sp3InputStreamRepository;
    this.authorizedNewSp3Download = authorizedNewSp3Download;
  }


  // -------------------- Methodes de l'interface --------------------

  @Override
  public void downloadsAndStoresIfNotExist(List<Sp3FileName> sp3FileNames) {

    if (!authorizedNewSp3Download.isAuthorized()) {
      log.warn("Download new SP3 files not authorized");
      return;
    }

    log.info("Consecutive download Sp3 files");
    Optional.ofNullable(sp3FileNames).stream().flatMap(Collection::stream)
        .forEach(sp3FileName -> {
          if (!isSp3FileExisting(sp3FileName)) {
            sp3FileRepository.saveSp3File(sp3FileName, sp3InputStreamRepository.downloadSp3File(sp3FileName, true));
          }
        });
  }

}
