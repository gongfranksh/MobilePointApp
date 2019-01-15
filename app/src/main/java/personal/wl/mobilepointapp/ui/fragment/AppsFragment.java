package personal.wl.mobilepointapp.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.update.BmobUpdateAgent;
import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.baserecycler.GridSectionAverageGapItemDecoration;
import personal.wl.mobilepointapp.common.MobilePointApplication;
import personal.wl.mobilepointapp.entity.MySection;
import personal.wl.mobilepointapp.entity.Video;
import personal.wl.mobilepointapp.entity.menufunction.MPAFunction;
import personal.wl.mobilepointapp.entity.menufunction.MPASection;
import personal.wl.mobilepointapp.ui.adapter.MPAFunctionAdapter;
import personal.wl.mobilepointapp.ui.adapter.SectionAdapter;
import personal.wl.mobilepointapp.ui.base.BaseFragment;
import personal.wl.mobilepointapp.utils.DataClearUtil;
import personal.wl.mobilepointapp.utils.ToastUtil;
import personal.wl.mobilepointapp.webservice.CallWebservices;
import personal.wl.mobilepointapp.webservice.WebServiceInterface;

import static android.widget.Toast.LENGTH_LONG;
import static personal.wl.mobilepointapp.common.AppConstant.Method_GET_FUNCTION_MENU_ALL;
import static personal.wl.mobilepointapp.common.AppConstant.PARA_GET_FUNCTION_MENU_ALL;

/**
 * Created by asus on 2016/8/27.
 */
public class AppsFragment extends BaseFragment implements  WebServiceInterface,View.OnClickListener {
    private RecyclerView mRecyclerView;
    private List<MPASection> mData;

    private MPAFunctionAdapter sectionAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apps, null);
        view.setOnClickListener(this);
        mRecyclerView = view.findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        mRecyclerView.addItemDecoration(new GridSectionAverageGapItemDecoration(10, 10, 10, 10));
        mRecyclerView.setOnClickListener(this);
        getfunctions();
        return view;
    }

    public void getfunctions() {

        CallWebservices callWebservices = new CallWebservices(this, Method_GET_FUNCTION_MENU_ALL, PARA_GET_FUNCTION_MENU_ALL, "01002");
        callWebservices.execute();
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
//        sectionAdapter.notifyDataSetChanged();
        init_menu();

    }


    public void init_menu(){
        sectionAdapter = new MPAFunctionAdapter(R.layout.fragment_item_section_content, R.layout.def_section_head, mData);
        sectionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
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

        sectionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getActivity(),"onItemChildClick" + position, LENGTH_LONG).show();
            }
        });
        mRecyclerView.setAdapter(sectionAdapter);

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(),"hello",LENGTH_LONG).show();
    }
}
