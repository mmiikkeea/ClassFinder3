package com.example.mikechen.ma4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;

/**
 * Created by groge on 10/22/2015.
 */
public class Find_Class extends Activity implements OnClickListener {

    Button mMap;
    Button mInformation;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_class);

        mMap = (Button)findViewById(R.id.Map);
        mMap.setOnClickListener(this);

        mInformation = (Button)findViewById(R.id.Information);
        mInformation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Map:
                Intent i = new Intent(getBaseContext(), Maps.class);
                startActivity(i);
                break;
            case R.id.Information:
                break;
        }
    }
}
