public class Computer {
    // Fields
    CPU cpu;
    
    // Constructor
    public Computer(CPU cpu) {
        this.cpu = cpu;
    }

    // Getters and Setters
    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    // toString
    @Override
    public String toString() {
        return "Computer [cpu=" + cpu.toString() + "]";
    }

}
