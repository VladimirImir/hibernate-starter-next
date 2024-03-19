package com.dev.entity;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SortNatural;
import org.hibernate.annotations.Cache;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name")
@ToString(exclude = "users")
@Builder
@Entity
//@BatchSize(size = 3)
@Audited
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "company_id")
    //@org.hibernate.annotations.OrderBy(clause = "username DESC, lastname ASC")
    //@OrderBy("personalInfo.firstname")
    //@OrderColumn(name = "id")
    //@SortNatural
    @MapKey(name = "username")
    @SortNatural
    @NotAudited
    private Map<String, User> users = new TreeMap<>();

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "company_locale", joinColumns = @JoinColumn(name = "company_id"))
    @MapKeyColumn(name = "lang")
    //private List<LocaleInfo> locales = new ArrayList<>();
    @NotAudited
    private Map<String, String> locales = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getUsername(), user);
        user.setCompany(this);
    }

}
