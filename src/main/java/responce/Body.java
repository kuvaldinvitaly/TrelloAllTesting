package responce;

import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;

public class Body {
    @SerializedName("key")
    String key = "";

    @SerializedName("token")
    String token = "";

    @SerializedName("name")
    private String name = "";


    @SerializedName("idBoard")
    private String idBoard = "";

    @SerializedName("idList")
    private String idList = "";

    @SerializedName("idCard")
    private String idCard = "";

    @SerializedName("idCheckList")
    private String idCheckList = "";

    @SerializedName("value")
    private String value = "";

    @SerializedName("url")
    private String url = "";

    @SerializedName("due")
    private String due;

    @SerializedName("desc")
    private String desc;

    @SerializedName("state")
    private String state;

    @SerializedName("text")
    private String text;


    public void setText(String text) {
        this.text = text;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ArrayList<String> idAllLists = new ArrayList<>();

    public ArrayList<String> getIdAllCheckItems() {
        return idAllCheckItems;
    }

    public String getKey() {
        return key;
    }

    public String getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

    public String getIdBoard() {
        return idBoard;
    }

    public String getIdList() {
        return idList;
    }

    public String getValue() {
        return value;
    }

    public String getUrl() {
        return url;
    }

    public String getDue() {
        return due;
    }

    public String getDesc() {
        return desc;
    }

    public String getState() {
        return state;
    }

    public String getText() {
        return text;
    }

    public ArrayList<String> idAllCheckItems = new ArrayList<>();

    public ArrayList<String> getIdAllLists() {
        return idAllLists;
    }

    public String getIdCheckList() {
        return idCheckList;
    }

    public void setIdCheckList(String idCheckList) {
        this.idCheckList = idCheckList;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setIdList(String idList) {
        this.idList = idList;
    }

    public void setIdBoard(String idBoard) {
        this.idBoard = idBoard;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setName(String name) {
        this.name = name;
    }

}
