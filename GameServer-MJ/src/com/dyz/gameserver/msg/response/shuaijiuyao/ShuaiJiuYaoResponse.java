package com.dyz.gameserver.msg.response.shuaijiuyao;

import com.context.ConnectAPI;
import com.dyz.gameserver.commons.message.ServerResponse;
import com.dyz.gameserver.pojo.CardListVO;
import com.dyz.persist.util.JsonUtilTool;
import java.io.IOException;

/**
 * 
 * @author wuislet
 *
 */
public class ShuaiJiuYaoResponse extends ServerResponse {
    /**
     * 必须调用此方法设置消息号
     *
     * @param status
     * @param
     */
    public ShuaiJiuYaoResponse(int status, CardListVO cardlistVO) {
        super(status, ConnectAPI.SHUAIJIUYAO_RESPONSE);
        try {
            output.writeUTF(JsonUtilTool.toJson(cardlistVO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
