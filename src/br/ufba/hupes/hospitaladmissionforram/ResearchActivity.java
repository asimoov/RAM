package br.ufba.hupes.hospitaladmissionforram;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TabHost;

/**
 * Created by denis on 09/09/13.
 */
public class ResearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.edit();
    }

    private void edit() {
        setContentView(R.layout.form_research);
        this.createTab();
    }

    private void createTab() {
        TabHost tabs = (TabHost) this.findViewById(R.id.tabHost);
        tabs.setup();

        TabHost.TabSpec tspec1 = tabs.newTabSpec("First Tab");
        tspec1.setIndicator("Anamnese");
        tspec1.setContent(R.id.tab1);
        tabs.addTab(tspec1);

        TabHost.TabSpec tspec2 = tabs.newTabSpec("Second Tab");
        tspec2.setIndicator("Suspeitos");
        tspec2.setContent(R.id.tab2);
        tabs.addTab(tspec2);

        TabHost.TabSpec tspec3 = tabs.newTabSpec("Third Tab");
        tspec3.setIndicator("Causas");
        tspec3.setContent(R.id.tab3);
        tabs.addTab(tspec3);

        TabHost.TabSpec tspec4 = tabs.newTabSpec("Fourth Tab");
        tspec4.setIndicator("Outros");
        tspec4.setContent(R.id.tab4);
        tabs.addTab(tspec4);

        TabHost.TabSpec tspec5 = tabs.newTabSpec("Fifth Tab");
        tspec5.setIndicator("Extras");
        tspec5.setContent(R.id.tab5);
        tabs.addTab(tspec5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save_research, menu);

        return true;
    }
}
