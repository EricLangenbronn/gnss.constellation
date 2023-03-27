package fr.gnss.constellation.ouranos.domain.constellation;

public enum ConstellationType {
  GPS('G'), GLONASS('R'), GALILEO('E'), BeiDou('C'), QZSS('J');

  private char prefixVehicule;

  ConstellationType(char prefixVehicule) {
    this.prefixVehicule = prefixVehicule;
  }

  public char getPrefixVehicule() {
    return prefixVehicule;
  }
}
