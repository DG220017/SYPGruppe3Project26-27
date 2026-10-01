package DAO;

public enum TransactionType {
    GEHALT(1, "GEHALT"),
    WOHNEN(2,"WOHNEN"),
    ESSEN_TRINKEN(3,"ESSEN_TRINKEN"),
    MOBILITÄT(4,"MOBILITÄT"),
    FREIZEIT(5,""),
    HOBBY(6,"FREIZEIT"),
    SHOPPING(7,"SHOPPING"),
    BILLS(8,"BILLS"),
    NEBENEINKOMMEN(9,"NEBENEINKOMMEN");

    private final int id;
    private final String type;

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    TransactionType(int id, String type){
        this.id = id;
        this.type = type;
    }
    
}
