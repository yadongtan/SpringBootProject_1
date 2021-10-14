package com.yadong.springbootproject_1.entity;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

@Component
@Alias("kill")
public class Kill {
    String killId;  //外键(商品ItemId)
    String KillCount;//剩余数量

    public Kill(){}

    @Override
    public String toString() {
        return "Kill{" +
                "killId='" + killId + '\'' +
                ", KillCount='" + KillCount + '\'' +
                '}';
    }

    public Kill(String killId, String killCount) {
        this.killId = killId;
        KillCount = killCount;
    }

    public String getKillId() {
        return killId;
    }

    public void setKillId(String killId) {
        this.killId = killId;
    }

    public String getKillCount() {
        return KillCount;
    }

    public void setKillCount(String killCount) {
        KillCount = killCount;
    }


}
