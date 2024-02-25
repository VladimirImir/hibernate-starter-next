package com.dev.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "username")
@ToString(exclude = {"company", "profile", "userChats"})
@Builder
@Entity
@Table(name = "users", schema = "public")
//@TypeDef(name = "dev", typeClass = JsonBinaryType.class)
public class User implements Comparable<User> {

    /*@Id
    @GeneratedValue(generator = "user_gen", strategy = GenerationType.TABLE)
    //@SequenceGenerator(name = "user_gen", sequenceName = "users_id_seq", allocationSize = 1)
    @TableGenerator(name = "user_gen", table = "all_sequence",
            pkColumnName = "table_name", valueColumnName = "pk_value",
            allocationSize = 1)
    private Long id;*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date"))
    private PersonalInfo personalInfo;


    @Column(unique = true)
    private String username;


    //@Type(type = "dev")
    //private String info;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id") // company_id
    private Company company;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserChat> userChats = new ArrayList<>();

    @Override
    public int compareTo(User o) {
        return username.compareTo(o.username);
    }
}