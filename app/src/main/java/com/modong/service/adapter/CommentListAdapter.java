package com.modong.service.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modong.service.R;
import com.modong.service.fragment.status.util.ClickableTextViewMentionLinkOnTouchListener;
import com.modong.service.fragment.status.util.TimeLineUtil;
import com.modong.service.model.WeiboComment;
import com.modong.service.model.WeiboUser;
import com.modong.service.ui.UserInfoActivity;
import com.modong.service.view.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 青松 on 2016/8/16.
 */
public class CommentListAdapter extends BaseQuickAdapter<WeiboComment> implements View.OnClickListener {

    private Context context;

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        ClickableTextViewMentionLinkOnTouchListener listener = new ClickableTextViewMentionLinkOnTouchListener();

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return listener.onTouch(view, motionEvent);
        }
    };

    public CommentListAdapter(Context context, int layoutResId, List<WeiboComment> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, WeiboComment weiboComment) {
        WeiboUser weiboUser = weiboComment.getUser();
        CircleImageView user_avatar_civ = helper.getView(R.id.user_avatar_civ);
        user_avatar_civ.setOnClickListener(this);
        user_avatar_civ.setTag(weiboUser);
        Picasso.with(mContext).load(weiboUser.getAvatar_large()).placeholder(R.drawable.header).into(user_avatar_civ);

        TextView user_name_tv = helper.getView(R.id.user_name_tv);
        user_name_tv.setOnClickListener(this);
        user_name_tv.setTag(weiboUser);
        user_name_tv.setText(weiboUser.getName());

        helper.setText(R.id.comment_time_tv, TimeLineUtil.getFormatTime(weiboComment.getCreated_at()));
        ImageView verified_iv = helper.getView(R.id.verified_iv);
        TimeLineUtil.setImageVerified(verified_iv, weiboUser);

        TextView status_content_tv = helper.getView(R.id.comment_content_tv);
        SpannableString spannableText = weiboComment.getSpannableText();
        if (spannableText != null) {
            status_content_tv.setText(weiboComment.getSpannableText());
        } else {
            TimeLineUtil.setSpannableText(status_content_tv, weiboComment);
        }
        status_content_tv.setOnTouchListener(touchListener);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_avatar_civ:
            case R.id.user_name_tv:
                WeiboUser weiboUser = (WeiboUser) view.getTag();
                UserInfoActivity.lunchUserInfoActivity(context, null, weiboUser);
                break;
        }
    }
}
