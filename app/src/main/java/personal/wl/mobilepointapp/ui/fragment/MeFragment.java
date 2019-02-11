package personal.wl.mobilepointapp.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import com.uuzuche.lib_zxing.activity.CaptureActivity;

import java.io.Serializable;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.auth.ldap.LdapSearchAsyn;
import personal.wl.mobilepointapp.auth.ldap.LdapSearchListener;
import personal.wl.mobilepointapp.common.AppConstant;
import personal.wl.mobilepointapp.entity.pos.Branch;
import personal.wl.mobilepointapp.model.User;
import personal.wl.mobilepointapp.preference.CurrentUser.MPALoginInfo;
import personal.wl.mobilepointapp.ui.activity.CartActivity;
import personal.wl.mobilepointapp.ui.activity.CollectActivity;
import personal.wl.mobilepointapp.ui.activity.CouponsActivity;
import personal.wl.mobilepointapp.ui.activity.HistoryActivity;
import personal.wl.mobilepointapp.ui.activity.LoginActivity;
import personal.wl.mobilepointapp.ui.activity.LotteryActivity;
import personal.wl.mobilepointapp.ui.activity.PaidActivity;
import personal.wl.mobilepointapp.ui.activity.SalesOrder.BranchSelectActivity;
import personal.wl.mobilepointapp.ui.activity.TreasureActivity;
import personal.wl.mobilepointapp.ui.activity.UnpaidActivity;
import personal.wl.mobilepointapp.ui.activity.UserProfileActivity;
import personal.wl.mobilepointapp.ui.base.BaseFragment;
import personal.wl.mobilepointapp.utils.ToastUtil;

import static personal.wl.mobilepointapp.common.MobilePointApplication.loginInfo;

/**
 * Created by asus on 2016/8/27.
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {
    private static final int LOGIN_REQUEST_CODE = 100;
    private static final int PROFILE_REQUEST_CODE = 101;
    private static final int SCAN_QR_REQUEST = 3001;
    private SimpleDraweeView mLoginIvHead;
    private TextView mLoginTvUsername;
    private ImageView mLoginIvLevel;
    private TextView mLoginTvBalance;
    private ImageView mLoginIvArrowRight;
    private RelativeLayout mLoginLayout;
    private Button mNologinBtnLogin;
    private LinearLayout mNologinLayout;
    private TextView mUserTvCart;
    private TextView mUserTvFavorite;
    private TextView mUserTvHistory;
    private TextView mItemUnpaidTvCount;
    private RelativeLayout mItemUnpaidLayout;
    private TextView mItemPaidTvCount;
    private TextView mItemPaidTvUncommentCount;
    private RelativeLayout mItemPaidOrderLayout;
    private TextView mItemLotteryTvCount;
    private RelativeLayout mItemLotteryLayout;
    private TextView mItemTreasureTvCount;
    private RelativeLayout mItemTreasureLayout;
    private LinearLayout mUserOrderLayout;
    private TextView mItemBankcardTvComplete;
    private RelativeLayout mItemBankcardLayout;
    private TextView mItemCouponsTvCount;
    private RelativeLayout mItemCouponsLayout;
    private TextView mItemRecommendTvNew;
    private RelativeLayout mItemRecommendLayout;
    private RelativeLayout mItemQrLayout;
    private LinearLayout mLoginProfileLayout;
    private personal.wl.mobilepointapp.auth.ldap.User user;

    private TextView currentbranch;


    private ImageView iv_branch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, null);
        initView(view);
        initUserLayout();
        return view;
    }


    private void initUserLayout() {
//        User user = User.getCurrentUser(User.class);
//        User user = User.getCurrentUser(User.class);
        user = loginInfo.getUser();

        if (user != null) {
            mLoginLayout.setVisibility(View.VISIBLE);
            mNologinLayout.setVisibility(View.GONE);


            mLoginTvUsername.setText(user.getUsername());


            mLoginIvHead.setImageResource(R.mipmap.user_head);
//            if (user.getHeadIcon() == null) {
//            } else {
//                mLoginIvHead.setImageURI(Uri.parse(user.getHeadIcon().getFileUrl()));
//            }

            currentbranch.setText(MPALoginInfo.getInstance().getCurrentBranch().getBraname());
        } else {
            mLoginLayout.setVisibility(View.GONE);
            mNologinLayout.setVisibility(View.VISIBLE);


        }
    }

    private void initView(View view) {
        mLoginIvHead = (SimpleDraweeView) view.findViewById(R.id.me_login_iv_head);
        mLoginTvUsername = (TextView) view.findViewById(R.id.me_login_tv_username);
        mLoginIvLevel = (ImageView) view.findViewById(R.id.me_login_iv_level);
        mLoginTvBalance = (TextView) view.findViewById(R.id.me_login_tv_balance);
        mLoginIvArrowRight = (ImageView) view.findViewById(R.id.me_login_iv_arrow_right);
        mLoginLayout = (RelativeLayout) view.findViewById(R.id.me_login_layout);
        mNologinBtnLogin = (Button) view.findViewById(R.id.me_nologin_btn_login);
        mNologinLayout = (LinearLayout) view.findViewById(R.id.me_nologin_layout);
        mUserTvCart = (TextView) view.findViewById(R.id.me_user_tv_cart);
        mUserTvFavorite = (TextView) view.findViewById(R.id.me_user_tv_favorite);
        mUserTvHistory = (TextView) view.findViewById(R.id.me_user_tv_history);
        mItemUnpaidTvCount = (TextView) view.findViewById(R.id.me_item_unpaid_tv_count);
        mItemUnpaidLayout = (RelativeLayout) view.findViewById(R.id.me_item_unpaid_layout);
        mItemPaidTvCount = (TextView) view.findViewById(R.id.me_item_paid_tv_count);
        mItemPaidTvUncommentCount = (TextView) view.findViewById(R.id.me_item_paid_tv_uncomment_count);
        mItemPaidOrderLayout = (RelativeLayout) view.findViewById(R.id.me_item_paid_order_layout);

        mItemTreasureTvCount = (TextView) view.findViewById(R.id.me_item_treasure_tv_count);
        mItemTreasureLayout = (RelativeLayout) view.findViewById(R.id.me_item_treasure_layout);
        mUserOrderLayout = (LinearLayout) view.findViewById(R.id.me_user_order_layout);
        currentbranch = view.findViewById(R.id.me_item_current_branch);

        mItemRecommendTvNew = (TextView) view.findViewById(R.id.me_item_recommend_tv_new);
        mItemRecommendLayout = (RelativeLayout) view.findViewById(R.id.me_item_recommend_layout);
        mItemQrLayout = (RelativeLayout) view.findViewById(R.id.me_item_qr_layout);
        mLoginProfileLayout = (LinearLayout) view.findViewById(R.id.me_login_profile_layout);

        iv_branch = view.findViewById(R.id.me_item_branch_iv_arrow_right);

        mLoginIvHead.setOnClickListener(this);
        mLoginIvArrowRight.setOnClickListener(this);
        mNologinBtnLogin.setOnClickListener(this);
        mUserTvCart.setOnClickListener(this);
        mUserTvFavorite.setOnClickListener(this);
        mUserTvHistory.setOnClickListener(this);
        mItemUnpaidLayout.setOnClickListener(this);
        mItemPaidOrderLayout.setOnClickListener(this);


        iv_branch.setOnClickListener(this);
        mItemQrLayout.setOnClickListener(this);
        mLoginProfileLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_login_iv_head:

                break;
            case R.id.me_login_profile_layout:
                Intent profileIntent = new Intent(getActivity(), UserProfileActivity.class);
                profileIntent.putExtra("login", (Serializable) loginInfo.getUser());
                startActivityForResult(profileIntent, PROFILE_REQUEST_CODE);
//                openActivity(UserProfileActivity.class);
                break;
            case R.id.me_login_iv_arrow_right:

                break;
            case R.id.me_nologin_btn_login:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, LOGIN_REQUEST_CODE);
//                openActivity(LoginActivity.class);
                break;
            case R.id.me_user_tv_cart:
                if (user != null) {
                    openActivity(CartActivity.class);
                } else {
                    ToastUtil.show(getActivity(), R.string.me_nologin_not_login);
                }
                break;
            case R.id.me_user_tv_favorite:
                if (user != null) {
                    openActivity(CollectActivity.class);
                } else {
                    ToastUtil.show(getActivity(), R.string.me_nologin_not_login);
                }
                break;
            case R.id.me_user_tv_history:
                if (user != null) {
                    openActivity(HistoryActivity.class);
                } else {
                    ToastUtil.show(getActivity(), R.string.me_nologin_not_login);
                }
                break;
            case R.id.me_item_unpaid_layout:
                if (user != null) {
                    openActivity(UnpaidActivity.class);
                } else {
                    ToastUtil.show(getActivity(), R.string.me_nologin_not_login);
                }
                break;
            case R.id.me_item_paid_order_layout:
                if (user != null) {
                    openActivity(PaidActivity.class);
                } else {
                    ToastUtil.show(getActivity(), R.string.me_nologin_not_login);
                }
                break;

            case R.id.me_item_qr_layout:
                Intent qrIntent = new Intent(getActivity(), CaptureActivity.class);
                getActivity().startActivityForResult(qrIntent, SCAN_QR_REQUEST);
                break;

            case R.id.me_item_branch_iv_arrow_right:
                Intent branchIntent = new Intent(getActivity(), BranchSelectActivity.class);
                startActivityForResult(branchIntent, AppConstant.BRANCH_SELECT_CODE);
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_REQUEST_CODE && resultCode == LoginActivity.LOGIN_RESULT_CODE) {
//            Log.i("MeFragment", "onActivityResult: "+user.getDisplayName());
            initUserLayout();
        } else if (requestCode == PROFILE_REQUEST_CODE && resultCode == UserProfileActivity.PROFILE_RESULT_CODE) {
            initUserLayout();
        } else if (requestCode == SCAN_QR_REQUEST) {

        }
        else  if(requestCode==AppConstant.BRANCH_SELECT_CODE){
            Branch branch = (Branch) data.getSerializableExtra(AppConstant.BRANCH_SELECT_RESULT_EXTRA_CODE);

            if (branch!=null){
                currentbranch.setText(branch.getBraname());

            }

        }

    }


}
