package com.nibbledebt.integration.sao;

import com.nibbledebt.common.error.NotificationException;

import java.util.List;

/**
 * @author a.salachyonok
 */
public interface IMessageSao {
    void sendEmail(String subject, String content, List<String> toEmails) throws NotificationException;
}
