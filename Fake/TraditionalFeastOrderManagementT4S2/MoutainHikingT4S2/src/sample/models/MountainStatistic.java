/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.models;

/**
 *
 * @author ADMIN
 */
public class MountainStatistic {

    private String mountainCode;
    private int participantCount;
    private float totalCost;

    public MountainStatistic(String mountainCode, int participantCount, float totalCost) {
        this.mountainCode = mountainCode;
        this.participantCount = participantCount;
        this.totalCost = totalCost;
    }

    public String getMountainCode() {
        return mountainCode;
    }

    public int getParticipantCount() {
        return participantCount;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public String toStringByStatic() {
        return String.format("%-12s | %-20d   | %,-15.0f", "MT" + String.format("%02d", Integer.parseInt(mountainCode)), participantCount, totalCost);
    }
}
