package main;

public class BirthdayOdds {
    public int minPeople(int minOdds, int daysInYear) {
        int lo = 1;
        int hi = daysInYear;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (checkBirthdayProb(birthdayCollisionProbability(mid, daysInYear), minOdds)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        if (checkBirthdayProb(birthdayCollisionProbability(lo, daysInYear), minOdds)) {
            return lo;
        } else {
            return -1;
        }
    }

    private int birthdayCollisionProbability(int persons, int daysInYear) {
        double p = 1;
        for (int i = 0; i < persons; ++i) {
            p *= ((double) (daysInYear - i)) / daysInYear;
        }
        return (int) ((1 - p) * 100);
    }

    private boolean checkBirthdayProb(int birthdayProb, int target) {
        return birthdayProb >= target;
    }
}
