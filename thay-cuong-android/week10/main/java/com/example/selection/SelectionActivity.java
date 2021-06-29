package com.example.selection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.selection.data_models.AbstractQuestion;
import com.example.selection.data_models.MatchingQuestion;
import com.example.selection.data_models.MultiQuestionMultiChoices;
import com.example.selection.data_models.MultiQuestionOneChoice;
import com.example.selection.data_models.Questions;
import com.example.selection.data_models.TrueFalseQuestion;
import com.example.selection.fragments.AbstractFragment;
import com.example.selection.fragments.MatchingQuestionFragment;
import com.example.selection.fragments.MultiQuestionMultiChoiceFragment;
import com.example.selection.fragments.MultiQuestionOneChoiceFragment;
import com.example.selection.fragments.TrueFalseQuestionSwitchFragment;
import com.example.selection.fragments.TrueFalseQuestionToggleFragment;

public class SelectionActivity extends AppCompatActivity {
    private int questionID = 0;
    private AbstractFragment fragment;
    private FragmentTransaction fragmentTransaction;
    private final String SAVE_QUESTIONID="Key";
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_layout);
        //Fill the layout to the Framelayout
        FrameLayout mainFrame=(FrameLayout) findViewById(R.id.mainContainer);
        getLayoutInflater().inflate(R.layout.selection_layout,mainFrame,true);
        //Init
        if(savedInstanceState == null){
            Questions.init();
        }else{
            questionID=savedInstanceState.getInt(SAVE_QUESTIONID);
        }

        //
        Button btnNext=(Button) findViewById(R.id.btnNext);
        Button btnPre=(Button) findViewById(R.id.btnPre);
        Button btnFi=(Button) findViewById(R.id.btnFi);

        //
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.setQuestionAnswer(questionID);
                if(questionID==Questions.questions.size()-1){
                    questionID=0;
                }else{
                    questionID++;
                }
                updateUI();
            }
        });
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.setQuestionAnswer(questionID);
                if(questionID==0){
                    questionID=Questions.questions.size()-1;
                }else{
                    questionID--;
                }
                updateUI();
            }
        });
        btnFi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SelectionActivity.this, ResultActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                fragment.setQuestionAnswer(questionID);
                startActivity(intent);
            }
        });
        //update the UI at the first time
        updateUI();
        //for slide menu
        //config for the drawer toggle button in the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //get drawer layout
        drawerLayout=findViewById(R.id.drawerLayout);
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.drawerToggleOpen,R.string.drawerToggleClose);
        drawerLayout.addDrawerListener(drawerToggle);
        //show the question in the slide menu
        ListView listOfQuestions=findViewById(R.id.questionList);
        ArrayAdapter<AbstractQuestion> adapter=new ArrayAdapter<AbstractQuestion>(this, android.R.layout.simple_list_item_1,Questions.questions);
        listOfQuestions.setAdapter(adapter);
        //event processing
        listOfQuestions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //SAVE THE QUESTION ANSWER OF THE USER
                fragment.setQuestionAnswer(questionID);
                questionID=position;
                drawerLayout.closeDrawer(GravityCompat.START);
                updateUI();
            }
        });
    }
    //option menu

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //Update UI for each fragment
    private void updateUI(){
        //update the title of the screen
        setTitle("Cau hoi so: "+ (questionID+1));
        AbstractFragment reuseFragment =(AbstractFragment) getSupportFragmentManager().findFragmentByTag(questionID+"");
        if(Questions.questions.get(questionID) instanceof MultiQuestionMultiChoices){
            if(reuseFragment != null){
                fragment=(MultiQuestionMultiChoiceFragment) reuseFragment;
            }else{
                fragment=new MultiQuestionMultiChoiceFragment();
            }

        }else if(Questions.questions.get(questionID) instanceof MultiQuestionOneChoice){
            if(reuseFragment != null){
                fragment=(MultiQuestionOneChoiceFragment) reuseFragment;
            }else{
                fragment=new MultiQuestionOneChoiceFragment();
            }
        }else if(Questions.questions.get(questionID) instanceof MatchingQuestion){
            if(reuseFragment != null){
                fragment=(MatchingQuestionFragment) reuseFragment;
            }else{
                fragment=new MatchingQuestionFragment();
            }
        }
        else if(Questions.questions.get(questionID) instanceof TrueFalseQuestion){
            if(questionID % 2 == 1){
                if(reuseFragment != null){
                    fragment=(TrueFalseQuestionToggleFragment) reuseFragment;
                }else{
                    fragment=new TrueFalseQuestionToggleFragment();
                }
            }
            else{
                if(reuseFragment != null){
                    fragment=(TrueFalseQuestionSwitchFragment) reuseFragment;
                }else{
                    fragment=new TrueFalseQuestionSwitchFragment();
                }
            }
        }
        fragment.setQuestion(Questions.questions.get(questionID));
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,fragment,questionID+"");
        //check if fragment is exsit in the stack or not
        if(getSupportFragmentManager().findFragmentByTag(questionID+"") == null){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
    private void processUserActivities(CheckBox chkA,CheckBox chkB,CheckBox chkC,CheckBox chkD){
        Questions.questions.get(0).getQuestionAnswers().clear();
        if(chkA.isChecked()){
            Questions.questions.get(0).setQuestionAnswers(0);
        }
        if(chkB.isChecked()){
            Questions.questions.get(0).setQuestionAnswers(1);
        }
        if(chkC.isChecked()){
            Questions.questions.get(0).setQuestionAnswers(2);
        }
        if(chkD.isChecked()){
            Questions.questions.get(0).setQuestionAnswers(3);
        }
        Log.e("test sl",Questions.questions.get(0).getQuestionAnswers().toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("test","onStart of 1");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //syn drawer toggle button
        drawerToggle.syncState();
        Log.d("test","onResume of 1");
    }

    @Override
    protected void onPause() {
        Log.d("test","onPause of 1");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("test","onStop of 1");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("test","onDestroy of 1");
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //save the state of que id
        outState.putInt(SAVE_QUESTIONID,questionID);
    }
}