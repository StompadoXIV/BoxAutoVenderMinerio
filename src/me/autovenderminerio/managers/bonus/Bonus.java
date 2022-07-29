package me.autovenderminerio.managers.bonus;

public class Bonus {

    private String permission;
    private String group;
    private double percentage;

    public Bonus(String permission, String group, double percentage) {
        this.permission = permission;
        this.group = group;
        this.percentage = percentage;
    }

    public String getPermission() {
        return permission;
    }

    public String getGroup() {
        return group;
    }

    public double getPercentage() {
        return percentage;
    }
}
