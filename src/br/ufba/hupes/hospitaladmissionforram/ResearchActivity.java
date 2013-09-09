package br.ufba.hupes.hospitaladmissionforram;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by denis on 09/09/13.
 */
public class ResearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.form_research);

        TabHost tabs = (TabHost) this.findViewById(R.id.tabHost);
        tabs.setup();

        TabHost.TabSpec tspec1 = tabs.newTabSpec("Tirst Tab");
        tspec1.setIndicator("One");
        tspec1.setContent(R.id.tab1);
        tabs.addTab(tspec1);

        TabHost.TabSpec tspec2 = tabs.newTabSpec("Second Tab");
        tspec2.setIndicator("Two");
        tspec2.setContent(R.id.tab2);
        tabs.addTab(tspec2);

        TabHost.TabSpec tspec3 = tabs.newTabSpec("Third Tab");
        tspec3.setIndicator("Three");
        tspec3.setContent(R.id.tab3);
        tabs.addTab(tspec3);
    }
}
