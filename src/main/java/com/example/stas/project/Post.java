package com.example.stas.project;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Post  extends Activity {

    // Declare Variables
    ListView list,list2;
    ListViewAdapter adapter,adapter2;
    private static final String REGISTER_URL = "http://sapironmyway.pe.hu/AddTremp.php"; //move to new class Register
    static EditText editsearch,editsearch2;
    ArrayList<Stop> arraylist = new ArrayList<Stop>();
    ArrayList<Stop> arraylist2 = new ArrayList<Stop>();
    String time,time2;
    int passengers, route;
    Button post;
    Button pickTime,pickTime2;
    CheckBox bash,sapir;
    String source,destination,facebook_id;
    private int pHour,pHour2;
    private int pMinute,pMinute2;
    /** This integer will uniquely define the dialog to be used for displaying time picker.*/
    static final int TIME_DIALOG_ID = 0;
    private static final int  TIME_DIALOG_ID1 = 1;

    /** Callback received when the user "picks" a time in the dialog */
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pHour = hourOfDay;
                    pMinute = minute;

                     time=pHour+":"+pMinute; //Time String
                    
                }
            };


    private TimePickerDialog.OnTimeSetListener mTimeSetListener2 =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pHour2 = hourOfDay;
                    pMinute2 = minute;

                    time2=pHour2+":"+pMinute2; //Time String


                }
            };

    public void onCreate(Bundle savedInstanceState) {
       this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); //adjustPan|adjustResize

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        // Generate sample data
        bash = (CheckBox) findViewById(R.id.checkBox2);
        sapir=(CheckBox) findViewById(R.id.checkBox3);

        sapir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (((CheckBox) v).isChecked())
                  //  System.out.println("CheckBox is checked");
                route=2;
                else

                System.out.println("CheckBox is unchecked");
            }
        });
        bash.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (((CheckBox) v).isChecked())
                    //  System.out.println("CheckBox is checked");
                    route=1;
                else

                    System.out.println("CheckBox is unchecked");
            }
        });

        SharedPreferences sharedPref = getSharedPreferences("mySettings", MODE_PRIVATE);

         facebook_id = sharedPref.getString("mySetting", null); //get data from local store



        NumberPicker np = (NumberPicker) findViewById(R.id.numberPicker);

        np.setMinValue(1);
        //Specify the maximum value/number of NumberPicker
        np.setMaxValue(10);

        //Gets whether the selector wheel wraps when reaching the min/max value.
        np.setWrapSelectorWheel(true);

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Display the newly selected number from picker

                passengers = newVal;        // Get passengers number


            }
        });

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.listview);
        list2 = (ListView) findViewById(R.id.listview2);
        // Read from the Csv file and Display in ListView :
        InputStream inputStream = getResources().openRawResource(R.raw.stops);
        CSVFile csvFile = new CSVFile(inputStream);
        List scoreList = csvFile.read();
        String [] a;
        final String [] stop=new String[scoreList.size()];
        final String [] stopInfo=new String[scoreList.size()];
        final String [] stopCode=new String[scoreList.size()];

        for(int i=0;i<scoreList.size();i++){
            a= (String[]) scoreList.get(i);
            stopCode[i]=a[1];
            stop[i]=a[2];
            stopInfo[i]=a[3];


        }

        for (int i = 0; i < scoreList.size(); i++)
        {
            Stop wp = new Stop(stopCode[i], stop[i],
                    stopInfo[i],"1");
            Stop wp2 = new Stop(stopCode[i], stop[i],
                    stopInfo[i],"2");

            // Binds all strings into an array
            arraylist.add(wp);
            arraylist2.add(wp2);
        }

        // Locate the EditText in listview_main.xml
        editsearch = (EditText) findViewById(R.id.search);
        editsearch2 = (EditText) findViewById(R.id.search2);



        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, arraylist);
        adapter2 = new ListViewAdapter(this, arraylist2);

        // Binds the Adapter to the ListView

        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
                list.setAdapter(adapter);

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });
        editsearch2.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch2.getText().toString().toLowerCase(Locale.getDefault());
                adapter2.filter(text);
                list2.setAdapter(adapter2);

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });

        /** Capture our View elements */

        pickTime = (Button) findViewById(R.id.pickTime);
        /** Listener for click event of the button */
        pickTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);

            }
        });

        pickTime2 = (Button) findViewById(R.id.button2);
        /** Listener for click event of the button */
        pickTime2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID1);



            }
        });

        // Post Button
        post = (Button) findViewById(R.id.button);

        View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String currentDateTimeString = sdf.format(c.getTime());


                //System.out.println(currentDateTimeString);
                register(facebook_id, editsearch.getText().toString(), editsearch2.getText().toString(), time, time2, currentDateTimeString,passengers ,route);
                Intent back = new Intent(Post.this, MainActivity.class);
                startActivity(back);

            }
        };

        post.setOnClickListener(oclBtnOk);
    }

    // Create Class for this Function :
    private void register(String facebook_id, String source,String destination, String time,String time2,String currentDateTimeString ,int passengers,int bash) {
        String urlSuffix = "?user_id=" + facebook_id + "&source=" + source + "&dest=" + destination + "&time_out=" + time + "&time_in=" + time2 +"&currentDateTimeString="+currentDateTimeString + "&no_p=" + passengers + "&maslulID=" + bash;

        System.out.println(passengers);

        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Post.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(REGISTER_URL + s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;
                    result = bufferedReader.readLine();

                    return result;
                } catch (Exception e) {
                    return null;
                }
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(urlSuffix);

    }

    /** Create a new dialog for time picker */
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                        mTimeSetListener, pHour, pMinute, false);


            case TIME_DIALOG_ID1:
                return new TimePickerDialog(this,
                        mTimeSetListener2, pHour2, pMinute2, false);
        }
        return null;
    }

}







