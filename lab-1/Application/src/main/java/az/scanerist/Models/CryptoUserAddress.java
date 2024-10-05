package az.scanerist.Models;

public class CryptoUserAddress {
    private String Street;
    private int BuildingNumber;

    public CryptoUserAddress(String street, int buildingNumber) {
        Street = street;
        BuildingNumber = buildingNumber;
    }

    public String getStreet() {
        return Street;
    }

    public int getBuildingNumber() {
        return BuildingNumber;
    }
}
