package vn.edu.uet;

public class LoyaltyPointsCalculator {

    // Hằng số mô phỏng cấu hình hệ thống
    private static final double TIER1_SPENT_THRESHOLD = 5_000_000;
    private static final int TIER1_PURCHASE_THRESHOLD = 5;
    private static final double TIER2_SPENT_THRESHOLD = 3_000_000;
    private static final int TIER2_PURCHASE_THRESHOLD = 3;
    private static final double TIER3_SPENT_THRESHOLD = 1_000_000;
    private static final int TIER3_PURCHASE_THRESHOLD = 2;

    private static final int POINTS_TIER1 = 100;
    private static final int POINTS_TIER2 = 70;
    private static final int POINTS_TIER3 = 40;

    public int calculatePoints(double totalSpent, int purchaseCount) {
        if (meetsTier1(totalSpent, purchaseCount)) {
            return POINTS_TIER1;
        }

        if (meetsTier2(totalSpent, purchaseCount)) {
            return POINTS_TIER2;
        }

        if (meetsTier3(totalSpent, purchaseCount)) {
            return POINTS_TIER3;
        }

        return 0;
    }

    private boolean meetsTier1(double spent, int count) {
        return spent >= TIER1_SPENT_THRESHOLD && count >= TIER1_PURCHASE_THRESHOLD;
    }

    private boolean meetsTier2(double spent, int count) {
        return spent >= TIER2_SPENT_THRESHOLD && count >= TIER3_PURCHASE_THRESHOLD;
    }

    private boolean meetsTier3(double spent, int count) {
        return spent >= TIER3_SPENT_THRESHOLD && count >= TIER3_PURCHASE_THRESHOLD;
    }
}