public interface SalesBehavior {
    double getSalesBonus();
    double getExitBonus();
}

class NoSell implements SalesBehavior {
    @Override
    public double getSalesBonus() {
        return 0.0;
    }

    @Override
    public double getExitBonus() {
        return 0.0;
    }
}

class SoftSell implements SalesBehavior {
    @Override
    public double getSalesBonus() {
        return 0.05; // 5% sales bonus
    }

    @Override
    public double getExitBonus() {
        return 0.02; // 2% exit bonus
    }
}

class NormalSell implements SalesBehavior {
    @Override
    public double getSalesBonus() {
        return 0.10; // 10% sales bonus
    }

    @Override
    public double getExitBonus() {
        return 0.05; // 5% exit bonus
    }
}

class HardSell implements SalesBehavior {
    @Override
    public double getSalesBonus() {
        return 0.15; // 15% sales bonus
    }

    @Override
    public double getExitBonus() {
        return 0.10; // 10% exit bonus
    }
}
