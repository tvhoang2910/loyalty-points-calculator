package vn.edu.uet;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoyaltyPointsCalculatorTest {

    private final LoyaltyPointsCalculator calculator = new LoyaltyPointsCalculator();

    // ===================================================================
    // 🔹 PHẦN 1: KIỂM THỬ BẢNG QUYẾT ĐỊNH (Decision Table)
    // ===================================================================

    /**
     * R1: totalSpent >= 5M, purchaseCount >= 5 → 100
     */
    @Test
    void testR1_Tier1() {
        assertEquals(100, calculator.calculatePoints(5_500_000, 5));
    }

    /**
     * R2: totalSpent >= 5M, purchaseCount [3, 5) → 70
     */
    @Test
    void testR2_Tier1Spent_MidCount() {
        assertEquals(70, calculator.calculatePoints(5_500_000, 4));
    }

    /**
     * R3: totalSpent >= 5M, purchaseCount [2, 3) → 40
     */
    @Test
    void testR3_Tier1Spent_LowCount() {
        assertEquals(40, calculator.calculatePoints(5_500_000, 2));
    }

    /**
     * R4: totalSpent >= 5M, purchaseCount < 2 → 0
     */
    @Test
    void testR4_Tier1Spent_VeryLowCount() {
        assertEquals(0, calculator.calculatePoints(5_500_000, 1));
    }

    /**
     * R5: totalSpent [3M, 5M), purchaseCount >= 5 → 70
     */
    @Test
    void testR5_Tier2Spent_HighCount() {
        assertEquals(70, calculator.calculatePoints(3_500_000, 5));
    }

    /**
     * R6: totalSpent [3M, 5M), purchaseCount [3, 5) → 70
     */
    @Test
    void testR6_Tier2Spent_MidCount() {
        assertEquals(70, calculator.calculatePoints(3_500_000, 3));
    }

    /**
     * R7: totalSpent [3M, 5M), purchaseCount [2, 3) → 40
     */
    @Test
    void testR7_Tier2Spent_LowCount() {
        assertEquals(40, calculator.calculatePoints(3_500_000, 2));
    }

    /**
     * R8: totalSpent [3M, 5M), purchaseCount < 2 → 0
     */
    @Test
    void testR8_Tier2Spent_VeryLowCount() {
        assertEquals(0, calculator.calculatePoints(3_500_000, 1));
    }

    /**
     * R9: totalSpent [1M, 3M), purchaseCount >= 5 → 40
     */
    @Test
    void testR9_Tier3Spent_HighCount() {
        assertEquals(40, calculator.calculatePoints(1_500_000, 5));
    }

    /**
     * R10: totalSpent [1M, 3M), purchaseCount [3, 5) → 40
     */
    @Test
    void testR10_Tier3Spent_MidCount() {
        assertEquals(40, calculator.calculatePoints(1_500_000, 3));
    }

    /**
     * R11: totalSpent [1M, 3M), purchaseCount [2, 3) → 40
     */
    @Test
    void testR11_Tier3Spent_LowCount() {
        assertEquals(40, calculator.calculatePoints(1_500_000, 2));
    }

    /**
     * R12: totalSpent < 1M, purchaseCount >= 3 → 0
     */
    @Test
    void testR12_LowSpent_AnyCount() {
        assertEquals(0, calculator.calculatePoints(800_000, 3));
    }

    // ===================================================================
    // 🔹 PHẦN 2: KIỂM THỬ BIÊN (Boundary Value Analysis)
    // ===================================================================

    // --- Biên totalSpent ---
    @Test
    void testBoundary_Spent_JustBelowTier3() {
        // 999,000 < 1M → 0 điểm dù đủ lần
        assertEquals(0, calculator.calculatePoints(999_000, 3));
    }

    @Test
    void testBoundary_Spent_AtTier3() {
        // 1M, đủ lần → 40
        assertEquals(40, calculator.calculatePoints(1_000_000, 3));
    }

    @Test
    void testBoundary_Spent_JustBelowTier2() {
        // 2,999,000 < 3M → Tier3 nếu đủ lần
        assertEquals(40, calculator.calculatePoints(2_999_000, 3));
    }

    @Test
    void testBoundary_Spent_AtTier2() {
        // 3M, đủ lần → 70
        assertEquals(70, calculator.calculatePoints(3_000_000, 3));
    }

    @Test
    void testBoundary_Spent_JustBelowTier1() {
        // 4,999,000 < 5M → Tier2 nếu đủ lần
        assertEquals(70, calculator.calculatePoints(4_999_000, 3));
    }

    @Test
    void testBoundary_Spent_AtTier1() {
        // 5M, đủ lần → 100
        assertEquals(100, calculator.calculatePoints(5_000_000, 5));
    }

    // --- Biên purchaseCount ---
    @Test
    void testBoundary_Count_BelowTier3() {
        // purchaseCount = 1 → 0 dù đủ tiền
        assertEquals(0, calculator.calculatePoints(3_500_000, 1));
    }

    @Test
    void testBoundary_Count_AtTier3() {
        // purchaseCount = 2 → Tier3 nếu đủ tiền
        assertEquals(40, calculator.calculatePoints(3_500_000, 2));
    }

    @Test
    void testBoundary_Count_BelowTier2() {
        // purchaseCount = 2 → KHÔNG đủ Tier2 → 40
        assertEquals(40, calculator.calculatePoints(3_500_000, 2));
    }

    @Test
    void testBoundary_Count_AtTier2() {
        // purchaseCount = 3 → Tier2 nếu đủ tiền
        assertEquals(70, calculator.calculatePoints(3_500_000, 3));
    }

    @Test
    void testBoundary_Count_BelowTier1() {
        // purchaseCount = 4 → KHÔNG đủ Tier1 → 70
        assertEquals(70, calculator.calculatePoints(5_500_000, 4));
    }

    @Test
    void testBoundary_Count_AtTier1() {
        // purchaseCount = 5 → Tier1 nếu đủ tiền
        assertEquals(100, calculator.calculatePoints(5_500_000, 5));
    }
}