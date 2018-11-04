package app.gramatic.gramaticapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.gramatic.gramaticapp.R;

public class MainActivity extends AppCompatActivity {

    private Button acti_list_voc;
    private Button acti_add_voc;
//    Button acti_list_voc = (Button) findViewById(R.id.button_list);
//    Button acti_add_gtic = (Button) findViewById(R.id.button_add_gtic);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acti_list_voc = (Button) findViewById(R.id.button_list);
        acti_add_voc = (Button) findViewById(R.id.button_add_voc);

        acti_list_voc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVocActList();
            }
        });

        acti_add_voc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVocActivity = new Intent(MainActivity.this, VocabularioActivityList.class);
                startActivity(intentVocActivity);
            }
        });

    }

    private void startVocActList(){
        Intent intent = new Intent(MainActivity.this, VocabularioActivityList.class);
        startActivity(intent);
    }

}
