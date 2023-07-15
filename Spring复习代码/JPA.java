import javax.persistence.*;
import java.util.Date;

// @Entity 注解将类标识为实体类
@Entity 
// @Table 注解指定实体类对应的数据库表名  
@Table(name="user")
public class User {

  // @Id 注解指定实体类的主键
  @Id
  // @GeneratedValue 注解配置主键的生成策略
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  // @Column 注解指定实体类属性到数据库表列的映射
  @Column(name="name")
  private String name;

  // @ManyToOne 定义多对一关系
  @ManyToOne
  // @JoinColumn 指定关联表中的外键列名  
  @JoinColumn(name="dept_id")
  private Department department;

  // @OneToMany 定义一对多关系
  @OneToMany(mappedBy="user")
  private List<Order> orders;

  // @ManyToMany 定义多对多关系
  @ManyToMany
  // @JoinTable 配置多对多关系的关联表
  @JoinTable(name="user_role",
      joinColumns={@JoinColumn(name="user_id")},
      inverseJoinColumns={@JoinColumn(name="role_id")})
  private Set<Role> roles;

  // @Transient 声明非数据库映射的属性
  @Transient 
  private String tempData;

  // @CreationTimestamp 在插入时自动填充创建时间
  @CreationTimestamp
  private Date createTime;

  // @UpdateTimestamp 在更新时自动填充最后修改时间
  @UpdateTimestamp 
  private Date updateTime;
  
}