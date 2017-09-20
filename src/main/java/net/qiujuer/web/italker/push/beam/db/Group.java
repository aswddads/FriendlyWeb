package net.qiujuer.web.italker.push.beam.db;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_GROUP")
public class Group {

    //key
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(generator = "uuid")//key-->UUID auto create uuid
    @GenericGenerator(name = "uuid", strategy = "uuid2")//uuid-->uuid2,uuid2 is commmon UUID toString
    @Column(updatable = false, nullable = false)//can't bu modifide,can't be null;
    private String id;

    //group name
    @Column(nullable = false)
    private String name;

    //group description
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String picture;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @CreationTimestamp//定义为创建的时间戳，在创建的时候就写入
    @Column(nullable = false)

    private LocalDateTime createAt = LocalDateTime.now();

    @UpdateTimestamp//定义为更新的时间戳，在创建的时候就已经写入
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    //群的创建者
    //optional：可选为false必须有一个创建者
    // FetchType.EAGER为急加载，意味着加载群的信息的时候就必须加载owner的信息
    //cascade:联级级别为all，所有的更改（更新、删除等）都进行关系更新
    @ManyToOne(optional = false,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "ownerId")
    private User owner;
    @Column(nullable = false,updatable = false,insertable = false)
    private String ownerId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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
}
