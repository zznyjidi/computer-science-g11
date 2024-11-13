public class CPU {
    // Fields
    String brand;
    String model;
    int coreCount;
    int threadCount;
    double baseFrequencyGhz;
    double boostFrequencyGhz;
    double cacheL1;
    double cacheL2;
    double cacheL3;

    // Constructors
    // No Spec
    public CPU(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    // With Core
    public CPU(String brand, String model, int coreCount, int threadCount) {
        this.brand = brand;
        this.model = model;
        setCoreCount(coreCount);
        setThreadCount(threadCount);
    }

    // With Frequency
    public CPU(String brand, String model, int coreCount, int threadCount,
            double baseFrequencyGhz, double boostFrequencyGhz) {
        this.brand = brand;
        this.model = model;
        setCoreCount(coreCount);
        setThreadCount(threadCount);
        setBaseFrequencyGhz(baseFrequencyGhz);
        setBoostFrequencyGhz(boostFrequencyGhz);
    }

    // With Cache
    public CPU(String brand, String model, int coreCount, int threadCount,
            double baseFrequencyGhz, double boostFrequencyGhz,
            double cacheL1, double cacheL2, double cacheL3) {
        this.brand = brand;
        this.model = model;
        setCoreCount(coreCount);
        setThreadCount(threadCount);
        setBaseFrequencyGhz(baseFrequencyGhz);
        setBoostFrequencyGhz(boostFrequencyGhz);
        setCacheL1(cacheL1);
        setCacheL2(cacheL2);
        setCacheL3(cacheL3);
    }

    // Getters and Setters
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(int coreCount) {
        if (coreCount > 0)
            this.coreCount = coreCount;
        else
            System.err.println("Core Count can't be less than 1. ");
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        if (threadCount > 0)
            this.threadCount = threadCount;
        else
            System.err.println("Thread Count can't be less than 1. ");
    }

    public double getBaseFrequencyGhz() {
        return baseFrequencyGhz;
    }

    public void setBaseFrequencyGhz(double baseFrequencyGhz) {
        if (baseFrequencyGhz > 0)
            this.baseFrequencyGhz = baseFrequencyGhz;
        else
            System.err.println("Frequency must be more than 0. ");
    }

    public double getBoostFrequencyGhz() {
        return boostFrequencyGhz;
    }

    public void setBoostFrequencyGhz(double boostFrequencyGhz) {
        if (boostFrequencyGhz > 0)
            this.boostFrequencyGhz = boostFrequencyGhz;
        else
            System.err.println("Frequency must be more than 0. ");
    }

    public double getCacheL1() {
        return cacheL1;
    }

    public void setCacheL1(double cacheL1) {
        if (cacheL1 >= 0)
            this.cacheL1 = cacheL1;
        else
            System.err.println("Cache can't be less than 0. ");
    }

    public double getCacheL2() {
        return cacheL2;
    }

    public void setCacheL2(double cacheL2) {
        if (cacheL2 >= 0)
            this.cacheL2 = cacheL2;
        else
            System.err.println("Cache can't be less than 0. ");
    }

    public double getCacheL3() {
        return cacheL3;
    }

    public void setCacheL3(double cacheL3) {
        if (cacheL3 >= 0)
            this.cacheL3 = cacheL3;
        else
            System.err.println("Cache can't be less than 0. ");
    }

    // toString
    @Override
    public String toString() {
        return "CPU [brand=" + brand + ", model=" + model + ", coreCount=" + coreCount + ", threadCount=" + threadCount
                + ", baseFrequencyGhz=" + baseFrequencyGhz + ", boostFrequencyGhz=" + boostFrequencyGhz + ", cacheL1="
                + cacheL1 + ", cacheL2=" + cacheL2 + ", cacheL3=" + cacheL3 + "]";
    }

}
