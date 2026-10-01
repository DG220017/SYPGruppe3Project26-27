package DAO;

public enum CostType {
    RECURRING_REVENUE(1, "RECURRING_REVENUE"),
    RECURRING_EXPENSES(2, "RECURRING_EXPENSES"),
    REVENUE(3,"REVENUE"),
    EXPENSES(4,"EXPENSES");

    private final int id;
    private final String type;

    CostType(int id, String type){
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
