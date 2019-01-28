package personal.wl.mobilepointapp.entity.pos;

import java.io.Serializable;

public class Member implements Serializable {

    private String Cardid;
    private String Mobile;
    private String NickName;
    private Long MemberLevel;
    private Long Points;
    private Double TotalAmount;

    public Member() {
    }

    public String getCardid() {
        return Cardid;
    }

    public void setCardid(String cardid) {
        Cardid = cardid;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public Long getMemberLevel() {
        return MemberLevel;
    }

    public void setMemberLevel(Long memberLevel) {
        MemberLevel = memberLevel;
    }

    public Long getPoints() {
        return Points;
    }

    public void setPoints(Long points) {
        Points = points;
    }

    public Double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        TotalAmount = totalAmount;
    }
}
