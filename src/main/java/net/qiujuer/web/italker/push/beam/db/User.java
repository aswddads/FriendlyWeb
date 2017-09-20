package net.qiujuer.web.italker.push.beam.db;


import com.sun.tools.corba.se.idl.StringGen;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * user model,对应数据库
 */
@Entity
@Table(name = "TB_USER")
public class User {
    //key
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(generator = "uuid")//key-->UUID
    @GenericGenerator(name = "uuid", strategy = "uuid2")//uuid-->uuid2,uuid2 is commmon UUID toString
    @Column(updatable = false, nullable = false)//can't bu modifide,can't be null;
    private String id;

    @Column(nullable = false, length = 128, unique = true) //name must only one
    private String name;

    @Column(nullable = false, length = 64, unique = true) //phone must only one
    private String phone;

    @Column(nullable = false)
    private String password;

    @Column
    private String portrait; //portrait can be null

    @Column
    private String descriiption;

    @Column(nullable = false)
    private int sex = 0;

    @Column(unique = true)
    private String token; //token can get the message about user,So the Token must be only one;

    @Column
    private String pushId;//push id

    @CreationTimestamp//定义为创建的时间戳，在创建的时候就写入
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @UpdateTimestamp//定义为更新的时间戳，在创建的时候就已经写入
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getDescriiption() {
        return descriiption;
    }

    public void setDescriiption(String descriiption) {
        this.descriiption = descriiption;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
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

    public LocalDateTime getLastReceivedAt() {
        return lastReceivedAt;
    }

    public void setLastReceivedAt(LocalDateTime lastReceivedAt) {
        this.lastReceivedAt = lastReceivedAt;
    }

    @Column//最后一次收到消息的时间
    private LocalDateTime lastReceivedAt = LocalDateTime.now();

    public Set<UserFollow> getFollowing() {
        return following;
    }

    public void setFollowing(Set<UserFollow> following) {
        this.following = following;
    }

    public Set<UserFollow> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<UserFollow> followers) {
        this.followers = followers;
    }

    //获取关注的人的列表
    //对应的数据库表字段为TB_USER_FOLLOW.originId
    @JoinColumn(name = "originId")
    //定义为懒加载，默认加载User信息的时候，并不查询这个集合
    @LazyCollection(LazyCollectionOption.EXTRA)

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserFollow> following = new HashSet<>();

    //关注我的人的列表
    //对应的数据库表字段为TB_USER_FOLLOW.targetId
    @JoinColumn(name = "targetId")
    //定义为懒加载，默认加载User信息的时候，并不查询这个集合
    @LazyCollection(LazyCollectionOption.EXTRA)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserFollow> followers = new HashSet<>();


}
