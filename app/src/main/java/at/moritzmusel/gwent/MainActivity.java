package at.moritzmusel.gwent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import at.moritzmusel.gwent.networking.Activities.debug_activity_network_create;
import at.moritzmusel.gwent.networking.Activities.debug_activity_network_join;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.create_btn).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, debug_activity_network_create.class));
        });
        findViewById(R.id.join_btn).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, debug_activity_network_join.class));
        });
    }
}