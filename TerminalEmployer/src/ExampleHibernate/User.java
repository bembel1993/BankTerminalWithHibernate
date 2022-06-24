package ExampleHibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "user")
public class User {
  /*  @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq",
            sequenceName = "SEQ_USER", allocationSize = 10)
    @Column(name = "id", updatable = false, nullable = false)*/
  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  @Column (name = "id")
    private Integer id;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "login")
    private String login;

    public User() {
    }

    public User(Integer id, String login, String name) {
        super();
        this.id = id;
        this.login = login;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}