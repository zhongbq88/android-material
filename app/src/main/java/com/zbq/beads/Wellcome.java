package com.zbq.beads;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by a123 on 15-7-1.
 */
public class Wellcome extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wellcome);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Util.RunOnUIThread(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Wellcome.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }
}
