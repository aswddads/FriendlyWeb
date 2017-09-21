package net.qiujuer.web.italker.push.beam.db;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_APPLY")
public class Apply {
    public static final int TYPE_ADD_USER = 1;
    public static final int TYPE_ADD_GROUP = 2;

    //key
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(generator = "uuid")//key-->UUID
    @GenericGenerator(name = "uuid", strategy = "uuid2")//uuid-->uuid2,uuid2 is commmon UUID toString
    @Column(updatable = false, nullable = false)//can't bu modifide,can't be null;
    private String id;

    @CreationTimestamp//定义为创建的时间戳，在创建的时候就写入
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @UpdateTimestamp//定义为更新的时间戳，在创建的时候就已经写入
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    //申请的附带信息
    @Column(nullable = false)
    private String description;

    //可为空，附件
    @Column(columnDefinition = "TEXT")
    private String attach;

    //申请类型
    @Column(nullable = false)
    private int type;

    //添加的对象,根据类型变动
    @Column(nullable = false)
    private String targetId;

    //申请人
    @ManyToOne
    @JoinColumn(name = "applicantId")
    private User applicant;
    @Column(nullable = false,insertable = false)
    private String applicantId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }
}
