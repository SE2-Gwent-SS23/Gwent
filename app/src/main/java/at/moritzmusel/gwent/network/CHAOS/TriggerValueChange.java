package at.moritzmusel.gwent.network.CHAOS;

public class TriggerValueChange {
    private Object variable;
    private TriggerValueChangeListener listener;

    public void setValue(Object value) {
        variable = value;
        if (listener != null) {
            listener.onVariableUpdated(value);
        }
    }

    public Object getVariable() {
        return variable;
    }

    public void setListener(TriggerValueChangeListener listener) {
        this.listener = listener;
    }
}

