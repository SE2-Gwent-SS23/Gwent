package at.moritzmusel.gwent.network.CHAOS;

import org.json.JSONException;

import java.io.IOException;

public interface TriggerValueChangeListener {
    void onVariableUpdated(Object value);
}
