package net.qiujuer.web.italker.push.beam.db;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * user follow model
 */

@Entity
@Table(name = "TB_USER_FOLLOW")
public class UserFollow {
    //key
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(updatable = false, nullable = false)
    private String id;

    //define a origin user,you follow other
    //you can follow much people ,每一次关注都是一条记录  多对一
    // user-->多个UserFollow
    @ManyToOne(optional = false)//optional 不可选，必须存储，一条关注记录必须要有一定要有一个关注人
    // define 关联的表的字段名，对应user中的id字段
    //定义的是数据库中的存储字段
    @JoinColumn(name = "originId")
    private User origin;
    //把这个列提取到model中,不允许为null，不允许更新，插入
    @Column(nullable = false, updatable = false, insertable = false)
    private String originId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getOrigin() {
        return origin;
    }

    public void setOrigin(User origin) {
        this.origin = origin;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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

    //define you follow other user
    //也是多对一，可以被很多人关注，每次关注都是一条记录
    //多个UserFollow对应一个User的情况
    @ManyToOne(optional = false)
    //定义关联的表字段名为targetId,对应的是user.id
    @JoinColumn(name = "targetId")

    private User target;
    //把这个列提取到model中,不允许为null，不允许更新，插入
    @Column(nullable = false, updatable = false, insertable = false)
    private String targetId;


    // 别名， It's the other name with target
    @Column
    private String alias;

    @CreationTimestamp//定义为创建的时间戳，在创建的时候就写入
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @UpdateTimestamp//定义为更新的时间戳，在创建的时候就已经写入
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();
}


