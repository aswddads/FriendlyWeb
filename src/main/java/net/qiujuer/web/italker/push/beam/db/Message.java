package net.qiujuer.web.italker.push.beam.db;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_MESSAGE")
public class Message {
    public static final int TYPE_STR = 1;  //string
    public static final int TYPE_PIC = 2;  //picture
    public static final int TYPE_FILE = 3; //file
    public static final int TYPE_AUDIO = 4; //audio

    //key
    @Id
    @PrimaryKeyJoinColumn
    //不自定生存id，id由代码写入,由客户端负责生成
    // @GeneratedValue(generator = "uuid")//key-->UUID
    @GenericGenerator(name = "uuid", strategy = "uuid2")//uuid-->uuid2,uuid2 is commmon UUID toString
    @Column(updatable = false, nullable = false)//can't bu modifide,can't be null;
    private String id;

    //content can't be null
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    //attach
    @Column
    private String attach;

    //message type

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    @Column(nullable = false)
    private int type;

    @CreationTimestamp//定义为创建的时间戳，在创建的时候就写入
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @UpdateTimestamp//定义为更新的时间戳，在创建的时候就已经写入
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    //much message to a sender
    @JoinColumn(name = "senderId")
    @ManyToOne(optional = false)
    private User sender;
    @Column(nullable = false, updatable = false, insertable = false)
    private String senderId;

    @ManyToOne
    @JoinColumn(name = "receiverId")
    private User receiver;
    @Column(updatable = false, insertable = false)
    private String receiverId;

    // a group can get much message
    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;
    @Column(updatable = false, insertable = false)
    private String groupId;

}
