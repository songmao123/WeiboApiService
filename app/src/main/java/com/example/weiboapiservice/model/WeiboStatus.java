package com.example.weiboapiservice.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sqsong on 16-8-6.
 */
public class WeiboStatus implements Parcelable {

    private long id;
    private long mid;
    private String idstr;
    private String text;
    private String source;
    private boolean favorited;
    private boolean truncated;
    private String in_reply_to_status_id;
    private String in_reply_to_user_id;
    private String in_reply_to_screen_name;
    private String thumbnail_pic;
    private String bmiddle_pic;
    private String original_pic;
    private WeiboGeo geo;
    private WeiboUser user;
    private WeiboStatus retweeted_status;
    private int reposts_count;
    private int comments_count;
    private int attitudes_count;
    private int mlevel;
    private String created_at;

    protected WeiboStatus(Parcel in) {
        id = in.readLong();
        mid = in.readLong();
        idstr = in.readString();
        text = in.readString();
        source = in.readString();
        favorited = in.readByte() != 0;
        truncated = in.readByte() != 0;
        in_reply_to_status_id = in.readString();
        in_reply_to_user_id = in.readString();
        in_reply_to_screen_name = in.readString();
        thumbnail_pic = in.readString();
        bmiddle_pic = in.readString();
        original_pic = in.readString();
        geo = in.readParcelable(WeiboGeo.class.getClassLoader());
        user = in.readParcelable(WeiboUser.class.getClassLoader());
        retweeted_status = in.readParcelable(WeiboStatus.class.getClassLoader());
        reposts_count = in.readInt();
        comments_count = in.readInt();
        attitudes_count = in.readInt();
        mlevel = in.readInt();
        created_at = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(mid);
        dest.writeString(idstr);
        dest.writeString(text);
        dest.writeString(source);
        dest.writeByte((byte) (favorited ? 1 : 0));
        dest.writeByte((byte) (truncated ? 1 : 0));
        dest.writeString(in_reply_to_status_id);
        dest.writeString(in_reply_to_user_id);
        dest.writeString(in_reply_to_screen_name);
        dest.writeString(thumbnail_pic);
        dest.writeString(bmiddle_pic);
        dest.writeString(original_pic);
        dest.writeParcelable(geo, flags);
        dest.writeParcelable(user, flags);
        dest.writeParcelable(retweeted_status, flags);
        dest.writeInt(reposts_count);
        dest.writeInt(comments_count);
        dest.writeInt(attitudes_count);
        dest.writeInt(mlevel);
        dest.writeString(created_at);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WeiboStatus> CREATOR = new Creator<WeiboStatus>() {
        @Override
        public WeiboStatus createFromParcel(Parcel in) {
            return new WeiboStatus(in);
        }

        @Override
        public WeiboStatus[] newArray(int size) {
            return new WeiboStatus[size];
        }
    };
}
