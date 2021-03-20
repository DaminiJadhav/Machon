package com.machon.machon.activity.issues.vehicleIssues;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.machon.machon.R;
import com.machon.machon.activity.issues.VehicleIssueContractor;
import com.machon.machon.adapter.SelectYourProblemCheckBoxAdapter;
import com.machon.machon.model.LocationData;
import com.machon.machon.model.SelectVehicleProblemData;
import com.machon.machon.model.response.GetVehicleProblemResponse;
import com.machon.machon.model.response.GetVehicleTypeResponse;
import com.machon.machon.model.response.PostGarageIssueResponse;
import com.machon.machon.utility.AppSession;
import com.machon.machon.utility.Constants;
import com.machon.machon.utility.OnRecyclerViewClick;
import com.machon.machon.utility.Utility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivitySelectYourProblemCheckBox extends AppCompatActivity implements OnRecyclerViewClick, VehicleProblemListContractor.View {

    ArrayList<String>  selectedList=new ArrayList<>();
    ArrayList<String>  problemIdList=new ArrayList<>();

    AppSession appSession;
    private OnRecyclerViewClick onRecyclerViewClick = this;
    VehicleProblemListPresenter problemListPresenter;
    String problemId;
    boolean isOtherSelected=false;

    List<GetVehicleProblemResponse.VehicleType> problemListTypes=new ArrayList<>();
    private ArrayList<SelectVehicleProblemData> selectVehicleProblemData = new ArrayList<>();


    SelectYourProblemCheckBoxAdapter selectYourProblemCheckBoxAdapter;
//    OnItemSendClickListener

    @BindView(R.id.select_problem_recycle_view)
    RecyclerView select_problem_recycle_view;


    @BindView(R.id.edittxt_your_problem)
    EditText your_problem;

    @BindView(R.id.btn_select)
    Button btnselect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_your_problem_check_box);
        ButterKnife.bind(this);
        appSession = AppSession.getInstance(this);
        problemListPresenter=new VehicleProblemListPresenter(this);
        setRecyclerView();
        getvehicleProblem();
    }

    private void setRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ActivitySelectYourProblemCheckBox.this);
        select_problem_recycle_view.setLayoutManager(linearLayoutManager);

    }

    void getvehicleProblem(){
        Utility.getInstance().showProgressDialogue(this);
        problemListPresenter.getVehicleProblemList();
    }


    @OnClick(R.id.btn_select)
    void selected_issues_btn(){
        Intent intent=new Intent();


        if(isOtherSelected==true){
            if(your_problem.getText().toString().isEmpty()){
                Toast.makeText(this, ""+getResources().getString(R.string.please_enter_your_problem), Toast.LENGTH_SHORT).show();
            }else {
                intent.putStringArrayListExtra(Constants.ISSUE_SELECTED_LIST,selectedList);
                intent.putStringArrayListExtra(Constants.ISSUE_ID,problemIdList);
                intent.putExtra(Constants.OTHER_ISSUE_TEXT,your_problem.getText().toString());

                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        }else {
            intent.putStringArrayListExtra(Constants.ISSUE_SELECTED_LIST,selectedList);
            intent.putStringArrayListExtra(Constants.ISSUE_ID,problemIdList);

            setResult(Activity.RESULT_OK,intent);
            finish();

        }


//        Intent intent1=new Intent();
//        intent1.putStringArrayListExtra(Constants.ISSUE_ID,problemIdList);
//        setResult(Activity.RESULT_OK,intent1);

        //         Bundle extra=new Bundle();
//         extra.putStringArrayList(Constants.ISSUE_SELECTED_LIST,selectedList);
//         extra.putStringArrayList(Constants.ISSUE_ID,problemIdList);
//         intent.putExtras(extra);
//         intent.putStringArrayListExtra(Constants.ISSUE_ID,selectedList);
    }

    @Override
    public void onReclerViewClick(View view, int position) {

            if(problemListTypes.get(position).isSelectProblem()){
                selectedList.add(problemListTypes.get(position).getPeoblem());
                problemIdList.add(problemListTypes.get(position).getProblemId());
                if(problemListTypes.get(position).getPeoblem().equalsIgnoreCase("Others")){
                    isOtherSelected=true;
                    your_problem.setVisibility(View.VISIBLE);

                }
            }else{
                selectedList.remove(problemListTypes.get(position).getPeoblem());
                problemIdList.remove(problemListTypes.get(position).getProblemId());
                isOtherSelected=false;
                your_problem.setVisibility(View.GONE);

            }

    }


    @Override
    public void vehicleProblemListSuccess(GetVehicleProblemResponse response) {
        Utility.getInstance().dismissProgress();

//        Toast.makeText(this, ""+response.getMessage(), Toast.LENGTH_SHORT).show();
        problemListTypes=response.getVehicleType();

        selectYourProblemCheckBoxAdapter=new SelectYourProblemCheckBoxAdapter(ActivitySelectYourProblemCheckBox.this,problemListTypes,onRecyclerViewClick);
        select_problem_recycle_view.setAdapter(selectYourProblemCheckBoxAdapter);
        selectYourProblemCheckBoxAdapter.notifyDataSetChanged();

    }

    @Override
    public void vehicleProblemListFailure(String message) {
        Utility.getInstance().dismissProgress();

        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();

    }
}