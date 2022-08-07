package city.ac.licensing.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "license_request_status", schema = "public")
public class LicenseRequestStatusEntity implements Serializable {
    private static final long serialVersionUID = -4820756067888551977L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public LicenseRequestStatusEntity(Long id) {
        this.id = id;
    }
    public LicenseRequestStatusEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LicenseRequestStatusEntity)) return false;
        LicenseRequestStatusEntity that = (LicenseRequestStatusEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "LicenseRequestStatusEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
