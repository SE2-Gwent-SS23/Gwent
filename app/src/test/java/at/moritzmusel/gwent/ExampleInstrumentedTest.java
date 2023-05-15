package at.moritzmusel.gwent;

import static org.junit.jupiter.api.Assertions.assertEquals;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExampleInstrumentedTest {
    private Context instrumentationContext;

    @BeforeEach
    void setup(){
        instrumentationContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @Test
    public void useAppContext() {
        assertEquals("at.moritzmusel.gwent", instrumentationContext.getPackageName());
    }
}