package com.tencent.qcloud.tim.uikit.modules.chat.layout.message.holder;

import android.view.View;
import android.widget.TextView;

import com.tencent.qcloud.tim.uikit.R;
import com.tencent.qcloud.tim.uikit.component.face.FaceManager;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;

public class MessageTextHolder extends MessageContentHolder {

    private TextView msgBodyText;

    public MessageTextHolder(View itemView) {
        super(itemView);
    }

    @Override
    public int getVariableLayout() {
        return R.layout.message_adapter_content_text;
    }

    @Override
    public void initVariableViews() {
        msgBodyText = rootView.findViewById(R.id.msg_body_tv);
    }

    @Override
    public void layoutVariableViews(MessageInfo msg, int position) {
        chatTimeText.setVisibility(View.GONE);//这里不需要显示时间
        // leftUserIcon.setVisibility(View.GONE);
        //rightUserIcon.setVisibility(View.GONE);
        usernameText.setVisibility(View.GONE);
        msgBodyText.setVisibility(View.VISIBLE);
        if (msg.isSelf()) {
            FaceManager.handlerEmojiText(msgBodyText, msg.getExtra().toString(), false);
        } else {
            FaceManager.handlerEmojiText2(msgBodyText, msg.getFromUser() + "：" + msg.getExtra().toString(), msg.getFromUser().length(),false);
        }
        if (properties.getChatContextFontSize() != 0) {
            msgBodyText.setTextSize(properties.getChatContextFontSize());
        }
        if (msg.isSelf()) {
            if (properties.getRightChatContentFontColor() != 0) {
                msgBodyText.setTextColor(properties.getRightChatContentFontColor());
            }
        } else {
            if (properties.getLeftChatContentFontColor() != 0) {
                msgBodyText.setTextColor(properties.getLeftChatContentFontColor());
            }
        }
    }

}
