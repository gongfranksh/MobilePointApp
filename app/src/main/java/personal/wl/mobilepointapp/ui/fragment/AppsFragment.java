package personal.wl.mobilepointapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.auth.ldap.User;
import personal.wl.mobilepointapp.baserecycler.GridSectionAverageGapItemDecoration;
import personal.wl.mobilepointapp.entity.menufunction.MPAFunction;
import personal.wl.mobilepointapp.entity.menufunction.MPASection;
import personal.wl.mobilepointapp.ui.adapter.MPAFunctionAdapter;
import personal.wl.mobilepointapp.ui.base.BaseFragment;
import personal.wl.mobilepointapp.utils.ToastUtil;
import personal.wl.mobilepointapp.webservice.CallWebservices;
import personal.wl.mobilepointapp.webservice.WebServiceInterface;

import static android.widget.Toast.LENGTH_LONG;
import static personal.wl.mobilepointapp.common.AppConstant.Method_GET_FUNCTION_MENU_ALL;
import static personal.wl.mobilepointapp.common.AppConstant.PARA_GET_FUNCTION_MENU_ALL;
import static personal.wl.mobilepointapp.common.MobilePointApplication.loginInfo;
import static personal.wl.mobilepointapp.utils.MPAResourceUtils.getMPAclass;

/**
 * Created by asus on 2016/8/27.
 */
public class AppsFragment extends BaseFragment implements WebServiceInterface {
    private RecyclerView mRecyclerView;
    private List<MPASection> mData;

    private MPAFunctionAdapter sectionAdapter;
    private TextView tv1;

    private personal.wl.mobilepointapp.auth.ldap.User user;


//    @Override
//    public void onClick(View v) {
//        v.getId();
//        Toast.makeText(getActivity(), "hhh", Toast.LENGTH_LONG).show();
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apps, null);
        tv1 = view.findViewById(R.id.fragment_apps_title);
        mRecyclerView = view.findViewById(R.id.rv_list);

        user = loginInfo.getUser();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        mRecyclerView.addItemDecoration(new GridSectionAverageGapItemDecoration(10, 10, 10, 10));

//        mRecyclerView.setOnClickListener(this);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "hhh", Toast.LENGTH_LONG).show();
            }
        });
        getfunctions();
        return view;
    }

    public void getfunctions() {

        CallWebservices callWebservices = new CallWebservices(this, Method_GET_FUNCTION_MENU_ALL, PARA_GET_FUNCTION_MENU_ALL, "01002");
        callWebservices.execute();
    }


    public void init_menu() {
        sectionAdapter = new MPAFunctionAdapter(R.layout.fragment_item_section_content, R.layout.def_section_head, mData);
        sectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MPASection myselect = mData.get(position);
                if (myselect.isHeader) {
                    Toast.makeText(getActivity(), "----" + myselect.header, LENGTH_LONG).show();
                } else {
                    if (myselect.t.getMethodName().isEmpty()) {
                        Toast.makeText(getActivity(), myselect.t.getFunctionName() + "->未上线..", LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), myselect.t.getFunctionName() + "->开始", LENGTH_LONG).show();
                        //装载功能
                        Class callclass = getMPAclass(myselect.t.getMethodName());
                        if (callclass != null)
                            if (user != null) {
                                openActivity(callclass);
                            } else {
                                ToastUtil.show(getActivity(), R.string.me_nologin_not_login);
                            }
                    }


                }


            }
        });

        sectionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener()

        {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MPASection myselect = mData.get(position);
                if (myselect.isHeader) {
                    Toast.makeText(getActivity(), myselect.header, LENGTH_LONG).show();

                } else {


                    Toast.makeText(getActivity(), myselect.t.getFunctionName(), LENGTH_LONG).show();
                }
            }
        });

        sectionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener()

        {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getActivity(), "onItemChildClick" + position, LENGTH_LONG).show();
            }
        });
        mRecyclerView.setAdapter(sectionAdapter);

    }


    @Override
    public void onRecevicedResult(JSONArray jsonArray) {
        Log.i("apps", "onRecevicedResult: " + jsonArray.toString());
        List<MPASection> list = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject rec = jsonArray.getJSONObject(i);
                list.add(new MPASection(true, rec.get("sectionname").toString(), false));
                JSONArray funs = new JSONArray();
                funs = (JSONArray) rec.get("functions");
                for (int j = 0; j < funs.length(); j++) {
                    JSONObject currline = funs.getJSONObject(j);
                    MPAFunction mpaf = new MPAFunction();
                    mpaf.setFunctionName(currline.get("functionname").toString());
                    mpaf.setFunctionId(currline.get("functionid").toString());
                    mpaf.setIconName(currline.get("iconname").toString());
                    mpaf.setMethodName(currline.get("methodname").toString());
                    list.add(new MPASection(mpaf));
                }
            }
        } catch (Exception e) {

        }
        mData = list;
        init_menu();
    }
}
