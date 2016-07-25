package jfsl.nytimessearch;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    String sortOrder;
    String art;
    String fashion;
    String sport;
    EditText beginDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Spinner dropdown = (Spinner) findViewById(R.id.spinner1);
        String[] items = new String[]{"Oldest", "Newest"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

    }


    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.arts:
                if (checked)
                    art = "Arts";// get art value
                else
                    art = null; // Remove value
                break;
            case R.id.fashion:
                if (checked)
                    fashion="Fashion & Style" ;  //get fashion value
                else
                    fashion= null; // remove fashion value
                break;
            case R.id.sports:
                if (checked)
                    sport = "Sports" ;//get sport value;
                else
                    sport = null;  // remove sport value
                break;
        }
    }


    public void submit(View view){

        String olderOrnewest = sortOrder;

        Intent i  = new Intent();
        i.putExtra("beginDate", beginDate.getText().toString());
        i.putExtra("art", art);
        i.putExtra("fashion", fashion);
        i.putExtra("sport", sport);
        i.putExtra("sortOrder",sortOrder );

        setResult(RESULT_OK,i);
        finish();
        overridePendingTransition(0,0);
    }



        // attach to an onclick handler to show the date picker
        public void showDatePickerDialog(View v) {
            DatePickerFragment newFragment = new DatePickerFragment();
            newFragment.show(getSupportFragmentManager(), "datePicker");
        }

        // handle the date selected
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // store the values selected into a Calendar instance
            final Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, monthOfYear);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            EditText beginDate = (EditText) findViewById(R.id.beginDate);
            beginDate.setText(sdf.format(c.getTime()));



        }




}
