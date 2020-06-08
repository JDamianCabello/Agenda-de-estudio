package es.jdamiancabello.agendadeestudio.data.model;


import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    public static final String userKey = "username";
    public static final String passwordKey = "password";
    public static final String userToken = "token";

    private String api_token;
    private String email;
    private String name;
    private boolean verified;

    protected User(Parcel in) {
        api_token = in.readString();
        email = in.readString();
        name = in.readString();
        verified = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(api_token);
        dest.writeString(email);
        dest.writeString(name);
        dest.writeByte((byte) (verified ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
