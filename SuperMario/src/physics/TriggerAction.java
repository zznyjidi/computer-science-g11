package physics;

public interface TriggerAction {
    // Interface for Lambda
    // https://stackoverflow.com/questions/13604703/how-do-i-define-a-method-which-takes-a-lambda-as-a-parameter-in-java-8
    public void action(int[] blockPos, PhysicsStatus status);
}
