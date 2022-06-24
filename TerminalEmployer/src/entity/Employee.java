package entity;

import ExampleHibernate.User;

import javax.persistence.*;

@Entity
@Table(name = "countsals")

public class Employee {

        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        @Column (name = "person_id")
        private int personId;

        @Column (name = "price")
        private String price;

        @Column (name = "fname")
        private String fName;

        @Column (name = "lname")
        private String lName;

        @SuppressWarnings("JpaAttributeTypeInspection")
        @OneToOne(mappedBy = "employee", fetch = FetchType.EAGER,
                cascade = CascadeType.ALL, orphanRemoval = true)
        private Password password;

        public Employee() {
        }

        public Employee(String price, String fName, String lName) {
                this.price = price;
                this.fName =fName;
                this.lName = lName;
                password = new Password();
        }

        public Integer getPersonId() {
                return personId;
        }

        public void setPersonId(int personId) {
                this.personId = personId;
        }

        public String getPrice() {
                return price;
        }

        public void setPrice(String price) {
                this.price = price;
        }

        public String getfName() {
                return fName;
        }

        public void setfName(String fName) {
                this.fName = fName;
        }

        public String getlName() {
                return lName;
        }

        public void setlName(String lName) {
                this.lName = lName;
        }

        public Password getPassword() {
                return password;
        }

        public void setPassword(Password password) {
                this.password = password;
        }

        @Override
        public String toString() {
                return "Employee{" +
                        "personId=" + personId +
                        ", price='" + price + '\'' +
                        ", fName='" + fName + '\'' +
                        ", lName='" + lName + '\'' +
                        ", password=" + password +
                        '}';
        }
}
