package net.qiujuer.web.italker.push.beam.db;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_PUSH_HISTORY")
public class PushHistory {
    //key
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(generator = "uuid")//key-->UUID
    @GenericGenerator(name = "uuid", strategy = "uuid2")//uuid-->uuid2,uuid2 is commmon UUID toString
    @Column(updatable = false, nullable = false)//can't bu modifide,can't be null;
    private String id;

    //推送的具体实体存储的都是JSON字符串
    //BLOB是比TEXT更多的大字段类型
    @Lob
    @Column(nullable = false, columnDefinition = "BLOB")
    private String entry;

    //实体类型
    @Column(nullable = false)
    private int entryType;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "receiverId")//默认是：receiver_id
    private User receiver;
    @Column(nullable = false, updatable = false, insertable = false)
    private String receiverId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public int getEntryType() {
        return entryType;
    }

    public void setEntryType(int entryType) {
        this.entryType = entryType;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverPushId() {
        return receiverPushId;
    }

    public void setReceiverPushId(String receiverPushId) {
        this.receiverPushId = receiverPushId;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public LocalDateTime getArrivalAt() {
        return arrivalAt;
    }

    public void setArrivalAt(LocalDateTime arrivalAt) {
        this.arrivalAt = arrivalAt;
    }

    //发送者可为空，可能为系统消息
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "senderId")//默认是：receiver_id
    private User sender;
    @Column(updatable = false, insertable = false)
    private String senderId;

    //接收者当前状态下的设备推送ID
    @Column
    private String receiverPushId;

    @CreationTimestamp//定义为创建的时间戳，在创建的时候就写入
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @UpdateTimestamp//定义为更新的时间戳，在创建的时候就已经写入
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    //可为空
    @Column
    private LocalDateTime arrivalAt = LocalDateTime.now();

}
