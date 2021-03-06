package net.qiujuer.web.italker.push.beam.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_GROUP_MEMBER")
public class GroupMember {

    public static final int NOTIFY_LEVEL_INVALID = -1;//默认不接收消息
    public static final int NOTIFY_LEVEL_NONE = 0;//默认通知级别
    public static final int NOTIFY_LEVEL_CLOSE = 1;//默认接收不提示

    public static final int PERMISSION_TYPE_NONE = 0;//默人权限，普通成员
    public static final int PERMISSION_TYPE_ADMIN = 1;//管理员
    public static final int PERMISSION_TYPE_ADMIN_SU = 100;//群主
    //key
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(updatable = false, nullable = false)
    private String id;

    // 别名， It's the other name
    @Column
    private String alias;

    //消息通知级别
    @Column(nullable = false)
    private int notifyLevel = NOTIFY_LEVEL_NONE;

    //成员的权限类型
    @Column(nullable = false)
    private int permissionType = PERMISSION_TYPE_NONE;

    @CreationTimestamp//定义为创建的时间戳，在创建的时候就写入
    @Column(nullable = false)

    private LocalDateTime createAt = LocalDateTime.now();

    @UpdateTimestamp//定义为更新的时间戳，在创建的时候就已经写入
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    //成员对应的用户信息
    @ManyToOne(optional = false,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;
    @Column(nullable = false,updatable = false,insertable = false)
    private String userId;

    //成员对应的群信息
    @ManyToOne(optional = false,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "groupId")
    private Group group;
    @Column(nullable = false,updatable = false,insertable = false)
    private String groupId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getNotifyLevel() {
        return notifyLevel;
    }

    public void setNotifyLevel(int notifyLevel) {
        this.notifyLevel = notifyLevel;
    }

    public int getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(int permissionType) {
        this.permissionType = permissionType;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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
}
