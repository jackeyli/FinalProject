package li.yifei4.services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import li.yifei4.beans.ConditionCheckResult;
import li.yifei4.datas.dao.NotificationHistoryDao;
import li.yifei4.datas.dao.UserDao;
import li.yifei4.datas.entity.NotificationCondition;
import li.yifei4.datas.entity.NotificationHistory;
import li.yifei4.datas.entity.User;
import li.yifei4.util.EntityManagerUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("notificationService")
public class DefaultNotificationServiceImpl implements NotificationService{
    @Resource(name="userEntityDao")
    private UserDao userDao;

    @Resource(name="notificationHistoryDao")
    private NotificationHistoryDao historyDao;
    @Override
    public boolean storeHistoryAndNotifiyUser(ConditionCheckResult result) {
        NotificationCondition condition = result.getCondition();
        User owner = condition.getUser();
        if(owner != null){
            if(Objects.equals(condition.getNotifyType(),"PHONE")) {
                this.sendMessage(owner.getPhone(), result.getNotificationText());
                NotificationHistory history = new NotificationHistory();
                history.setCondition(condition);
                history.setTriggerTime(new Date());
                EntityManagerUtil.getEntityManager().persist(history);
            }
            if(Objects.equals(condition.getNotifyType(),"EMAIL")){
                this.sendEmail(owner.getEmail(),result.getNotificationText());
                NotificationHistory history = new NotificationHistory();
                history.setCondition(condition);
                history.setTriggerTime(new Date());
                EntityManagerUtil.getEntityManager().persist(history);
            }
        }
        return true;
    }

    public List<NotificationHistory> getNotificationHistory(int userId, Date from, Date to){
        return historyDao.getNotificationHistory(userId,from,to);
    }
    private boolean sendEmail(String emailAddr,String message){
        String encodedKey = "QUtJQUpOVEVUMllDUEIzNUhKU0E=";
        String encodedSecretKey = "ZnVOU1Q2MlB5VGJNNmE1eW1sayt0Rml6VGhTd1NyS1ArTk9YOTd5UQ==";
        AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(
                        new String(Base64.getDecoder().decode(encodedKey)),
                        new String(Base64.getDecoder().decode(encodedSecretKey)))))
                .withRegion("us-east-1").build();
        SendEmailRequest request = new SendEmailRequest()
                .withSource("yifei_li_us@163.com")
                .withDestination(
                        new Destination().withToAddresses(emailAddr))
                .withMessage(
                        new Message()
                                .withSubject(new Content().withData("Notification Alert").withCharset("UTF-8"))
                                .withBody(
                                        new Body()
                                                .withText(new Content().withData(message).withCharset("UTF-8"))));
        SendEmailResult response = client.sendEmail(request);
        return true;
    }
    private boolean sendMessage(String phoneNumber,String message) {
        String encodedKey = "QUtJQUpOVEVUMllDUEIzNUhKU0E=";
        String encodedSecretKey = "ZnVOU1Q2MlB5VGJNNmE1eW1sayt0Rml6VGhTd1NyS1ArTk9YOTd5UQ==";
        AmazonSNSClient snsClient = (AmazonSNSClient) AmazonSNSClientBuilder.standard().withCredentials(
                new AWSStaticCredentialsProvider(new BasicAWSCredentials(new String(Base64.getDecoder().decode(encodedKey)),
                        new String(Base64.getDecoder().decode(encodedSecretKey)))))
                        .withRegion("us-east-1").build();
        Map<String, MessageAttributeValue> smsAttributes =
                new HashMap<String, MessageAttributeValue>();
        smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
                .withStringValue("mySenderID") //The sender ID shown on the device.
                .withDataType("String"));
        smsAttributes.put("AWS.SNS.SMS.MaxPrice", new MessageAttributeValue()
                .withStringValue("0.50") //Sets the max price to 0.50 USD.
                .withDataType("Number"));
        smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
                .withStringValue("Promotional") //Sets the type to promotional.
                .withDataType("String"));
        snsClient.publish(new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(phoneNumber)
                .withMessageAttributes(smsAttributes));
        return true;
    }
}
