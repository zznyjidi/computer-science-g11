package physics;

// Interface for Lambda
// https://stackoverflow.com/questions/13604703/how-do-i-define-a-method-which-takes-a-lambda-as-a-parameter-in-java-8
public interface TriggerAction {
    /**
     * Action to Run for Trigger
     * @param blockPos source block that triggered the action
     * @param status physics status when action is triggered
     */
    public void action(int[] blockPos, PhysicsStatus status);
}
