package fr.gnss.constellation.ouranos.service.process.satelitevisible.dto;

import java.time.LocalDateTime;

import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisibleSateliteRequestDto {

	private LocalDateTime dateDebut;
	private LocalDateTime dateFin;
	private GeodeticCoordinate geodeticCoordinate;
	private double radElevationMask;

}
