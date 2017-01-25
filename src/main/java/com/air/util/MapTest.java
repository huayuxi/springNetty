package com.air.util;


import com.air.constant.CmdType;
import com.air.entity.StautsMsgTemplateEntity;
import com.air.pojo.AirUser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author semstouch
 * @Date 2017/1/20
 **/
public class MapTest {
    public static void main(String[] args) {

        StautsMsgTemplateEntity msgTemplateEntity= new StautsMsgTemplateEntity();
        msgTemplateEntity.setFirstData("请注意，你的设备已远程开启成功","#173177");
        msgTemplateEntity.setKeynote1Data("办公室1号报警器", "#173177");
        msgTemplateEntity.setKeynote2Data("000000000000", "#173177");
        msgTemplateEntity.setKeynote3Data("在线", "#173177");
        msgTemplateEntity.setKeynote4Data("2017-02-25", "#173177");
        msgTemplateEntity.setKeynote5Data("启动状态", "#173177");
        msgTemplateEntity.setRemarkData("密切注意哦！", "#173177");

        Map params = new HashMap();
        params.put("touser", "o3aw6v5x9S36WOS0viwzp80QvP5o");
        params.put("template_id", "Ku3Kw7p5fGBsJknGoTfiAzaJpdWW9FU408wwfaUTJ0o");
        params.put("url", "http://air.semsplus.com/rest/wx/go");
        params.put("data",msgTemplateEntity);
        String rs = StringUtils.MapToStr(params);
        System.out.print(rs);

    }
}
