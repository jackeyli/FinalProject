package li.yifei4.services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import li.yifei4.beans.ConditionCheckResult;
import li.yifei4.datas.dao.UserDao;
import li.yifei4.datas.entity.NotificationCondition;
import li.yifei4.datas.entity.NotificationHistory;
import li.yifei4.datas.entity.User;
import li.yifei4.util.EntityManagerUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("notificationService")
public class DefaultNotificationServiceImpl implements NotificationService{
    @Resource(name="userEntityDao")
    private UserDao userDao;
    @Override
    public boolean storeHistoryAndNotifiyUser(ConditionCheckResult result) {
        NotificationCondition condition = result.getCondition();
        User owner = EntityManagerUtil.getEntityManager().find(User.class,condition.getUserOid());
        if(owner != null){
            String phoneNumber = owner.getPhone();
            this.sendMessage(owner.getPhone(),result.getNotificationText());
            // set Record
            NotificationHistory history = new NotificationHistory();
            history.setNtOid(condition.getOid());
            history.setTriggerTime(new Date());
            EntityManagerUtil.getEntityManager().persist(history);
        }
        return true;
    }
    private boolean sendMessage(String phoneNumber,String message) {
        AmazonSNSClient snsClient = (AmazonSNSClient) AmazonSNSClientBuilder.standard().withCredentials(
                new AWSStaticCredentialsProvider(new BasicAWSCredentials("AKIAIBNG447CADIRD7CA",
                        "DYXTDiH9EatSFEAlurYGNnPSN7P/0tQlUwa65JcX")))
                        .withRegion("us-east-2").build();
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
